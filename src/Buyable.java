import java.util.ArrayList;

public abstract class Buyable extends Square
{
    private Player      owner;
    private int         price;
    private boolean     isMortgaged;
    private boolean     wantsToBuy;
    private String      message;

    public static final int[][] COLORS = {{1, 3}, {5, 15, 25, 35}, {12, 28}, {6, 8, 9}, {11, 13, 14}, {16, 18, 19}, {21, 23, 24}, {26, 27, 29}, {31, 32, 34}, {37, 39}};
    
    public  Buyable(int coordinate)
    {
        super(coordinate);
    }

    public abstract int getRent();

    // TESTED by Al
    public void doAction(Player activePlayer)
    {
        if (this.owner == null)
        {
            //System.out.println("You landed on an unowned land!");
            if (wantsToBuy)
                activePlayer.buyProperty(this);
            
        }
        else
        {
            //System.out.println("You landed on an owned land.");
            if (!(this.owner.equals(activePlayer)))
            {
               // System.out.println("You have to pay rent now.");
                activePlayer.rentProperty(this);
            }
        }
    }

    public boolean canBeMortgaged()
    {
        if (!this.isMortgaged() && this.owner != null)
        {
            if (this.getClass().getName() != "Property" || ((this.getClass().getName() == "Property" && !((Property)this).isImproved())))
                return (true);
        }
        return (false);
    }



    public void initializeAuction(Player starter)
    {
        System.out.println("Auction commenced.");
        ArrayList<Player> bidders = new ArrayList<>();
        bidders.add(starter);
        for (Player p : Monopoly.getPlayers())
            if (p.equals(starter))
                bidders.add(p);
        int index = 0;
        int lowEnd = 1;
        int highEnd;
        int choice = starter.getMoney();
        Player activeBidder;
        while (true)
        {
            if (index == bidders.size())
                index = 0;
            activeBidder = bidders.get(index);
            if (activeBidder.getMoney() >= choice)
            {
                highEnd = activeBidder.getMoney();
                System.out.println(activeBidder.getName() + ", please select a value between " + lowEnd + " and " + highEnd + " to bid.");
                // >>> receiving input <<<
                choice = 100;
                //or give up
                
                // >>> received input <<<
                lowEnd = choice;
                index++;
            }
            else
            {
                bidders.remove(activeBidder);
            }
            if (bidders.size() == 1)
                break ;
        }
        bidders.get(0).receiveMoney(choice);
        bidders.get(0).getBelongings().add(this);
    }

    public boolean isMortgaged()
    {
        return (this.isMortgaged);
    }

    public void setIsMortgaged(boolean isMortgaged)
    {
        this.isMortgaged = isMortgaged;
    }

    public void setPrice(int price)
    {
        this.price = price;
    } 

    public int getPrice()
    {
        return (this.price);
    }

    public void setOwner(Player player)
    {
        this.owner = player;
    }

    public Player getOwner()
    {
        return (this.owner);
    }
    public void setWantsToBuy(boolean wantsToBuy){
        this.wantsToBuy = wantsToBuy;
    }

    public void setMessage(Player activPlayer){
        if (this.owner != null && this.owner.equals(activPlayer)){
            this.message = "Enjoy being in your properties";
        }
        else if (this.owner != null && !this.owner.equals(activPlayer)){

            this.message = "You pay $" + this.getRent() + " rent.";
        }
        else if (this.owner == null){
           this.message =  "You landed on an unowned property. Do you want to buy it?";
        }
    }
    public String getMessage(){

        return this.message;

    }
}
