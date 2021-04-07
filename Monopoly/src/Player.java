public class Player
{
    String  name;
    int     money;
    int     coordinates;
    boolean getouttajail;

    public Player(String name)
    {
        setName(name);
    }
    
    public void setName(String name){
        this.name = name;
    }
}
