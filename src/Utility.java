public class Utility extends Buyable
{
    
    private Player  owner;
    private int     price;

    public Utility(int coordinate)
    {
        super(coordinate);
        if (coordinate % 5 == 0)
            this.price = 200;
        else
            this.price = 150;
    }

    public void doAction(int[] dice, Player activePlayer){
        
    }
    public int  ownsOfThisColor(Player activePlayer, Buyable property)
    {
        int     props;
        int[]   owned = new int[activePlayer.belongings.size()];

        // create an array of ints (coordinates) representing the belongings of a player
        for (int x = 0; x < activePlayer.belongings.size(); x++)
            owned[x] = activePlayer.belongings.get(x).getCoordinate();
        props = 0;
        for (int i = 0; i < COLORS[ft_searchintinmatrix(property.getCoordinate(), COLORS)].length; i++)
        if (ft_searchintinarray(COLORS[ft_searchintinmatrix(property.getCoordinate(), COLORS)][i], owned))
            props++;
        return (props);
    }
}
