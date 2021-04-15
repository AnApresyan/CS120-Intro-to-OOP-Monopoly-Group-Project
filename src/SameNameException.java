public class SameNameException extends Exception 
{
    public SameNameException(){
        super("The name is empty or there is already a player with that same name, please pick another one");
    }
}
