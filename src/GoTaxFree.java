public class GoTaxFree extends Square
{
    public GoTaxFree(int coordinate) 
    {
        super(coordinate);
        if (coordinate == 0)
            setTitle("GO");
        else if (coordinate == 4)
            setTitle("Income Tax");
        else if (coordinate == 20)
            setTitle("Free Parking");
        else if (coordinate == 38)
            setTitle("Luxury Tax");
    }
    public void doAction(Player activePlayer)
    {
        
    }

    
    // public int getReward()
    // {

    // }
}
