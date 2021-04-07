public class Player
{
    private String  name;
    private int     money;
    private int     coordinates;
    private boolean getouttajail;

    public Player(String name)
    {
        setName(name);
        this.coordinates = 1;
        this.getouttajail = false;
    }

    public void setName(String name){
        this.name = name;
    }
    public void movePlayer(int dice)
    {
        if ((this.coordinates + dice) > 40)
            this.coordinates = this.coordinates + dice - 40;
        else
            this.coordinates = this.coordinates + dice;
    }


    // public static void main(String[] args){
    //     Player first = new Player("Anahit");
        
    //     first.movePlayer(10);
    //     System.out.println(first.coordinates);
    //     first.movePlayer(30);
    //     System.out.println(first.coordinates);
    // }
}
