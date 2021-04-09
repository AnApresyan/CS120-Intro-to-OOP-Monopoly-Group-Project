public abstract class Square 
{
    
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
    
} 
