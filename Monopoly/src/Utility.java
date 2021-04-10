public class Utility extends Square{
    
    private Player owner;
    private int price;

    public Utility(int coordinate){
        super(coordinate);
        if (coordinate % 5 == 0)
            this.price = 200;
        else
            this.price = 150;
    }

    public void doAction(int[] dice, Player activePlayer){
        
    }
}
