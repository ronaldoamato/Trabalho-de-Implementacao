package sync;

public class NoProcessException extends Exception
{
    public NoProcessException()
    {
        System.out.println("No process available");
    }

}
