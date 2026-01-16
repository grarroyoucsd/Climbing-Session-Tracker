public class Climb
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