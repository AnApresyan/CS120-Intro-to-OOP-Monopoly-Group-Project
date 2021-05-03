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

    public void setWantsToBuy(boolean wantsToBuy)
    {
        this.wantsToBuy = wantsToBuy;
    }

    public void setMessage(Player activePlayer){
        if (this.owner != null && this.owner.equals(activePlayer)){
            this.message = "Enjoy being in your properties";
        }
        else if (this.owner != null && !this.owner.equals(activePlayer)){

            this.message = "You pay $" + this.getRent() + " rent.";
        }
        else if (this.owner == null){
           this.message =  "You landed on an unowned property. Do you want to buy it?";
        }
    }

    public String getMessage()
    {
        return (this.message);
    }
}
