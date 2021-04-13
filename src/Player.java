import java.util.ArrayList;

public class Player
{
    private String              name;
    private int                 money;
    private int                 coordinate;
    private int                 index;
    public ArrayList<Buyable>   belongings = new ArrayList<>(); //made it public for testing!
    private boolean             getouttajail;

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

    public boolean doesOwnAllProps(Buyable property)
    {
        return Buyable.COLORS[ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS)].length == this.ownsOfThisColor(property) ? true : false;
    }

    public int  ownsOfThisColor(Buyable property)
    {
        int     props;
        int[]   owned = new int[this.belongings.size()];

        // create an array of ints (coordinates) representing the belongings of a player
        for (int x = 0; x < this.belongings.size(); x++)
            owned[x] = this.belongings.get(x).getCoordinate();
        props = 0;
        
        int colorIndex = ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS);
        for (int i = 0; i < Buyable.COLORS[colorIndex].length; i++)
        if (ft_searchintinarray(Buyable.COLORS[colorIndex][i], owned))
            props++;
        return (props);
    }

    public static boolean  ft_searchintinarray(int num, int[] nums)
    {
        for (int i = 0; i < nums.length; i++)
            if (num == nums[i])
                return (true);
        return (false);
    }

    // this is a helper for the searcher functions. returns the INDEX of the ARRAY (an element of the matrix)
    // in which the given int was found (if not found, returns -1)
    public static int      ft_searchintinmatrix(int num, int[][] nums)
    {
        for (int i = 0; i < nums.length; i++)
            if (ft_searchintinarray(num, nums[i]))
                return (i);
        return (-1);
    }


    public void buyProperty(Buyable property){
        //property.getPrice();
    };

    public void rentProperty(Buyable property){
        //proprty.getRent();
    }

    //override equals

}
