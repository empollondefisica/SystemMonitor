
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.attribute.UserPrincipal;

public class Test
{
<<<<<<< HEAD


=======
>>>>>>> 4940bfffc2e5b7a2906d0769826928f399281d75
    public static void main( String[] args )
    {
        File file = new File( "/proc" );
        System.out.println( file.getAbsoluteFile() );
        File tempFile = null;
        File statusFile = null;

        for( String name : file.list() )
        {
            if( name.matches( "^\\d+$" ) )
            {
                tempFile = new File( file.getAbsoluteFile() + "/" + name );
                System.out.println( tempFile.isDirectory() + " " + tempFile.getAbsoluteFile() );
                statusFile = new File( tempFile.getAbsoluteFile() + "/status" );
                System.out.println( statusFile.getAbsoluteFile() );

                try
                {
                    Path path = Paths.get( statusFile.getAbsoluteFile().toString() );
                    UserPrincipal owner = Files.getOwner( path );
                    String username = owner.getName();
                    System.out.println( "Owner: " + username );
                }
                catch( IOException ioe )
                {
                    System.out.println( ioe.getMessage() );
                }
                //Process proc = new Process( readProcFile( statusFile ) );
                //System.out.println( proc.toString() );
            }
        }
    }

    public static String readProcFile( File procFile )
    {
        StringBuilder fileText = new StringBuilder();

        try
        {
            InputStreamReader isr = new InputStreamReader( new FileInputStream( procFile ) );
            char ch;
            int data = isr.read();
            while( data != -1 )
            {
                ch = (char)data;
                fileText.append( ch );
                data = isr.read();
            }
        }
        catch( IOException exception )
        {
            System.out.println( exception.getMessage() );
        }

        return fileText.toString();
    }
}
