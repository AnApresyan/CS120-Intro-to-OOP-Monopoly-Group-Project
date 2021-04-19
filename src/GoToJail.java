public class GoToJail extends Square {
    public GoToJail(int coordinate){
        super(coordinate);
        setTitle("Go To Jail");
    }
    public void doAction(Player activePlayer){
        activePlayer.setIsPrisoned(true);
        activePlayer.setCoordinate(10);
    }
}
