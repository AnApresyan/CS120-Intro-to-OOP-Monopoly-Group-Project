public class GOTaxFree extends Square
{
    public GOTaxFree(int coordinate) 
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
        if (getCoordinate() == 4)
        {
            System.out.println("Do you wanna pay $200 OR 10% of your wealth?");
            // if 1
                activePlayer.receiveMoney(-200);
            // if 2
                // activePlayer.setMoney((int)(activePlayer.getMoney() * 0.9));
        }
        else if (getCoordinate() == 38)
        {
            activePlayer.receiveMoney(-75);
        }
    }
    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return null;
    }
}
