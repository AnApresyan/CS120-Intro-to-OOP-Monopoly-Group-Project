public abstract class Square 
{
    private String title;
    private int coordinate;

    public Square(int coordinate)
    {
        setCoordinate(coordinate);
    }

    public void setCoordinate(int coordinate){
        this.coordinate = coordinate;
    }  
    public int getCoordinate(){
        return this.coordinate;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public abstract void doAction(int[] dice, Player activePlayer);
    

    
} 
