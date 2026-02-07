import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Session
{
    private String date;
    private ArrayList<Climb> climbsList;
    private int[] grades;

    public Session(String date)
    {
        this.date = date;
        climbsList = new ArrayList<>();
        grades = new int[18];
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
        try (Scanner input = new Scanner(System.in);)
        {
            while (true)
            {
                System.out.println("Enter name of climb or 'f' to finish: ");
                String name = input.nextLine();
                if (name.equals("f"))
                {
                    break;
                }
                
                int grade;
                while (true)
                {
                    System.out.println("Enter grade (0-17): ");
                    if (input.hasNextInt())
                    {
                        grade = input.nextInt();
                        input.nextLine();
                        if (grade >= 0 && grade <= 17)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Grade must be from 0-17");
                        }
                    }
                    else
                    {
                        System.out.println("Enter a number from 0-17");
                        input.nextLine();
                    }
                }

                int attempts;
                while (true)
                {
                    System.out.println("Enter number of attempts: ");
                    if (input.hasNextInt())
                    {
                        attempts = input.nextInt();
                        input.nextLine();
                        if (attempts >= 0)
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("Number of attempts must be positive");
                        }
                    }
                    else
                    {
                        System.out.println("Enter a number");
                        input.nextLine();
                    }
                }

                // Work on!!!
                System.out.println("Did you send the climb? (yes/no) ");
                String sentYesNo = input.nextLine();
                boolean sent = false;
                if (sentYesNo.equals("yes"))
                {
                    sent = true;
                    ++grades[grade];
                }

                Climb climb = new Climb(name, grade, attempts, sent);
                climbsList.add(climb);
            }
        }

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

    public class protected Climb
    {

        private String name;
        private int grade;
        private int attempts;
        private boolean sent;

        public Climb(String name, int grade, int attempts, boolean sent)
        {
            this.name = name;
            this.grade = grade;
            this.attempts = attempts;
            this.sent = sent;
        }

        @Override
        public String toString()
        {
            String status = (sent) ? "Sent ✅" : "In Progress...";
            return "Name: " + name + ", Grade: V" + grade +
                "\nAttempts: " + attempts + " Status: " + status; 
        }
    }

    @Override
    public String toString()
    {
        String climbsListStr = "";
        for (int i = 0; i < climbsList.size(); ++i)
        {
            climbsListStr += "\n" + climbsList.get(i);
        }
        return date + climbsListStr;
    }

    public static void main(String[] args)
    {
        try(Scanner input = new Scanner(System.in);)
        {
            System.out.println("Enter date of session: ");
            String date = input.nextLine();
            Session sesh = new Session(date);

            sesh.askForClimbs();
        }
    }
}
