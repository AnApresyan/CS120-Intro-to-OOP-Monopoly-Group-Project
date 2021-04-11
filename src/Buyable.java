public abstract class Buyable extends Square
{
    public static final int[][] COLORS = {{1, 3}, {6, 8, 9}, {11, 13, 14}, {16, 18, 19}, {21, 23, 24}, {26, 27, 29}, {31, 32, 34}, {37, 39}};
    
    public  Buyable(int coordinate)
    {
        super(coordinate);
    }
    
    // dear Anahit, this function return TRUE if the given player owns ALL the properties of color of the GIVEN
    // property, and FALSE otherwise
    public boolean          doesOwnAllProps(Player activePlayer, Square property)
    {
        int[]   owned = new int[activePlayer.belongings.size()];

        // create an array of ints (coordinates) representing the belongings of a player
        for (int x = 0; x < activePlayer.belongings.size(); x++)
            owned[x] = activePlayer.belongings.get(x).getCoordinate();
        
        for (int i = 0; i < COLORS[ft_searchintinmatrix(property.getCoordinate(), COLORS)].length; i++)
            if (!(ft_searchintinarray(COLORS[ft_searchintinmatrix(property.getCoordinate(), COLORS)][i], owned)))
                return (false);
        return (true);
    }

    // this is a helper for the helper for the searcher functions. return TRUE if an int is found in an array
    // of ints.
    public static boolean  ft_searchintinarray(int num, int[] nums)
    {
        for (int i = 0; i < nums.length; i++)
            if (num == nums[i])
                return (true);
        return (false);
    }

    // this is a helper for the searcher functions. returns the INDEX of the ARRAY (an element of the matrix)
    // in which the given int was found (if not found, returns -1)
    public static int      ft_searchintinmatrix(int num, int[][] nums)
    {
        for (int i = 0; i < nums.length; i++)
            if (ft_searchintinarray(num, nums[i]))
                return (i);
        return (-1);
    }
}
