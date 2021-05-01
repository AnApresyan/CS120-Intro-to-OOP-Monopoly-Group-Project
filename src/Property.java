public class Property extends Buyable
{
    private int     houses;
    private int[]   rents;
    private int     housePrice;

    public Property(int coordinate, String title, int price, int[] rents, int housePrice)
    {
        super(coordinate);
        setPrice(price);
        this.rents = rents;
        this.housePrice = housePrice;
        setTitle(title);
    }

    public boolean isImproved()
    {
        if (this.getOwner().doesOwnAllProps(this))
        {
            int colorIndex = Player.ft_searchintinmatrix(this.getCoordinate(), Buyable.COLORS);
            for (int propertyCord : Buyable.COLORS[colorIndex])
            {
                for (Buyable property : this.getOwner().getBelongings())
                {
                    if (property.getCoordinate() == propertyCord && ((Property) property).getHouses() >= 1)
                        return (true);
                }
                
            }
        }
        return (false);
    }



    public int getRent()
    {
        if (isMortgaged())
            return (0);
        if (this.getOwner() != null)
        {
            if (houses == 0 & getOwner().doesOwnAllProps(this))
            return (2 * rents[0]);
        }
        return (this.rents[houses]);
    }

    public void buildHouse()
    {
        if (houses <= 4)
        {
            this.houses += 1;
            this.getOwner().receiveMoney(-housePrice);
            System.out.println("Bought a home");
        }
    }

    public void sellHouse()
    {
        if (houses >= 1)
        {
            this.houses -= 1;
            this.getOwner().receiveMoney(housePrice / 2);
            System.out.println("Sold a home");
        }
    }

    public int getHouses()
    {
        return (houses);
    }

    public void setHouses(int houses)
    {
        this.houses = houses;
    }

    public int  getHousePrice()
    {
        return (housePrice);
    }

    public boolean canBeImproved()
    {                                                     //JUST ADDED
        if (this.getOwner() == null)
            return (false);
        if (this.getOwner().doesOwnAllProps(this) && !(this.getOwner().hasMortgagedPropertydInASet(this)) && houses < 5)
            return (true);
        return (false);
    }

    public String toString()
    {
        return (getCoordinate() + ": " + getTitle() + ". Number of houses: " + houses + ". Mortgaged: " + isMortgaged() + ". Owner: (" + getOwner() + ")");
    }
}