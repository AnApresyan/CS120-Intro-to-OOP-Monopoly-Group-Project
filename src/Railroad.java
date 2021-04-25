public class Railroad extends Buyable 
{
    public Railroad(int coordinate, String title)
    {
        super(coordinate);
        setTitle(title);
        setPrice(200);
    }

    public int getRent()
    {
        if (this.getOwner() != null)
            return (25 * (int)Math.pow(2, this.getOwner().ownsOfThisColor(this) - 1));
        return (0);
    }

    public String toString()
    {
        return (getCoordinate() + ": " + getTitle() + ". Mortgaged: " + isMortgaged() + ". Owner: (" + getOwner() + ")");
    }
}
