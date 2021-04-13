public class Utility extends Buyable
{
    
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
        return 0;
    }

    
}
