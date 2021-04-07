public class Property extends Square
{
    private Player owner;
    private int price;
    private String title;
    private String color;
    private int houses;
    private int housePrice;
    private int[] rents;

    public Property(String title, int price, String color, int[] rents, int housePrice)
    {
        setColor(color);
        setPrice(price);
        setTitle(title);
        setRents(rents);
        setHousePrice(housePrice);
    }
    public void setPrice(int price)
    {
        this.price = price;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setColor(String color)
    {
        this.color = color;
    }
    public void setRents(int[] rents)
    {
        this.rents = rents;
    }
    public void setHousePrice(int housePrice)
    {
        this.housePrice = housePrice;
    }
}