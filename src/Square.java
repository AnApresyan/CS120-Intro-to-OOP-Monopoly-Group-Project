public abstract class Square 
{
    private String          title;
    private int             coordinate;
    public abstract void    doAction(Player activePlayer);
    public abstract String  getMessage();

    public  Square(int coordinate)
    {
        this.coordinate = coordinate;
    }


    
    public int      getCoordinate()
    {
        return (this.coordinate);
    }

    public String   getTitle()
    {
        return (this.title);
    }

    public void     setTitle(String title)
    {
        this.title = title;
    }
    
    public void     setCoordinate(int coordinate)
    {
        this.coordinate = coordinate;
    }

    public String   toString()
    {
        return ("Title: " + this.title + ", coordinate: " + this.coordinate);
    }
} 
