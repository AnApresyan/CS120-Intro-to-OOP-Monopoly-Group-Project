public class GoToJail extends Square {
    public GoToJail(int coordinate){
        super(coordinate);
        setTitle("Go To Jail");
    }
    public void doAction(Player activePlayer)
    {
        //System.out.println("Haha! You go to jail right now!");
        activePlayer.setIsPrisoned(true);
        activePlayer.setCoordinate(10);
    }
    @Override
    public String getMessage() {
        return "Haha! You go to jail right now!";
    }
}
