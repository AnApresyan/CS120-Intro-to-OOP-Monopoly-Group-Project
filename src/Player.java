import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Player
{
    private String              name;
    private int                 money;
    private int                 coordinate;
    private int                 index;
    private  ArrayList<Buyable>  belongings = new ArrayList<>(); ////made it public for testing!
    private boolean             getouttajail;
    
    static Scanner input = new Scanner(System.in);

    public Player(String name, int index)
    {
        setName(name);
        this.index = index;
        this.coordinate = 1;
        this.getouttajail = false;
    }

    public ArrayList<Buyable> getBelongings(){                  //privacy leak?
        return this.belongings;
    }
    public String getName(){
        return this.name;
    }

    public int getIndex(){
        return this.index;
    }
    public void setName(String name){
        this.name = name;
    }
    public void movePlayer(int[] dice)
    {
        //check if in jail or not
        if ((this.coordinate + dice[0] + dice[1]) > 39)
            this.coordinate = this.coordinate + dice[0] + dice[1] - 39;
        else
            this.coordinate = this.coordinate + dice[0]  + dice[1];
    }

    public int getCoordinate(){
        return this.coordinate;
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


    public void buyProperty(Buyable property){
        //Prompt that it's gonna buy it
        if (this.money > property.getPrice()){
            //you bought it
            this.money -= property.getPrice();
            property.setOwner(this);
            this.belongings.add(property);
        }
        else if (this.money < property.getPrice()){
            //going bakrupt

            /**
             * if the player cannot afford the property, it's auctioned. while the latter is not implemented,
             * we shouldn't do anything else in this if.
             */
        }
        else{
            //if this.money == property.getprice();

            /**
             * this has to be included in the opening if statement. the player is playing as long as their
             * balance is a POSITIVE number AND they have unmortgaged properties.
             */
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
                belonging.setisMortgaged(true);
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
