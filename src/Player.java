import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Player
{
    private String              name;
    private int                 money;
    private int                 coordinate;
    private int                 index;
    private ArrayList<Buyable>  belongings = new ArrayList<>();
    private boolean             getOutOfJail;
    private int                 daysInJail;
    private boolean             isPrisoned;
    private int[]               dice = new int[2];
    
    static Scanner input = new Scanner(System.in);

    public Player(String name, int index)
    {
        setName(name);
        this.index = index;
        this.coordinate = 1;
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

    public int getIndex()
    {
        return (this.index);
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
    public void movePlayer()
    {

        if (!(this.isPrisoned && this.coordinate == 10))
        {
            if ((this.coordinate + this.dice[0] + this.dice[1]) > 39)
            {
                this.coordinate = this.coordinate + this.dice[0] + this.dice[1] - 40;
                this.money += 200;
            }
            else
                this.coordinate = this.coordinate + this.dice[0] + this.dice[1];
        }
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
            while(!done){
                try{
                    System.out.println("Please type the coordinate of the property you want to mortgage");
                    coord = input.nextInt();
                    done = true;
                    if (!canBeMortgaged(coord)){
                        System.out.println("Not yours or cannot be mortgaged");
                        done = false;
                    } 
                }
                catch(InputMismatchException e){
                    input.nextLine();
                    System.out.println("Not a number, please try again");
                }
            }
            this.mortgage(coord);
        }

    }

    public void mortgage(int coord){
        for (Buyable belonging : belongings){
            if (belonging.getCoordinate() == coord){
                this.money += belonging.getPrice();
                belonging.setIsMortgaged(true);
            }
        }
    }

    // public boolean isYourProperty(int coordinate){
    //     for (Buyable belonging : belongings){
    //         if (belonging.getCoordinate() == coordinate)
    //             return true;
    //     }
    //     return false;
    // }


    public boolean canBeMortgaged(int coordinate){
        for (Buyable belonging : belongings){
            if (belonging.getCoordinate() == coordinate && canBeMortgaged(belonging))
                return true;
        }
        return false;
    }

    public boolean canBeMortgaged(Buyable belonging){
        if (!belonging.isMortgaged()){
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
