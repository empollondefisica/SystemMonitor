
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import parser.TypeParser;

public class Types
{
    private ObservableList<Type> oTypeList;
    private FilteredList<Type> oFilteredList;
    private SortedList<Type> oSortedList;
    private TypeParser oTypeParser;
    private String oResourceFile;
    private TableView<Type> oTableView;
    private ToggleGroup oToggleGroup;

    public Types()
    {
        oTypeList = FXCollections.observableArrayList();
        oFilteredList = new FilteredList<Type>( oTypeList, p -> true );
        oSortedList = new SortedList<Type>( oFilteredList );
        oTypeParser = new TypeParser();
        oResourceFile = "";
    }

    public ObservableList<Type> getCollection()
    {
        return oTypeList;
    }

    public FilteredList<Type> getFilteredList()
    {
        return oFilteredList;
    }

    public SortedList<Type> getSortedList()
    {
        return oSortedList;
    }

    public void setResourceFile(String pResourceFile)
    {
        oResourceFile = pResourceFile;
    }

    public void load()
    {
        oTypeParser.parse( oResourceFile );
        for(Map.Entry<String, Type> vEntry : oTypeParser.getTypeMap().entrySet())
        {
            oTypeList.add(vEntry.getValue());
        }
    }

    public TableView<Type> getTableView()
    {
        if(oTableView == null)
        {
            TableColumn<Type, String> vStringColumn;
            RadioButton vRadioButton;
            oToggleGroup = new ToggleGroup();
            oTableView = new TableView<Type>();

            oSortedList.comparatorProperty().bind(oTableView.comparatorProperty());

            oTableView.setItems( oSortedList );
            oTableView.setEditable( true );

            vRadioButton = new RadioButton();
            vRadioButton.setId( "Name" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vRadioButton.setSelected( true );
            vStringColumn = new TableColumn<Type, String>("Name");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "Name" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "Category" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("Category");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "Category" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "Flags" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("Flags");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 200 );
            vStringColumn.setMaxWidth( 900 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "Flags" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "STPL" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("STPL");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "STPL" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "Abbreviation" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("Abbreviation");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "Abbreviation" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "ExternalName" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("External Name");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "ExternalName" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "Format" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("Format");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "Format" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "FormatString" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("Format String");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "FormatString" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "AlternateName" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("Alternate Name");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "AlternateName" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "Group" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("Group");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "Group" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "UPD" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("UPD");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "UPD" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "Territory" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("Territory");
            vStringColumn.setGraphic( vRadioButton );
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "Territory" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "DISP" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("DISP");
            vStringColumn.setGraphic( vRadioButton );
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "DISP" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);

            vRadioButton = new RadioButton();
            vRadioButton.setId( "RVW" );
            vRadioButton.setToggleGroup( oToggleGroup );
            vStringColumn = new TableColumn<Type, String>("RVW");
            vStringColumn.setMinWidth( 50 );
            vStringColumn.setPrefWidth( 100 );
            vStringColumn.setMaxWidth( 200 );
            vStringColumn.setCellValueFactory( new PropertyValueFactory<Type, String>( "RVW" ) );
            vStringColumn.setGraphic( vRadioButton );
            oTableView.getColumns().add(vStringColumn);
        }
        return oTableView;
    }

    public ToggleGroup getToggleGroup()
    {
        return oToggleGroup;
    }

    public RadioButton getSelectedRadioButton()
    {
        return (RadioButton)oToggleGroup.getSelectedToggle();
    }
}
