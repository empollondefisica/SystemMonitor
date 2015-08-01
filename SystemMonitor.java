
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.geometry.Orientation;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;

import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.attribute.UserPrincipal;

import java.util.HashMap;
import java.util.Map;

public class SystemMonitor extends Application
{
    SystemProcesses      oUpdateSystemProcesses = null;
    SystemProcesses      oSystemProcesses       = null;
    CPUs                 oUpdateCPUs            = null;
    CPUs                 oCPUs                  = null;
    File                 oFile                  = null;
    SimpleStringProperty oInterval              = null;

    SystemProcesses updateSystemProcesses = null;
    SystemProcesses systemProcesses = null;
    CPUs updateCpus = null;
    CPUs cpus = null;
    File file = null;
    HashMap<String, XYChart.Series<Number, Number>> seriesMap;
    HashMap<String, CPU> prevCPUMap;

    SimpleStringProperty interval = null;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public void init()
    {
        seriesMap = new HashMap<String, XYChart.Series<Number, Number>>();
        prevCPUMap = new HashMap<String, CPU>();
    }

    public void start(Stage stage)
    {
        oFile                  = new File("/proc");
        oUpdateSystemProcesses = new SystemProcesses();
        oSystemProcesses       = new SystemProcesses();
        oUpdateCPUs            = new CPUs();
        oCPUs                  = new CPUs();
        oInterval              = new SimpleStringProperty();

        updateCPUList();
        updateProcessList();

        oInterval.setValue("" + oSystemProcesses.count());

        Group      group      = new Group();
        Scene      scene      = new Scene(group, 640, 800);
        BorderPane borderPane = new BorderPane();
        SplitPane  splitPane  = new SplitPane();
        TabPane    tabPane    = new TabPane();

        NumberAxis                     xAxis        = new NumberAxis();
        NumberAxis                     yAxis        = new NumberAxis();
        LineChart<Number, Number>      lineChart    = new LineChart<Number, Number>(xAxis, yAxis);
        XYChart.Series<Number, Number> userSeries   = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> systemSeries = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> totalSeries  = new XYChart.Series<Number, Number>();

        MenuBar  menuBar      = new MenuBar();
        Menu     menu         = new Menu("Menu");
        Menu     procCount    = new Menu();
        MenuItem exitMenuItem = new MenuItem("Exit");

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabPane.getTabs().add(buildProcessTab());
        tabPane.getTabs().add(buildCPUTab());

        exitMenuItem.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent actionEvent)
            {
                Platform.exit();
            }
        });

        menuBar.getMenus().add(menu);
        menuBar.getMenus().add(procCount);

        menu.getItems().add(exitMenuItem);

        procCount.textProperty().bind(oInterval);

        xAxis.setAutoRanging(false);
        xAxis.setForceZeroInRange(false);
        xAxis.setTickLabelsVisible(false);
        xAxis.setMinorTickVisible(false);
        xAxis.setTickMarkVisible(false);

        yAxis.setAutoRanging(false);
        yAxis.setForceZeroInRange(true);
        yAxis.setUpperBound(100.0);
        yAxis.setMinorTickVisible(false);

        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(false);

        splitPane.getItems().add(tabPane);
        splitPane.getItems().add(lineChart);
        splitPane.setOrientation(Orientation.VERTICAL);

        borderPane.setTop(menuBar);
        borderPane.setCenter(splitPane);
        borderPane.setBottom(lineChart);
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        borderPane.prefHeightProperty().bind(scene.heightProperty());

        group.getChildren().add(borderPane);

        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>()
        {
            CPU prevCPU = new CPU();
            CPU currCPU = null;
            int X = 0;
            double userPercent = 0.0;
            double systemPercent = 0.0;
            double totalPercent = 0.0;

            Long currWork;
            Long currTotal;
            Long prevWork;
            Long prevTotal;

            public void handle(ActionEvent event)
            {
                updateProcessList();
                updateCPUList();

                for(CPU currCPU : oCPUs.getCollection())
                {
                    if( !currCPU.getName().equalsIgnoreCase("cpu") )
                    {
                        if(prevCPUMap.get(currCPU.getName()) == null)
                        {
                            prevCPUMap.put(currCPU.getName(), new CPU());
                        }

                        prevCPU = prevCPUMap.get(currCPU.getName());

                        if(seriesMap.get(currCPU.getName()) == null)
                        {
                            XYChart.Series<Number, Number> tempSeries = new XYChart.Series<Number, Number>();
                            tempSeries.setName(currCPU.getName());
                            lineChart.getData().add(tempSeries);
                            seriesMap.put(currCPU.getName(), tempSeries);
                        }

                        currWork = currCPU.getWork();
                        prevWork = prevCPUMap.get(currCPU.getName()).getWork();
                        currTotal = currCPU.getTotal();
                        prevTotal = prevCPUMap.get(currCPU.getName()).getTotal();

                        userPercent = (double)(currCPU.getUser() - prevCPU.getUser()) / (double)(currTotal - prevTotal) * 100.0;
                        systemPercent = (double)(currCPU.getSystem() - prevCPU.getSystem()) / (double)(currTotal - prevTotal) * 100.0;
                        totalPercent = (double)(currWork - prevWork) / (double)(currTotal - prevTotal) * 100.0;

                        seriesMap.get(currCPU.getName()).getData().add(new XYChart.Data<Number, Number>(X, totalPercent));

                        prevCPUMap.get(currCPU.getName()).update(currCPU);
                    }
                }

                xAxis.setLowerBound(X - 29);
                xAxis.setUpperBound(X);
                ++X;

                for(Map.Entry<String, XYChart.Series<Number, Number>> entry : seriesMap.entrySet())
                {
                    while(entry.getValue().getData().size() > 30)
                    {
                        entry.getValue().getData().remove(0);
                    }
                }

                oCPUs.getTableView().getColumns().get(0).setVisible(false);
                oCPUs.getTableView().getColumns().get(0).setVisible(true);
                oSystemProcesses.getTableView().getColumns().get(0).setVisible(false);
                oSystemProcesses.getTableView().getColumns().get(0).setVisible(true);

                oInterval.setValue("" + oSystemProcesses.count());
            }
        }));

        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            public void handle(WindowEvent windowEvent)
            {
                timer.stop();
            }
        });

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        stage.setScene(scene);
        stage.setTitle("SystemMonitor");
        stage.show();
    }

    private Tab buildCPUTab()
    {
        Tab            cpuTab    = new Tab("CPU");
        TableView<CPU> tableView = oCPUs.getTableView();

        cpuTab.setContent(tableView);

        return cpuTab;
    }

    private Tab buildProcessTab()
    {
        Tab                      processTab = new Tab("Processes");
        TableView<SystemProcess> tableView  = oSystemProcesses.getTableView();

        processTab.setContent(tableView);

        return processTab;
    }

    private void updateCPUList()
    {
        oUpdateCPUs.clear();

        try
        {
            File                statFile   = new File(oFile.getAbsoluteFile() + "/stat");
            String              fileText   = readProcFile(statFile);
            String[]            fileLines  =  fileText.split("\n");
            CPU                 tempCPU    = null;
            int                 index      = -1;
            ObservableList<CPU> removeList = FXCollections.observableArrayList();

            for(String line : fileLines)
            {
                if(line.substring(0, 3).equals("cpu"))
                {
                    oUpdateCPUs.add(line);
                }
            }

            for(CPU cpu : oUpdateCPUs.getCollection())
            {
                index = oCPUs.find(cpu);

                if(index != -1)
                {
                    oCPUs.getCollection().get(index).update(
                        cpu.getName(),
                        cpu.getUser(),
                        cpu.getNice(),
                        cpu.getSystem(),
                        cpu.getIdle(),
                        cpu.getIOWait(),
                        cpu.getInterrupts(),
                        cpu.getSoftInterrupts(),
                        cpu.getSteal());
                }
                else
                {
                    oCPUs.add(cpu);
                }
            }

            for(CPU cpu : oCPUs.getCollection())
            {
                index = oUpdateCPUs.find(cpu);
                if(index == -1)
                {
                    cpu.setModified(false);
                }
            }

            for(CPU cpu : oCPUs.getCollection())
            {
                if(!cpu.getModified())
                {
                    removeList.add(cpu);
                }
            }

            oCPUs.getTableView().setItems(oCPUs.getCollection());
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private void updateProcessList()
    {
        oUpdateSystemProcesses.clear();

        File                          tempFile    = null;
        File                          statusFile  = null;
        SystemProcess                 tempProcess = null;
        int                           index       = -1;
        ObservableList<SystemProcess> removeList  = FXCollections.observableArrayList();
        String                        textFile;
        Path                          path;
        UserPrincipal                 owner;

        for(String name : oFile.list())
        {
            try
            {
                if(name.matches("^\\d+$"))
                {
                    tempFile   = new File(oFile.getAbsoluteFile() + "/" + name);
                    statusFile = new File(tempFile.getAbsoluteFile() + "/stat");
                    textFile   = readProcFile(statusFile);
                    path       = Paths.get(statusFile.getAbsoluteFile().toString());
                    owner      = Files.getOwner(path);
                    
                    oUpdateSystemProcesses.add(textFile, owner.getName());
                }
            }
            catch(IOException ioe)
            {
            }
        }

        for(SystemProcess process : oUpdateSystemProcesses.getCollection())
        {
            index = oSystemProcesses.find(process);

            if(index != -1)
            {
                tempProcess = oSystemProcesses.getCollection().get(index);
                tempProcess.update(
                    process.getProcessID(),
                    process.getName(),
                    process.getState(),
                    process.getParentPID(),
                    process.getProcessGroupID(),
                    process.getSessionID(),
                    process.getThreadGroupID(),
                    process.getMinorFaults(),
                    process.getChildMinorFaults(),
                    process.getMajorFaults(),
                    process.getChildMajorFaults(),
                    process.getUserTime(),
                    process.getSystemTime(),
                    process.getChildUserTime(),
                    process.getChildSystemTime(),
                    process.getPriority(),
                    process.getNiceValue(),
                    process.getNumThreads(),
                    process.getStartTime(),
                    process.getVirtualSize(),
                    process.getResidentSetSize(),
                    process.getProcessorNumber(),
                    process.getOwner());
            }
            else
            {
                oSystemProcesses.add(process);
            }
        }

        for(SystemProcess process : oSystemProcesses.getCollection())
        {
            index = oUpdateSystemProcesses.find(process);
            if(index == -1)
            {
                process.setModified(false);
            }
        }

        for(SystemProcess process : oSystemProcesses.getCollection())
        {
            if(!process.getModified())
            {
                removeList.add(process);
            }
            else
            {
                process.setModified(false);
            }
        }

        oSystemProcesses.getCollection().removeAll(removeList);
    }

    private String readProcFile(File procFile) throws IOException
    {
        StringBuilder fileText = new StringBuilder();
        InputStreamReader isr  = new InputStreamReader(new FileInputStream(procFile));
        char              ch;
        int               data = isr.read();

        while(data != -1)
        {
            ch = (char)data;
            fileText.append(ch);
            data = isr.read();
        }
        isr.close();

        return fileText.toString();
    }
}
