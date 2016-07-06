
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class TestCommand
{
    public static void main(String[] args)
    {
        String command = "cat /etc/passwd | grep nathaniel";

        try
        {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while(reader.ready())
            {
                System.out.println(reader.readLine());
            }
        }
        catch(IOException|InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
