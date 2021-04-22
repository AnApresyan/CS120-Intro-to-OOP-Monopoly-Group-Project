import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

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
    }

    public ArrayList<Buyable> getBelongings()
    {                  //privacy leak?
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

    public boolean getIsPrisoned()
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

    public int throwDice()
    {
        this.dice[0] = (int)(Math.random() * 6) + 1;
        this.dice[1] = (int)(Math.random() * 6) + 1;
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
            // if is prop, owned of all colors, and there's no mortgaged props of this color
            if (p.getClass().getName().equals("Property") && this.doesOwnAllProps(p) && !(this.hasMortgagedInASet(p)))
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
            // if is prop, owned of all colors, and there's no mortgaged props of this color
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

    public boolean  hasMortgagedInASet(Buyable property)
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


    public void buyProperty(Buyable property)
    {
        //Prompt that it's gonna buy it
        if (this.money > property.getPrice()){
            String  response;
            Scanner input;
            //you bought it
            System.out.println("You've landed on " + property.getTitle() + " (price: " + property.getPrice() + "). Do you want to buy it? YES/NO");
            input = new Scanner(System.in);
            response = input.next();
            while (true)
            {
                if (response.equals("YES"))
                {
                    this.money -= property.getPrice();
                    property.setOwner(this);
                    this.belongings.add(property);
                    input.close();
                    break ;
                }
                else if (response.equals("NO"))
                {
                    // do auction
                    input.close();
                    break ;
                }
                else
                    System.out.println("Please input either YES or NO.");
                // check for mismatched input!
            }

        }
    };

    public void rentProperty(Buyable property){
        //Prompt: you rent the house
        this.money -= property.getRent();
        while (this.money < 0){   
            if (this.belongings.size() > 0){
                for (Buyable belonging : belongings){
                    if (canBeMortgaged(belonging))
                        System.out.println(belonging.toString());
                }
            }
            else{
                System.out.println("You lost");
                //outtaGame();
                break;
            }
            boolean done = false;
            int coord = 0;
            while(!done)
            {
                try
                {
                    System.out.println("Please type the coordinate of the property you want to mortgage");
                    coord = input.nextInt();
                    done = true;
                    if (!canBeMortgaged(coord))
                    {
                        System.out.println("Not yours or cannot be mortgaged");
                        done = false;
                    } 
                }
                catch(InputMismatchException e)
                {
                    input.nextLine();
                    System.out.println("Not a number, please try again");
                }
            }
            this.mortgage(coord);
        }

    }

    public void mortgage(int coord)
    {
        for (Buyable belonging : belongings)
        {
            if (belonging.getCoordinate() == coord)
            {
                this.money += (belonging.getPrice() / 2);
                belonging.setIsMortgaged(true);
            }
        }
    }

    public void receiveMoney(int mon)
    {
        this.money += mon;
    }
    // public boolean isYourProperty(int coordinate){
    //     for (Buyable belonging : belongings){
    //         if (belonging.getCoordinate() == coordinate)
    //             return true;
    //     }
    //     return false;
    // }


    public boolean canBeMortgaged(int coordinate)
    {
        for (Buyable belonging : belongings)
        {
            if (belonging.getCoordinate() == coordinate && canBeMortgaged(belonging))
                return true;
        }
        return false;
    }

    public boolean canBeMortgaged(Buyable belonging)
    {
        if (!belonging.isMortgaged())
        {
            if (belonging.getClass().getName() != "Property" || (belonging.getClass().getName() != "Property" && !((Property)belonging).isImproved()))
                return true;
        }
        return false;
    }


    public boolean equals(Object obj){
        if (obj == null)
            return false;
        else if (this.getClass() != obj.getClass())
            return false;
        else{
            Player second = (Player) obj;
            return this.name.equalsIgnoreCase(second.name);     //won't allow two players to have the same name, checked by SameNameException //// cool!
        }
    }

}
