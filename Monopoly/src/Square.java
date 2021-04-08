public class Square 
{
    enum TitleDeed
    {
        GO                      (1, 0, null, 0, null),
        MEDITERRANEAN_AVENUE    (2, 60, new int[]{2, 10, 30, 90, 160, 250}, 50, "BROWN"),
        COMMUNITY_CHEST1        (3, 0, null, 0, "CHEST"),
        BALTIC_AVENUE           (4, 60, new int[]{4, 20, 60, 180, 320, 450}, 50, "BROWN"),
        INCOME_TAX              (5, 0, null, 0, null),
        READING_RAILROAD        (6, 200, null, 0, "RAILROAD"),
        ORIENTAL_AVENUE         (7, 100, new int[]{6, 30, 90, 270, 400, 550}, 50, "CYAN"),
        CHANCE1                 (8, 0, null, 0, "CHANCE"),
        VERMOUNT_AVENUE         (9, 100, new int[]{6, 30, 90, 270, 400, 550}, 50, "CYAN"),
        CONNECTICUT_AVENUE      (10, 120, new int[]{8, 40, 100, 300, 450, 600}, 50, "CYAN"),
        JAIL                    (11, 0, null, 0, null),
        ST_CHARLES_PLACE        (12, 140, new int[]{10, 50, 150, 450, 625, 750}, 100, "PINK"),
        ELECTRIC_COMPANY        (13, 0, null, 0, "UTILITY"),
        STATES_AVENUE           (14, 140, new int[]{10, 50, 150, 450, 625, 750}, 100, "PINK"),
        VIRGINIA_AVENUE         (15, 160, new int[]{12, 60, 180, 500, 700, 900}, 100, "PINK"),
        PENNSYLVANIA_RAILROAD   (16, 200, null, 0, "RAILROAD"),
        ST_JAMES_PLACE          (17, 180, new int[]{14, 70, 200, 550, 750, 950}, 100, "ORANGE"),
        COMMUNITY_CHEST2        (18, 0, null, 0, "CHEST"),
        TENNESSEE_AVENUE        (19, 180, new int[]{14, 70, 200, 550, 750, 950}, 100, "ORANGE"),
        NEW_YORK_AVENUE         (20, 200, new int[]{16, 80, 220, 600, 800, 1000}, 100, "ORANGE"),
        FREE_PARKING            (21, 0, null, 0, null);
        // and so on...

        public final int    coordinate;
        public final String title;
        public final int    price;
        public final int    housePrice;
        public final String color;

        TitleDeed(int coordinate, int price, int[] rents, int housePrice, String color)
        {
            this.coordinate = coordinate;
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
    private TitleDeed titleDeed;
    
    public void setTitleDeed(int square) 
    {
        for (TitleDeed p : TitleDeed.values())
            if (square == p.coordinate)
                this.titleDeed = p;
    }
} 
