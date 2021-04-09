public class Property extends RailProperty
{
    private Player  owner;
    private int     houses;
    private TitleDeed titleDeed;

    public Property(int coordinate){
        super(coordinate);
    }

    
    public enum TitleDeed
    {
        MEDITERRANEAN_AVENUE    (60, new int[]{2, 10, 30, 90, 160, 250}, 50, "BROWN"),
        BALTIC_AVENUE           (60, new int[]{4, 20, 60, 180, 320, 450}, 50, "BROWN"),
        ORIENTAL_AVENUE         (100, new int[]{6, 30, 90, 270, 400, 550}, 50, "CYAN"),
        VERMOUNT_AVENUE         (100, new int[]{6, 30, 90, 270, 400, 550}, 50, "CYAN"),
        CONNECTICUT_AVENUE      (120, new int[]{8, 40, 100, 300, 450, 600}, 50, "CYAN"),
        ST_CHARLES_PLACE        (140, new int[]{10, 50, 150, 450, 625, 750}, 100, "PINK"),
        STATES_AVENUE           (140, new int[]{10, 50, 150, 450, 625, 750}, 100, "PINK"),
        VIRGINIA_AVENUE         (160, new int[]{12, 60, 180, 500, 700, 900}, 100, "PINK"),
        ST_JAMES_PLACE          (180, new int[]{14, 70, 200, 550, 750, 950}, 100, "ORANGE"),
        TENNESSEE_AVENUE        (180, new int[]{14, 70, 200, 550, 750, 950}, 100, "ORANGE"),
        NEW_YORK_AVENUE         (200, new int[]{16, 80, 220, 600, 800, 1000}, 100, "ORANGE");
        // and so on...

        private final String title;
        private final int    price;
        private final int    housePrice;
        private final String color;

        TitleDeed(int price, int[] rents, int housePrice, String color)
        {
            this.price = price;
            this.housePrice = housePrice;
            this.color = color;
            this.title = toString();
        }
        // setting the title
        public String   toString()
        {
            String res;

            res = this.name().toLowerCase();
            if (res.indexOf('_') >= 0)
                res = res.replace('_', ' ');
            return (res);
        }
    }



    public void setTitleDeed(int square) 
    {
        for (TitleDeed p : TitleDeed.values())
            if (square == this.getCoordinate())
                this.titleDeed = p;
    }
    // public int getRent()
    // {

    // }
}