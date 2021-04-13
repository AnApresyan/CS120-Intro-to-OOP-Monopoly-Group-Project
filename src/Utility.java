public class Utility extends Buyable
{
    private static int diceValue;

    public Utility(int coordinate)
    {
        super(coordinate);
        if (coordinate % 5 == 0)
            this.setPrice(200);
        else
            this.setPrice(100);
            
    }

    public void doAction(int[] dice, Player activePlayer){
        
    }

    public int getRent() {
        if (this.getOwner().ownsOfThisColor(this) == 1)
            return (diceValue * 4);
        return (diceValue * 10);
    }

    public static void setDice(int dice)
    {
        diceValue = dice;
    }
}
