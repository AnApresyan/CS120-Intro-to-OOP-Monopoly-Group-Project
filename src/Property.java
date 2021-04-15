public class Property extends Buyable
{
    private int     houses;
    private int[]   rents;
    private int     housePrice;

    public Property(int coordinate, String title, int price, int[] rents, int housePrice)
    {
        super(coordinate);
        setPrice(price);
        this.rents = rents;
        this.housePrice = housePrice;
        setTitle(title);
    }

    // public enum TitleDeed
    // {
    //     MEDITERRANEAN_AVENUE    (1, 60, new int[]{2, 10, 30, 90, 160, 250}, 50),
    //     BALTIC_AVENUE           (3, 60, new int[]{4, 20, 60, 180, 320, 450}, 50),
    //     ORIENTAL_AVENUE         (6, 100, new int[]{6, 30, 90, 270, 400, 550}, 50),
    //     VERMOUNT_AVENUE         (8, 100, new int[]{6, 30, 90, 270, 400, 550}, 50),
    //     CONNECTICUT_AVENUE      (9, 120, new int[]{8, 40, 100, 300, 450, 600}, 50),
    //     ST_CHARLES_PLACE        (11, 140, new int[]{10, 50, 150, 450, 625, 750}, 100),
    //     STATES_AVENUE           (13, 140, new int[]{10, 50, 150, 450, 625, 750}, 100),
    //     VIRGINIA_AVENUE         (14, 160, new int[]{12, 60, 180, 500, 700, 900}, 100),
    //     ST_JAMES_PLACE          (16, 180, new int[]{14, 70, 200, 550, 750, 950}, 100),
    //     TENNESSEE_AVENUE        (18, 180, new int[]{14, 70, 200, 550, 750, 950}, 100),
    //     NEW_YORK_AVENUE         (19, 200, new int[]{16, 80, 220, 600, 800, 1000}, 100);
    //     // and so on...

    //     TitleDeed(int i, int price, int[] rents, int housePrice)
    //     {
    //         this.rents = rents;
    //         this.i = i;
    //         this.price = price;
    //         this.housePrice = housePrice;
    //     }
    //     // setting the title
    //     public String toString()
    //     {
    //         String res;

    //         res = this.name().toLowerCase();
    //         if (res.indexOf('_') >= 0)
    //             res = res.replace('_', ' ');
    //         return (res);
    //     }

    // }

    public boolean isImproved(){
        if (this.getOwner().doesOwnAllProps(this)){
            int colorIndex = Player.ft_searchintinmatrix(this.getCoordinate(), Buyable.COLORS);
            for (int propertyCord : Buyable.COLORS[colorIndex]){
                for(Buyable property : this.getOwner().getBelongings()){
                    if (property.getCoordinate() == propertyCord && ((Property) property).getHouses() >= 1)
                        return true;
                }
                
            }
        }
        return false;
    }

    // public void setTitleDeed() 
    // {
    //     for (TitleDeed p : TitleDeed.values())
    //         if (p.i == this.getCoordinate()){
    //             this.setTitle(p.toString());
    //             this.titleDeed = p;
    //         }
    // }
    // public String toString()
    // {
    //     return (toString());
    // }
    // public void doAction(int[] dice, Player activePlayer){

    // }

    public int getRent() {
        return this.rents[houses];
    }

    public int getHouses()
    {
        return (houses);
    }

    public int  getHousePrice()
    {
        return (housePrice);
    }
}