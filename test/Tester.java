
import java.util.function.Predicate;
import java.util.regex.PatternSyntaxException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import type.Type;
import type.Types;

public class Tester extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    public void start(Stage pMainStage)
    {
        Group vGroup = new Group();
        Scene vScene = new Scene( vGroup, 800, 500 );
        BorderPane vBorderPane = new BorderPane();
        TextField filterField = new TextField();

        Types vTypes = new Types();
        vTypes.setResourceFile( "csxmig_database.typ" );
        vTypes.load();

        filterField.textProperty().addListener( new ChangeListener<String>()
        {
            public void changed( ObservableValue<? extends String> observableValue, String oldValue, String newValue )
            {
                vTypes.getFilteredList().setPredicate(new Predicate<Type>()
                {
                    public boolean test( Type type )
                    {
                        if( newValue == null || newValue.isEmpty() )
                        {
                            return true;
                        }

                        String filterText = newValue.toLowerCase();

                        try
                        {
                            if(vTypes.getSelectedRadioButton().getId().equals("Name"))
                            {
                                if(type.getName().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("Category"))
                            {
                                if(type.getCategory().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("Flags"))
                            {
                                if(type.getFlags().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("STPL"))
                            {
                                if(type.getSTPL().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("Abbreviation"))
                            {
                                if(type.getAbbreviation().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("ExternalName"))
                            {
                                if(type.getExternalName().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("Format"))
                            {
                                if(type.getFormat().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("FormatString"))
                            {
                                if(type.getFormatString().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("AlternateName"))
                            {
                                if(type.getAlternateName().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("Group"))
                            {
                                if(type.getGroup().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("UPD"))
                            {
                                if(type.getUPD().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("Territory"))
                            {
                                if(type.getTerritory().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("DISP"))
                            {
                                if(type.getDISP().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                            else if(vTypes.getSelectedRadioButton().getId().equals("RVW"))
                            {
                                if(type.getRVW().toLowerCase().contains(filterText))
                                {
                                    return true;
                                }
                            }
                        }
                        catch(PatternSyntaxException pPatternSyntaxException)
                        {
                            return false;
                        }

                        return false;
                    }
                });
            }
        } );

        vBorderPane.setTop( filterField );
        vBorderPane.setCenter( vTypes.getTableView() );
        vBorderPane.prefHeightProperty().bind(vScene.heightProperty());
        vBorderPane.prefWidthProperty().bind(vScene.widthProperty());

        vGroup.getChildren().add(vBorderPane);

        pMainStage.setScene( vScene );
        pMainStage.setTitle( "Tester App" );
        pMainStage.show();
    }

    /*public static void logMessage( String message )
    {
        System.out.println( message  );
    }*/
}
