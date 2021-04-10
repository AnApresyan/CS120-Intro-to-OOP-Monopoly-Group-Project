import java.util.ArrayList;

public class Player
{
    private String  name;
    private int     money;
    private int     coordinate;
    private int     index;
    private ArrayList<Square> belongings = new ArrayList<Square>();
    private boolean getouttajail;

    public Player(String name, int index)
    {
        setName(name);
        this.index = index;
        this.coordinate = 1;
        this.getouttajail = false;
    }

    public void setName(String name){
        this.name = name;
    }
    public void movePlayer(int[] dice)
    {
        //check if in jail or not
        if ((this.coordinate + dice[0] + dice[1]) > 39)
            this.coordinate = this.coordinate + dice[0] + dice[1] - 39;
        else
            this.coordinate = this.coordinate + dice[0]  + dice[1];
    }

    public int getCoordinate(){
        return this.coordinate;
    }

    // public static void main(String[] args){
    //     Player first = new Player("Anahit");
        
    //     first.movePlayer(10);
    //     System.out.println(first.coordinates);
    //     first.movePlayer(30);
    //     System.out.println(first.coordinates);
    // }
}
