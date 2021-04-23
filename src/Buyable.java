public abstract class Buyable extends Square
{
    private Player      owner;
    private int         price;
    private boolean     isMortgaged;

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
            System.out.println("You landed on an unowned land!");
            activePlayer.buyProperty(this);
        }
        else
        {
            System.out.println("You landed on an owned land.");
            if (!(this.owner.equals(activePlayer)))
            {
                System.out.println("You have to pay rent now.");
                activePlayer.rentProperty(this);
            }
        }
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
}
