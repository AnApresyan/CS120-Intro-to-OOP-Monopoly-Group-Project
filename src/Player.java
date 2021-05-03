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
    private int                 doublesInARow;
    private int[]               dice = new int[2];
    
    static Scanner input = new Scanner(System.in);

    public Player(String name)
    {
        setName(name);
        this.coordinate = 0;
        this.daysInJail = 1;
        this.money = 1500;        //1500  
                // Al: FOR TESTING

        
    }

    public boolean state(){
        int sum = 0;
        if (this.money >= 0)
            return true;
        if (this.ownsUnmortgagedProps() == 0)
            return false;
        else{
            for (Buyable belonging : belongings){
                if (!belonging.isMortgaged()){
                    if (belonging.getClass().getName().equals("Property") && ((Property)belonging).getHouses() > 0){
                        for (int i = 0; i < ((Property)belonging).getHouses(); i++)
                            sum += (((Property)belonging).getHousePrice() / 2);
                    }
                    sum += (belonging.getPrice() / 2);
                 }                    
            }
        }

        if ((sum + this.money) >= 0)
            return true;
        return false;

    }

    public int throwDice()
    {
        this.dice[0] = (int)(Math.random() * 6) + 1;
        this.dice[1] = (int)(Math.random() * 6) + 1;
        // this.dice[0] = 0;
        // this.dice[1] = 30;

        if (holdsDoubles())
            this.doublesInARow++;
        else
            this.doublesInARow = 0;
        
        

        System.out.println("Dice roll: " + this.dice[0] + ", " +  this.dice[1]);        // An: for testing
        return (this.dice[0] + this.dice[1]);
    }

    public boolean holdsDoubles()
    {
        System.out.println("If holds doubles: " + (this.dice[0] == this.dice[1]));
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

    public boolean owns(int coordinate){
        for (Buyable property : belongings){
            if (property.getCoordinate() == coordinate){
                return true;
            }
        }
        return false;
    }

    /*public void     liftMortgage()
    {
        // Al: An, the corresponding button should be enabled only when there's a mortgaged prop in their 
        // belongings. please take care of that condition
            System.out.println("Please select a property to lift mortgage from:");

            for (Buyable b : belongings)
            {
                if (b.isMortgaged())
                {
                    System.out.println(b);
                }
            }

            // >>> receiving input <<<
            int receivedInt = 1;
            // >>> received input
            for (Buyable b : belongings)
            {
                if (b.getCoordinate() == receivedInt)
                    b.setIsMortgaged(false);
                receiveMoney((int)(-(b.getPrice() / 2) * 1.1));
            }
    }*/

    public void liftMortgage(Buyable property)
    {
        if (property.isMortgaged())
        {
            property.setIsMortgaged(false);
            receiveMoney((int)(-(property.getPrice() / 2) * 1.1));
        }
    }

    /*public void     erectHouse()
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
    }*/

        
    public void   erectHouse(Property property)
    {
        if (property.canBeImproved())
            property.buildHouse();
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
                    // property.initializeAuction(this);
                    // break ;
                }
            // }
        }
        else
        {
            System.out.println("Auction commenced.");
            //property.initializeAuction(this);
        }
    }

    // TESTED by Al
    public void rentProperty(Buyable property)
    {
        this.money -= property.getRent();
        //enterMortgageLoop(property.getOwner());
        System.out.println("You need to mortgage a house or build or ...");
    }

    // TESTED by Al
    /*public void enterMortgageLoop(Player owner)
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
                
                for (Buyable b : belongings){
                    if (!(owner == null))
                    {
                        owner.belongings.add(b);
                        b.setOwner(owner);
                    }
                    else
                        b.setOwner(null);
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
    }*/

    // Al: has to undergo major testing: not safe for use!
    /*public void initializeTrade()
    {
        // Al: also need to implement selling of getouttajail card
        System.out.println("Please select a player to trade with: ");
        for (Player p : Monopoly.getPlayers())
            if (!(this.equals(p)))
                System.out.println(p);
        // >>> receiving input <<<
        int playerIndex = 0;
        // >>> Player to trade with received <<<
        Player              another = Monopoly.getPlayers().get(playerIndex);

        ArrayList<Buyable>  myProps = new ArrayList<>(this.getBelongings());
        ArrayList<Buyable>  theirProps = new ArrayList<>(another.getBelongings());

        ArrayList<Buyable>  propsToGet = new ArrayList<>();
        ArrayList<Buyable>  propsToOffer = new ArrayList<>();
        int                 moneyToGet = 0;
        int                 moneyToOffer = 0;
        while (true)
        {
            System.out.println("Please choose a belonging to add to the list of THEIR props to get:");
            for (Buyable b : theirProps)
                System.out.println(b);
            // >>> receiving input <<<
            int index = 0;
            // >>> received input <<<

            // add to one, remove from the other
            propsToGet.add(theirProps.get(index));
            theirProps.remove(index);

            System.out.println("done? YES/NO");
            // if yes, break 
            if (true)
                break ;
        }
        System.out.println("Do you want to request money? YES/NO");
        // >>> receiving input <<<
        boolean requestMoney = false;
        // >>> received input <<<
        if (requestMoney)
        {
            // >>> receiving input <<<
            moneyToGet = 122313;
            // >>> received input <<<
        }
        else
            moneyToGet = 0;
        System.out.println("Time to discuss your end of the bargain.");
        while (true)
        {
            System.out.println("Please choose a belonging to add to the list of YOUR props to offer:");
            for (Buyable b : myProps)
                System.out.println(b);
            // >>> receiving input <<<
            int index = 0;
            // >>> received input <<<

            // add to one, remove from the other
            propsToOffer.add(myProps.get(index));
            myProps.remove(index);

            System.out.println("done? YES/NO");
            // if yes, break 
            if (true)
                break ;
        }
        System.out.println("Do you want to give away money? YES/NO");
        // >>> receiving input <<<
        boolean offeredMoney = false;
        // >>> received input <<<
        if (offeredMoney)
        {
            // >>> receiving input <<<
            moneyToOffer = 122313;
            // >>> received input <<<
        }
        else
            moneyToOffer = 0;
        // a POP-UP window asking the other player to accept/decline the offer:
        boolean response = true;
        // >>> receiving input <<<
        if (response)
        {
            another.receiveMoney(moneyToOffer);
            another.receiveMoney(-moneyToGet);
            this.receiveMoney(-moneyToOffer);
            this.receiveMoney(moneyToGet);

            for (Buyable e : propsToGet)
            {
                e.setOwner(this);
                another.getBelongings().remove(e);
                this.getBelongings().add(e);
            }
            for (Buyable e : propsToOffer)
            {
                e.setOwner(another);
                another.getBelongings().add(e);
                this.getBelongings().remove(e);
            }
        }
    }*/

    // TESTED by Al
    public void removePlayer(Player player)
    {
        for (Player p : Monopoly.getPlayers())
            if (p.equals(player))
                Monopoly.getPlayers().remove(p);
    }

    // TESTED by Al     (changed by An)
    public void mortgageProperty(Buyable property)
    {
        if (property.getOwner().equals(this)){
            if (property.canBeMortgaged()){
                this.money += (property.getPrice() / 2);
                property.setIsMortgaged(true);
            }
        }
    }

    // TESTED by Al
    /*public void degradeProperty(int coord)
    {
        for (Buyable belonging : belongings)
        {
            if (belonging.getCoordinate() == coord && canBeDegraded(belonging))
            {
                System.out.println(belonging + " has been degraded.");
                ((Property) belonging).sellHouse();
            }
        }
    }*/

    //An:
    public void degradeProperty(Property property){
        if (property.getOwner().equals(this)){                  //check the owner in all the methods of popUp
            if(property.getHouses() > 0){
                property.sellHouse();
            }
        }
    }

    // TESTED by Al
    public void receiveMoney(int mon)
    {
        System.out.println("entered RECEIVEMONEY. mon = " + mon);
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
    // public boolean canBeMortgaged(Buyable belonging)
    // {
    //     if (!belonging.isMortgaged())
    //     {
    //         if (belonging.getClass().getName() != "Property" || (belonging.getClass().getName() == "Property" && !((Property)belonging).isImproved()))
    //             return (true);
    //     }
    //     return (false);
    // }

    // TESTED by An
    // public boolean canBeMortgaged(int coordinate)
    // {
    //     for (Buyable belonging : belongings)
    //     {
    //         if (belonging.getCoordinate() == coordinate && canBeMortgaged(belonging))
    //             return (true);
    //     }
    //     return (false);
    // }

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

    public int      getDoublesInARow()
    {
        return (this.doublesInARow);
    }
    
    public int getDaysInJail()
    {
        return (this.daysInJail);
    }
    
    public int getDice()
    {
        return (this.dice[0] + this.dice[1]);
    }

    public int getFirstDice()
    {
        return (this.dice[0]);
    }

    public int getSecondDice()
    {
        return (this.dice[1]);
    }

    public void nullifyDice()
    {
        this.dice[0] = 0;
        this.dice[1] = 0;
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

    public void setDoublesInARow(int doublesInARow)
    {
        this.doublesInARow = doublesInARow;
    }

    public String toString(){
        return "Player: " + this.name + "   Money: " + this.money + "   Coordinate: " + this.coordinate;
    }

}
