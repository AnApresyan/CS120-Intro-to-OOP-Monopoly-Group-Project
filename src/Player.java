import java.util.ArrayList;
import java.util.Scanner;

public class Player
{
    private String              name;
    private int                 money;
    private int                 coordinate;
    private ArrayList<Buyable>  belongings = new ArrayList<>();
    private boolean             getOutOfJail;
    private int                 daysInJail;
    private boolean             isPrisoned;
    private int[]               dice = new int[2];
    
    static Scanner input = new Scanner(System.in);

    public Player(String name)
    {
        setName(name);
        this.coordinate = 0;
        this.daysInJail = 1;
        this.money = 1500;
    }

    public int throwDice()
    {
        this.dice[0] = (int)(Math.random() * 6) + 1;
        this.dice[1] = (int)(Math.random() * 6) + 1;
        System.out.println("Dice value: " + this.dice[0] + this.dice[1]);       //for testing purposes
        return (this.dice[0] + this.dice[1]);
    }

    public boolean holdsDoubles()
    {
        return (this.dice[0] == this.dice[1]);
    }

    public void movePlayer(int move)
    {
        if (!(this.isPrisoned))
        {
            if ((this.coordinate + move) > 39)
            {
                this.money += 200;
                System.out.println("You passed the GO! Collect $200.");
            }
            setCoordinate(this.coordinate + move);
        }
    }

    public void     erectHouse()
    {
        ArrayList<Buyable>  canBeImproved = new ArrayList<>();
        System.out.println("Please enter a property coordinate to build a house on:");
        
        for (Buyable p : belongings)
        {
            // if is prop, owned of all colors, and there's no mortgagePropertyd props of this color
            if (p.getClass().getName().equals("Property") && this.doesOwnAllProps(p) && !(this.hasMortgagedPropertydInASet(p)))
                canBeImproved.add(p);
        }
        // Al: has to be severely tested before run. Looks hella buggy to me

        // >>> receiving input <<<
        int receivedInt = 1;
        // >>> received input <<<
        // Al: maybe it'll be easier to do this with a click?
        int i = 0;
        while (i < canBeImproved.size())
        {
            if (canBeImproved.get(i).getCoordinate() == receivedInt)
            {
                if (((Property)canBeImproved.get(i)).getHouses() < 5 && this.money > ((Property)canBeImproved.get(i)).getHousePrice() && this.allowedToBuild(((Property)canBeImproved.get(i))))
                    ((Property) canBeImproved.get(i)).buildHouse();
                else
                    System.out.println("Too many houses/Too little money!");
                break ;
            }
        }
    }

    public void     destroyHouse()
    {
        ArrayList<Buyable>  canBeDegraded = new ArrayList<>();
        System.out.println("Please enter a property coordinate to destroy a house on:");
        
        for (Buyable p : belongings)
        {
            // if is prop, owned of all colors, and there's no mortgagePropertyd props of this color
            if (p.getClass().getName().equals("Property") && ((Property) p).getHouses() > 0)
                canBeDegraded.add(p);
        }
        // Al: has to be severely tested before run. Looks hella buggy to me

        // >>> receiving input <<<
        int receivedInt = 1;
        // >>> received input <<<
        // Al: maybe it'll be easier to do this with a click?
        int i = 0;
        while (i < canBeDegraded.size())
        {
            if (canBeDegraded.get(i).getCoordinate() == receivedInt)
            {
                if (((Property)canBeDegraded.get(i)).getHouses() >= 1 && this.allowedToBuild(((Property)canBeDegraded.get(i))))
                    ((Property) canBeDegraded.get(i)).sellHouse();
                break ;
            }
        }
    }

    public boolean  hasMortgagedPropertydInASet(Buyable property)
    {
        int colorIndex;

        colorIndex = ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS);
        for (int i = 0; i < Buyable.COLORS[colorIndex].length; i++)
        {
            if (((Buyable) Board.getSquares()[Buyable.COLORS[colorIndex][i]]).isMortgaged())
                return (true);
        }
        return (false);
    }

    // Al: has to be tested before connecting to Swing
    public boolean allowedToBuild(Buyable property)
    {
        int colorIndex;
        int currentHouses;

        currentHouses = ((Property) property).getHouses();
        colorIndex = ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS);
        for (int i = 0; i < Buyable.COLORS[colorIndex].length; i++)
        {
            if (Math.abs(currentHouses - ((Property) Board.getSquares()[Buyable.COLORS[colorIndex][i]]).getHouses()) > 0)
                return (false);
        }
        return (true);
    }

    // TESTED by An & Al
    public boolean doesOwnAllProps(Buyable property)
    {
        return Buyable.COLORS[ft_searchintinmatrix(property.getCoordinate(), Buyable.COLORS)].length == this.ownsOfThisColor(property) ? true : false;
    }

    // TESTED by An & Al
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
    
    // TESTED by Al
    public int ownsUnmortgagedProps()
    {
        int res;

        res = 0;
        for (int i = 0; i < belongings.size(); i++)
            if (!(belongings.get(i).isMortgaged()))
                res++;
        return (res);
    }

    public static boolean  ft_searchintinarray(int num, int[] nums)
    {
        for (int i = 0; i < nums.length; i++)
            if (num == nums[i])
                return (true);
        return (false);
    }

    // Al: this is a helper for the searcher functions. returns the INDEX of the ARRAY (an element of the matrix)
    // in which the given int was found (if not found, returns -1)
    public static int      ft_searchintinmatrix(int num, int[][] nums)
    {
        for (int i = 0; i < nums.length; i++)
            if (ft_searchintinarray(num, nums[i]))
                return (i);
        return (-1);
    }

    // TESTED by Al
    public void buyProperty(Buyable property)
    {
        //Prompt that it's gonna buy it
        if (this.money > property.getPrice())
        {
            System.out.println("You can buy " + property.getTitle() + " (price: " + property.getPrice() + "). Do you want to buy it? YES/NO");
            // while (true)
            // {            
                // >>> receiving response <<<
                String response = "YES";
                // >>> received response <<<
                if (response.equals("YES"))
                {
                    System.out.println("Bought a property.");
                    this.money -= property.getPrice();
                    property.setOwner(this);
                    this.belongings.add(property);
                    // break ;
                }
                else if (response.equals("NO"))
                {
                    System.out.println("Auction commenced.");
                    // do auction
                    // break ;
                }
            // }
        }
        else
        {
            System.out.println("Auction commenced.");
            // do auction
        }
    };

    // TESTED by Al
    public void rentProperty(Buyable property)
    {
        this.money -= property.getRent();
        enterMortgageLoop(property.getOwner());
    }

    // TESTED by Al
    public void enterMortgageLoop(Player owner)
    {
        if (this.money < 0)
            System.out.println("Your balance is negative. Entered a mortgage loop.");
        while (this.money < 0)
        {
            if (ownsUnmortgagedProps() > 0)
            {
                System.out.println("Please select a prop to mortgage:");
                for (Buyable belonging : belongings)
                    if (canBeMortgaged(belonging))
                        System.out.println(belonging);
                System.out.println("Or an improved property to degrade:");
                for (Buyable belonging : belongings)
                    if (canBeDegraded(belonging))
                        System.out.println(belonging);
            }
            else
            {
                System.out.println("Unfortunately, " + this.name + ", you lost.");
                if (!(owner == null))
                {
                    for (Buyable b : belongings)
                    {
                        owner.belongings.add(b);
                        b.setOwner(owner);
                    }
                }
                removePlayer(this);
                break ;
            }

            int coord;
            // >>> receiving input <<<
            coord = 9;
            // >>> received input <<<
            if (canBeMortgaged(coord))
                mortgageProperty(coord);
            else if (canBeDegraded(coord))
                degradeProperty(coord);
            // else if (TRADE button pressed)
            //     initializeTrade();
            else
                System.out.println("Cannot be mortgaged/degraded.");
            
            if (this.money >= 0)
                System.out.println("You have managed to pay your debt off.");
        }
    }

    // TESTED by Al
    public void removePlayer(Player player)
    {
        for (Player p : Monopoly.getPlayers())
            if (p.equals(player))
                Monopoly.getPlayers().remove(p);
    }

    // TESTED by Al
    public void mortgageProperty(int coord)
    {
        for (Buyable belonging : belongings)
        {
            if (belonging.getCoordinate() == coord)
            {
                this.money += (belonging.getPrice() / 2);
                belonging.setIsMortgaged(true);
            }
        }
        System.out.println("Mortgaged a property under coord " + coord);
    }

    // TESTED by Al
    public void degradeProperty(int coord)
    {
        for (Buyable belonging : belongings)
        {
            if (belonging.getCoordinate() == coord && canBeDegraded(belonging))
            {
                System.out.println(belonging + " has been degraded.");
                ((Property) belonging).sellHouse();
            }
        }
    }

    // TESTED by Al
    public void receiveMoney(int mon)
    {
        this.money += mon;
    }

    // TESTED by Al
    public boolean canBeDegraded(Buyable belonging)
    {
        return (belonging.getClass().getName().equals("Property") && ((Property)belonging).getHouses() >= 1);
    }

    // TESTED by Al
    public boolean canBeDegraded(int coordinate)
    {
        for (Buyable belonging : belongings)
        {
            if (belonging.getCoordinate() == coordinate && canBeDegraded(belonging))
                return (true);
        }
        return (false);
    }

    // TESTED by An
    public boolean canBeMortgaged(Buyable belonging)
    {
        if (!belonging.isMortgaged())
        {
            if (belonging.getClass().getName() != "Property" || (belonging.getClass().getName() == "Property" && !((Property)belonging).isImproved()))
                return (true);
        }
        return (false);
    }

    // TESTED by An
    public boolean canBeMortgaged(int coordinate)
    {
        for (Buyable belonging : belongings)
        {
            if (belonging.getCoordinate() == coordinate && canBeMortgaged(belonging))
                return (true);
        }
        return (false);
    }

    // TESTED by An
    public boolean equals(Object obj)
    {
        if (obj == null)
            return (false);
        else if (this.getClass() != obj.getClass())
            return (false);
        else
        {
            Player second = (Player) obj;
            return (this.name.equalsIgnoreCase(second.name));
            // An: won't allow two players to have the same name, checked by SameNameException // Al: cool!
        }
    }

    public ArrayList<Buyable> getBelongings()
    {
        return (this.belongings);
    }

    public String getName()
    {
        return (this.name);
    }

    public int getCoordinate()
    {
        return (this.coordinate);
    }

    public int getMoney()
    {
        return (this.money);
    }

    public boolean isPrisoned()
    {
        return (this.isPrisoned);
    }

    public boolean getGetOutOfJail()
    {
        return (this.getOutOfJail);
    }
    
    public int getDaysInJail()
    {
        return (this.daysInJail);
    }
    
    public int getDice()
    {
        return (this.dice[0] + this.dice[1]);
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setIsPrisoned(boolean bool)
    {
        this.isPrisoned = bool;
    }

    public void setGetOutOfJail(boolean bool)
    {
        this.getOutOfJail = bool;
    }
    
    public void setCoordinate(int coordinate)
    {
        if (coordinate > 39)
            this.coordinate = coordinate - 40;
        else
            this.coordinate = coordinate;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }

    public void setDaysInJail(int daysInJail)
    {
        this.daysInJail = daysInJail;
    }

    public String toString(){
        return "Player: " + this.name + "   Money: " + this.money + "   Coordinate: " + this.coordinate;
    }

}
