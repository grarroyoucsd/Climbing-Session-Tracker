public class Session implements Comparable<Session>
{
    private int[] numsDate;
    private String date;
    private Climb[] climbsList;

    public Session(String date)
    {
        climbsList = new Climb[0];
        this.date = date;
        try
        {
            this.numsDate = parseDate(date);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
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

    public void finishSession()
    {
        
    }

    public int[] parseDate(String date) throws IllegalArgumentException
    {
        int[] result = new int[3];
        String[] numsDate = date.split("/");
        try 
        {
            for (int i = 0; i < 3; ++i)
            {
                result[i] = Integer.parseInt(numsDate[i]);
            }
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("Date should be in format MM/DD/YYYY");
        }

        boolean validMonth = (result[0] > 12 || result[0] < 1);
        boolean validDay = (result[0] > 31 || result[0] < 1);
        boolean validYear = (result[0] > 2050 || result[0] < 2020);

        if (!validMonth)
        {
            throw new IllegalArgumentException("Enter valid month (01-12)");
        }
        if (!validDay)
        {
            throw new IllegalArgumentException("Enter valid day (1-31)");
        }
        if (!validYear)
        {
            throw new IllegalArgumentException("Enter valid year (Ex. 2025)");
        }

        return result;
    }

    @Override
    public int compareTo(Session obj)
    {
        // Check year
        if (numsDate[2] > obj.numsDate[2])
        {
            return 1;
        }
        if (numsDate[2] < obj.numsDate[2])
        {
            return -1;
        }

        // Check month
        if (numsDate[1] > obj.numsDate[1])
        {
            return 1;
        }
        if (numsDate[1] < obj.numsDate[1])
        {
            return -1;
        }

        // Check day
        if (numsDate[0] > obj.numsDate[0])
        {
            return 1;
        }
        if (numsDate[0] < obj.numsDate[0])
        {
            return -1;
        }

        // Same date
        return 0;
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
        Climb climb1 = new Climb("Warm Me Up", 4, 1, true);
        Climb climb2 = new Climb("Biggy Smalls", 5, 1, true);
        Climb climb3 = new Climb("Rightness Pt.3", 5, 2, true);
        Climb climb4 = new Climb("Kemal's Var", 6, 1, true);
        Climb climb5 = new Climb("L'Ho Duro Duro", 7, 1, true);
        Climb climb6 = new Climb("Jordan Var", 8, 4, true);
        Climb climb7 = new Climb("Kokolo", 8, 1, true);
        Climb climb8 = new Climb("Mark's Favorite Problem", 8, 17, true);
        Climb climb9 = new Climb("Stooked", 8, 3, true);
        Climb climb10 = new Climb("Penay Colada", 8, 2, true);
        Climb climb11 = new Climb("Captain Fitzroy", 8, 3, false);

        Session mejor = new Session("02/11/2025");
        mejor.addClimb(climb1);
        mejor.addClimb(climb2);
        mejor.addClimb(climb3);
        mejor.addClimb(climb4);
        mejor.addClimb(climb5);
        mejor.addClimb(climb6);
        mejor.addClimb(climb7);
        mejor.addClimb(climb8);
        mejor.addClimb(climb9);
        mejor.addClimb(climb10);
        mejor.addClimb(climb11);

        System.out.println(mejor);
    }
}