public class RailRoad extends Buyable {

    public RailRoad(int coordinate) {
        super(coordinate);
    }

    // public void doAction(int[] dice, Player activePlayer) {
        
    // }

    public int getRent() {
        return (25 * (int)Math.pow(2, this.getOwner().ownsOfThisColor(this) - 1));
    }

}
