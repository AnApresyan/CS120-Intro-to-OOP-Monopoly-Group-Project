public class Property extends Buyable
{
    private int     houses;
    private int[]   rents;
    private int     housePrice;

    public Property(int coordinate, String title, int price, int[] rents, int housePrice)
    {
        super(coordinate);
        this.rents = rents;
        this.housePrice = housePrice;
        setPrice(price);
        setTitle(title);
    }

    public boolean  isImproved()
    {
        if (this.getOwner().doesOwnAllProps(this))
        {
            int colorIndex = Player.ft_searchintinmatrix(this.getCoordinate(), Buyable.COLORS);
            for (int propertyCord : Buyable.COLORS[colorIndex])
                for (Buyable property : this.getOwner().getBelongings())
                    if (property.getCoordinate() == propertyCord && ((Property) property).getHouses() >= 1)
                        return (true);
        }
        return (false);
    }

    public int      getRent()
    {
        if (this.isMortgaged())
            return (0);
        if (this.getOwner() != null)
        {
            if (this.houses == 0 & this.getOwner().doesOwnAllProps(this))
            return (2 * this.rents[0]);
        }
        return (this.rents[this.houses]);
    }

    public void     buildHouse()
    {
        if (this.houses <= 4)
        {
            this.houses += 1;
            this.getOwner().receiveMoney(-this.housePrice);
        }
    }

    public void     sellHouse()
    {
        if (houses >= 1)
        {
            this.houses -= 1;
            this.getOwner().receiveMoney(this.housePrice / 2);
        }
    }

    public boolean  canBeDegraded()
    {
        if (houses > 0 && this.getOwner().allowedToDegrade(this))
            return (true);
        return (false);
    }

    public boolean  canBeImproved()
    {
        if (this.getOwner() == null)
            return (false);
        if (this.getOwner().doesOwnAllProps(this) && !(this.getOwner().hasMortgagedPropertydInASet(this)) && houses < 5 && this.getOwner().allowedToBuild(this))
            return (true);
        return (false);
    }

    public int      getHouses()
    {
        return (this.houses);
    }

    public int      getHousePrice()
    {
        return (this.housePrice);
    }

    public void     setHouses(int houses)
    {
        this.houses = houses;
    }

    public String   toString()
    {
        return (getCoordinate() + ": " + getTitle() + ". Houses: " + houses + ". Mortgaged? " + isMortgaged());
    }
}