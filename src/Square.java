public abstract class Square 
{
    private String title;
    private int coordinate;

    public Square(int coordinate)
    {
        setCoordinate(coordinate);
    }

    public abstract void doAction(Player activePlayer);
    
    public void setCoordinate(int coordinate)
    {
        this.coordinate = coordinate;
    }  

    public int getCoordinate()
    {
        return (this.coordinate);
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle()
    {
        return (this.title);
    }

    public String toString()
    {
        return ("Title: " + this.title + ", coordinate: " + this.coordinate);
    }
} 
