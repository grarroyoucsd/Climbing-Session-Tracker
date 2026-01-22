import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Session
{
    private String date;
    private Climb[] climbsList;
    private int[] grades;

    public Session(String date)
    {
        this.date = date;
        climbsList = new Climb[0];
        grades = new int[15];
    }

    public void addClimb(Climb climb)
    {
        Climb[] temp = new Climb[climbsList.length + 1];
        for (int i = 0; i < climbsList.length; ++i)
        {
            temp[i] = climbsList[i];
        }
        temp[temp.length - 1] = climb;
        climbsList = temp;
    }

    public void finishSession() throws IOException
    {
        
        try (java.io.PrintWriter output = new java.io.PrintWriter(
            new FileWriter("ClimbingHistory.txt", true));)
        {
            output.print(toString() + "\n\n");
        }
    }

    public void askForClimbs()
    {
        Scanner input = new Scanner(System.in);

        while (true)
        {
            System.out.println("Enter name of climb or 'f' to finish: ");
            String name = input.nextLine();
            if (name.equals("f"))
            {
                break;
            }

            System.out.println("Enter grade: ");
            int grade = input.nextInt();
            input.nextLine();

            System.out.println("Enter # of attempts: ");
            int attempts = input.nextInt();
            input.nextLine();

            System.out.println("Did you send the climb? (yes/no) ");
            String sentYesNo = input.nextLine();
            boolean sent = false;
            if (sentYesNo.equals("yes"))
            {
                sent = true;
                ++grades[grade];
            }

            Climb climb = new Climb(name, grade, attempts, sent);
            addClimb(climb);
        }

        input.close();

        try
        {
            finishSession();
            updateGradeDistribution();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateGradeDistribution() throws FileNotFoundException, IOException
    {
        File GD = new File("GradeDistribution.txt");

        int[] prevGrades = new int[grades.length];
        try (Scanner input = new Scanner(GD);)
        {
            for (int i = 0; i < grades.length; ++i)
            {
                String line = input.nextLine();
                String num = line.substring(line.indexOf(" ") + 1);
                prevGrades[i] = Integer.parseInt(num);
            }
        }

        try(java.io.PrintWriter output = new java.io.PrintWriter(GD);)
        {
            for (int i = 0; i < grades.length; ++i)
            {
                output.print("V" + i + ": " + (grades[i] + prevGrades[i]) + "\n");
            }
        }
    }

    @Override
    public String toString()
    {
        String climbsListStr = "";
        for (int i = 0; i < climbsList.length; ++i)
        {
            climbsListStr += "\n" + climbsList[i];
        }
        return date + climbsListStr;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter date of session: ");
        String date = input.nextLine();
        Session sesh = new Session(date);

        sesh.askForClimbs();

        input.close();
    }
}
