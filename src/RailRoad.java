public class Railroad extends Buyable {

    public Railroad(int coordinate, String title) {
        super(coordinate);
        setTitle(title);
    }

    // public void doAction(int[] dice, Player activePlayer) {
        
    // }

    public int getRent()
    {
        return (25 * (int)Math.pow(2, this.getOwner().ownsOfThisColor(this) - 1));
    }

}
