public class Board
{
    private static Square[] squares = new Square[40];

    public  Board()
    {
        /**
         * Setting up the board:
         */
        squares[0] = new GOTaxFree(0);
        squares[1] = new Property(1, "Mediterranean Avenue", 60, new int[]{2, 10, 30, 90, 160, 250}, 50);
        squares[2] = new CommunityChest(2);
        squares[3] = new Property(3, "Baltic Avenue", 60, new int[]{4, 20, 60, 180, 320, 450}, 50);
        squares[4] = new GOTaxFree(4);
        squares[5] = new Railroad(5, "Reading Railroad");
        squares[6] = new Property(6, "Oriental Avenue", 100, new int[]{6, 30, 90, 270, 400, 550}, 50);
        squares[7] = new Chance(7);
        squares[8] = new Property(8, "Vermount Avenue", 100, new int[]{6, 30, 90, 270, 400, 550}, 50);
        squares[9] = new Property(9, "Connecticut Avenue", 120, new int[]{8, 40, 100, 300, 450, 600}, 50);
        squares[10] = new Jail(10);
        squares[11] = new Property(11, "St. Charles Place", 140, new int[]{10, 50, 150, 450, 625, 750}, 100);
        squares[12] = new Utility(12, "Electric Company"); 
        squares[13] = new Property(13, "States Avenue", 140, new int[]{10, 50, 150, 450, 625, 750}, 100);
        squares[14] = new Property(14, "Virginia Avenue", 160, new int[]{12, 60, 180, 500, 700, 900}, 100);
        squares[15] = new Railroad(15, "Pennsylvania Railroad");
        squares[16] = new Property(16, "St. James Place", 180, new int[]{14, 70, 200, 550, 750, 950}, 100);
        squares[17] = new CommunityChest(17);
        squares[18] = new Property(18, "Tennessee Avenue", 180, new int[]{14, 70, 200, 550, 750, 950}, 100);
        squares[19] = new Property(19, "New York Avenue", 200, new int[]{16, 80, 220, 600, 800, 1000}, 100);
        squares[20] = new GOTaxFree(20);
        squares[21] = new Property(21, "Kentucky Avenue", 220, new int[]{18, 90, 250, 700, 875, 1050}, 150);
        squares[22] = new Chance(22);
        squares[23] = new Property(23, "Indiana Avenue", 220, new int[]{18, 90, 250, 700, 875, 1050}, 150);
        squares[24] = new Property(24, "Illinois Avenue", 240, new int[]{20, 100, 300, 750, 925, 1100}, 150);
        squares[25] = new Railroad(25, "B&O Railroad");
        squares[26] = new Property(26, "Atlantic Avenue", 260, new int[]{22, 110, 330, 800, 975, 1150}, 150);
        squares[27] = new Property(27, "Ventnor Avenue", 260, new int[]{22, 110, 330, 800, 975, 1150}, 150);
        squares[28] = new Utility(28, "Water Works");
        squares[29] = new Property(29, "Marvin Gardens", 280, new int[]{24, 120, 360, 850, 1025, 1200}, 150);
        squares[30] = new GOTaxFree(30);
        squares[31] = new Property(31, "Pacific Avenue", 300, new int[]{26, 130, 390, 900, 1100, 1275}, 200);
        squares[32] = new Property(32, "North Carolina Avenue", 300, new int[]{26, 130, 390, 900, 1100, 1275}, 200);
        squares[33] = new CommunityChest(33);
        squares[34] = new Property(34, "Pennsylvania Avenue", 320, new int[]{28, 150, 450, 1000, 1200, 1400}, 200);
        squares[35] = new Railroad(35, "Short Line");
        squares[36] = new Chance(36);
        squares[37] = new Property(37, "Park Place", 350, new int[]{35, 175, 500, 1100, 1300, 1500}, 200);
        squares[38] = new GOTaxFree(38);
        squares[39] = new Property(39, "Boardwalk", 400, new int[]{50, 200, 600, 1400, 1700, 2000}, 200);
    }

    public static Square[]  getSquares()
    {
        return (squares);
    }
}
