public class Utility extends Buyable
{
    private static int diceValue;

    public Utility(int coordinate, String title)
    {
        super(coordinate);
        if (coordinate % 5 == 0)
            setPrice(200);
        else
            setPrice(100);
        setTitle(title);
    }

    public int getRent() 
    {
        if (this.getOwner().ownsOfThisColor(this) == 1)
            return (diceValue * 4);
        return (diceValue * 10);
    }

    public static int setDice(int dice)
    {
        diceValue = dice;
        return (diceValue);
    }

    public String toString()
    {
        return (getCoordinate() + ": " + getTitle() + ". Mortgaged: " + isMortgaged() + ". Owner: (" + getOwner() + ")");
    }
}
