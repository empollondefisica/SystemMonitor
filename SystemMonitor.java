
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
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
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

public class SystemMonitor extends Application
{
    SystemProcesses updateSystemProcesses = null;
    SystemProcesses systemProcesses = null;
    CPUs updateCpus = null;
    CPUs cpus = null;
    File file = null;

    SimpleStringProperty interval = null;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public void start(Stage stage)
    {
        file = new File("/proc");

        updateSystemProcesses = new SystemProcesses();
        systemProcesses = new SystemProcesses();
        updateCpus = new CPUs();
        cpus = new CPUs();

        interval = new SimpleStringProperty();

        updateCPUList();
        updateProcessList();

        interval.setValue("" + systemProcesses.count());

        Group group = new Group();
        Scene scene = new Scene(group, 640, 800);
        BorderPane borderPane = new BorderPane();
        TabPane tabPane = new TabPane();

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        XYChart.Series<Number, Number> userSeries = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> systemSeries = new XYChart.Series<Number, Number>();
        XYChart.Series<Number, Number> totalSeries = new XYChart.Series<Number, Number>();

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        Menu procCount = new Menu();
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

        procCount.textProperty().bind(interval);


        xAxis.setAutoRanging(false);
        xAxis.setForceZeroInRange(false);
        yAxis.setAutoRanging(false);
        yAxis.setForceZeroInRange(true);
        yAxis.setUpperBound(100.0);

        userSeries.setName("User");
        systemSeries.setName("System");
        totalSeries.setName("Total");

        lineChart.getData().add(userSeries);
        lineChart.getData().add(systemSeries);
        lineChart.getData().add(totalSeries);
        lineChart.setAnimated(false);

        borderPane.setTop(menuBar);
        borderPane.setCenter(tabPane);
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

                currCPU = cpus.getCollection().get(0);

                currWork = currCPU.getWork();
                prevWork = prevCPU.getWork();
                currTotal = currCPU.getTotal();
                prevTotal = prevCPU.getTotal();

                userPercent = (double)(currCPU.getUser() - prevCPU.getUser()) / (double)(currTotal - prevTotal) * 100.0;
                systemPercent = (double)(currCPU.getSystem() - prevCPU.getSystem()) / (double)(currTotal - prevTotal) * 100.0;
                totalPercent = (double)(currWork - prevWork) / (double)(currTotal - prevTotal) * 100.0;

                userSeries.getData().add(new XYChart.Data<Number, Number>(++X, userPercent));
                systemSeries.getData().add(new XYChart.Data<Number, Number>(X, systemPercent));
                totalSeries.getData().add(new XYChart.Data<Number, Number>(X, totalPercent));
                xAxis.setLowerBound(X - 29);
                xAxis.setUpperBound(X);

                while(userSeries.getData().size() > 30)
                {
                    userSeries.getData().remove(0);
                }

                while(systemSeries.getData().size() > 30)
                {
                    systemSeries.getData().remove(0);
                }

                while(totalSeries.getData().size() > 30)
                {
                    totalSeries.getData().remove(0);
                }

                prevCPU.update(currCPU);

                cpus.getTableView().getColumns().get(0).setVisible(false);
                cpus.getTableView().getColumns().get(0).setVisible(true);
                systemProcesses.getTableView().getColumns().get(0).setVisible(false);
                systemProcesses.getTableView().getColumns().get(0).setVisible(true);

                interval.setValue("" + systemProcesses.count());
            }
        }));

        stage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            public void handle(WindowEvent windowEvent)
            {

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
        Tab cpuTab = new Tab("CPU");
        TableView<CPU> tableView = cpus.getTableView();

        cpuTab.setContent(tableView);

        return cpuTab;
    }

    private Tab buildProcessTab()
    {
        Tab processTab = new Tab("Processes");
        TableView<SystemProcess> tableView = systemProcesses.getTableView();

        processTab.setContent(tableView);

        return processTab;
    }

    private void updateCPUList()
    {
        updateCpus.clear();

        try
        {
            File statFile = new File(file.getAbsoluteFile() + "/stat");
            String fileText = readProcFile(statFile);
            String[] fileLines = fileText.split("\n");
            CPU tempCPU = null;
            int index = -1;
            ObservableList<CPU> removeList = FXCollections.observableArrayList();

            for(String line : fileLines)
            {
                if(line.substring(0, 3).equals("cpu"))
                {
                    updateCpus.add(line);
                }
            }

            for(CPU cpu : updateCpus.getCollection())
            {
                index = cpus.find(cpu);

                if(index != -1)
                {
                    cpus.getCollection().get(index).update(
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
                    cpus.add(cpu);
                }
            }

            for(CPU cpu : cpus.getCollection())
            {
                index = updateCpus.find(cpu);
                if(index == -1)
                {
                    cpu.setModified(false);
                }
            }

            for(CPU cpu : cpus.getCollection())
            {
                if(!cpu.getModified())
                {
                    removeList.add(cpu);
                }
            }

            cpus.getTableView().setItems(cpus.getCollection());
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private void updateProcessList()
    {
        updateSystemProcesses.clear();
        File tempFile = null;
        File statusFile = null;
        SystemProcess tempProcess = null;
        int index = -1;
        ObservableList<SystemProcess> removeList = FXCollections.observableArrayList();
        String textFile;
        Path path;
        UserPrincipal owner;

        for(String name : file.list())
        {
            try
            {
                if(name.matches("^\\d+$"))
                {
                    tempFile = new File(file.getAbsoluteFile() + "/" + name);
                    statusFile = new File(tempFile.getAbsoluteFile() + "/stat");
                    textFile = readProcFile(statusFile);
                    path = Paths.get(statusFile.getAbsoluteFile().toString());
                    owner = Files.getOwner(path);
                    updateSystemProcesses.add(textFile, owner.getName());
                }
            }
            catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
        }

        for(SystemProcess process : updateSystemProcesses.getCollection())
        {
            index = systemProcesses.find(process);

            if(index != -1)
            {
                tempProcess = systemProcesses.getCollection().get(index);
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
                systemProcesses.add(process);
            }
        }

        for(SystemProcess process : systemProcesses.getCollection())
        {
            index = updateSystemProcesses.find(process);
            if(index == -1)
            {
                process.setModified(false);
            }
        }

        for(SystemProcess process : systemProcesses.getCollection())
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

        systemProcesses.getCollection().removeAll(removeList);
    }

    private String readProcFile(File procFile) throws IOException
    {
        StringBuilder fileText = new StringBuilder();

        InputStreamReader isr = new InputStreamReader(new FileInputStream(procFile));
        char ch;
        int data = isr.read();
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
