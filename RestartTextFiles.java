import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestartTextFiles
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Are you sure you want to restart the File? Enter 'restartfiles' to confirm: ");
        String statement = input.next();
        input.close();
        if (statement.equals("restartfiles"))
        {
            try(java.io.PrintWriter output = new java.io.PrintWriter(new File("ClimbingHistory.txt"));)
            {}
            catch(FileNotFoundException e)
            {
                System.out.println(e.getMessage());
            }

            try(java.io.PrintWriter output = new java.io.PrintWriter(new File("GradeDistribution.txt"));)
            {
                for (int i = 0; i < 18; ++i)
                {
                    output.print("V" + i + ": 0\n");
                }
            }
            catch(FileNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }
}