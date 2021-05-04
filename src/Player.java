import java.util.ArrayList;

public class Player
{
    private String              name;
    private int                 money;
    private int                 coordinate;
    private ArrayList<Buyable>  belongings = new ArrayList<>();
    private boolean             getOutOfJail;
    private int                 daysInJail;
    private boolean             isPrisoned;
    private int                 doublesInARow;
    private int[]               dice = new int[2];

    public  Player(String name)
    {
        this.name = name;
        this.coordinate = 0;
        this.daysInJail = 1;
        this.money = 1500;
    }

    public boolean              state()
    {
        int sum;
        
        sum = 0;
        if (this.money >= 0)
            return (true);
        if (this.ownsUnmortgagedProps() == 0)
            return (false);
        else
        {
            for (Buyable belonging : belongings)
                if (!(belonging.isMortgaged()))
                {
                    if (belonging.getClass().getName().equals("Property") && ((Property)belonging).getHouses() > 0)
                        for (int i = 0; i < ((Property)belonging).getHouses(); i++)
                            sum += (((Property)belonging).getHousePrice() / 2);
                    sum += (belonging.getPrice() / 2);
                }                    
        }
        if ((sum + this.money) >= 0)
            return (true);
        return (false);
    }

    public int                  throwDice()
    {
        // this.dice[0] = (int)(Math.random() * 6) + 1;
        // this.dice[1] = (int)(Math.random() * 6) + 1;
        this.dice[0] = 10;
        this.dice[1] = 2;
        if (this.holdsDoubles())
            this.doublesInARow++;
        else
            this.doublesInARow = 0;
        return (this.dice[0] + this.dice[1]);
    }

    public boolean              holdsDoubles()
    {
        if (this.dice[0] == 0)
            return (false);
        return (this.dice[0] == this.dice[1]);
    }

    public void                 movePlayer(int move)
    {
        if (!(this.isPrisoned))
        {
            if ((this.coordinate + move) > 39)
                this.money += 200;
            this.setCoordinate(this.coordinate + move);
        }
    }

    public boolean              owns(int coordinate)
    {
        for (Buyable property : belongings)
            if (property.getCoordinate() == coordinate)
                return (true);
        return (false);
    }

    public void                 liftMortgage(Buyable property)
    {
        if (property.isMortgaged())
        {
            property.setIsMortgaged(false);
            receiveMoney((int)(-(property.getPrice() / 2) * 1.1));
        }
    }

    public void                 erectHouse(Property property)
    {
        if (property.canBeImproved())
            property.buildHouse();
    }

    public boolean              hasMortgagedPropertydInASet(Buyable property)
    {
        int colorIndex;

        colorIndex = ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS);
        for (int i = 0; i < Buyable.COLORS[colorIndex].length; i++)
            if (((Buyable) Board.getSquares()[Buyable.COLORS[colorIndex][i]]).isMortgaged())
                return (true);
        return (false);
    }

    public boolean              allowedToBuild(Buyable property)
    {
        int colorIndex;
        int currentHouses;

        currentHouses = ((Property) property).getHouses();
        colorIndex = ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS);
        for (int i = 0; i < Buyable.COLORS[colorIndex].length; i++)
            if (Math.abs((currentHouses + 1) - ((Property) Board.getSquares()[Buyable.COLORS[colorIndex][i]]).getHouses()) > 1)
                return (false);
        return (true);
    }

    public boolean              allowedToDegrade(Buyable property)
    {
        int colorIndex;
        int currentHouses;

        currentHouses = ((Property) property).getHouses();
        colorIndex = ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS);
        for (int i = 0; i < Buyable.COLORS[colorIndex].length; i++)
            if (Math.abs((currentHouses - 1) - ((Property) Board.getSquares()[Buyable.COLORS[colorIndex][i]]).getHouses()) > 1)
                return (false);
        return (true);
    }

    public boolean              doesOwnAllProps(Buyable property)
    {
        return (Buyable.COLORS[ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS)].length == this.ownsOfThisColor(property) ? true : false);
    }

    public int                  ownsOfThisColor(Buyable property)
    {
        int     props;
        int[]   owned;
        int     colorIndex;

        owned = new int[this.belongings.size()];
        for (int x = 0; x < this.belongings.size(); x++)
            owned[x] = this.belongings.get(x).getCoordinate();
        props = 0;
        colorIndex = ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS);
        for (int i = 0; i < Buyable.COLORS[colorIndex].length; i++)
            if (ft_searchintinarray(Buyable.COLORS[colorIndex][i], owned))
                props++;
        return (props);
    }
    
    public int                  ownsUnmortgagedProps()
    {
        int res;

        res = 0;
        for (int i = 0; i < belongings.size(); i++)
            if (!(belongings.get(i).isMortgaged()))
                res++;
        return (res);
    }

    public static boolean       ft_searchintinarray(int num, int[] nums)
    {
        for (int i = 0; i < nums.length; i++)
            if (num == nums[i])
                return (true);
        return (false);
    }

    public static int           ft_searchintinmatrix(int num, int[][] nums)
    {
        for (int i = 0; i < nums.length; i++)
            if (ft_searchintinarray(num, nums[i]))
                return (i);
        return (-1);
    }

    public void                 buyProperty(Buyable property)
    {
        if (this.money > property.getPrice())
        {
            this.money -= property.getPrice();
            property.setOwner(this);
            this.belongings.add(property);
        }
    }

    public void                 rentProperty(Buyable property)
    {
        this.money -= property.getRent();
    }

    public void                 mortgageProperty(Buyable property)
    {
        if (property.getOwner().equals(this))
            if (property.canBeMortgaged())
            {
                this.money += (property.getPrice() / 2);
                property.setIsMortgaged(true);
            }
    }

    public void                 degradeProperty(Property property)
    {
        if (property.getOwner().equals(this))
            if (property.getHouses() > 0)
                property.sellHouse();
    }

    public void                 receiveMoney(int mon)
    {
        this.money += mon;
    }

    public void                 nullifyDice()
    {
        this.dice[0] = 0;
        this.dice[1] = 0;
    }

    public ArrayList<Buyable>   getBelongings()
    {
        return (this.belongings);
    }

    public String               getName()
    {
        return (this.name);
    }

    public int                  getCoordinate()
    {
        return (this.coordinate);
    }

    public int                  getMoney()
    {
        return (this.money);
    }

    public boolean              isPrisoned()
    {
        return (this.isPrisoned);
    }

    public boolean              getGetOutOfJail()
    {
        return (this.getOutOfJail);
    }

    public int                  getDoublesInARow()
    {
        return (this.doublesInARow);
    }
    
    public int                  getDaysInJail()
    {
        return (this.daysInJail);
    }
    
    public int                  getDice()
    {
        return (this.dice[0] + this.dice[1]);
    }

    public int                  getFirstDice()
    {
        return (this.dice[0]);
    }

    public int                  getSecondDice()
    {
        return (this.dice[1]);
    }

    public void                 setName(String name)
    {
        this.name = name;
    }
    
    public void                 setIsPrisoned(boolean bool)
    {
        this.isPrisoned = bool;
    }

    public void                 setGetOutOfJail(boolean bool)
    {
        this.getOutOfJail = bool;
    }
    
    public void                 setCoordinate(int coordinate)
    {
        if (coordinate > 39)
            this.coordinate = coordinate - 40;
        else
            this.coordinate = coordinate;
    }

    public void                 setMoney(int money)
    {
        this.money = money;
    }

    public void                 setDaysInJail(int daysInJail)
    {
        this.daysInJail = daysInJail;
    }

    public void                 setDoublesInARow(int doublesInARow)
    {
        this.doublesInARow = doublesInARow;
    }

    public String               toString()
    {
        return ("Player: " + this.name + " Balance: " + this.money + " Coordinate: " + this.coordinate + " Card: " + this.getGetOutOfJail());
    }
    
    public boolean              equals(Object obj)
    {
        if (obj == null)
            return (false);
        else if (this.getClass() != obj.getClass())
            return (false);
        else
        {
            Player second = (Player) obj;
            return (this.name.equalsIgnoreCase(second.name));
        }
    }
}
