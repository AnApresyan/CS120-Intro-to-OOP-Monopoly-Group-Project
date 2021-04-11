public class Tester
{
    public static void main (String[] args)
    {
        Player player = new Player("Alex", 1);
        player.belongings.add(new Property(1));
        player.belongings.add(new Property(3));
        System.out.println(player.belongings.get(0).getClass() + " " + player.belongings.get(0).getCoordinate() + " " + player.belongings.get(0).toString());
        /**
         * gives me an error: 'The method ownsOfThisColor(Player, Square) is undefined for the type 
         * SquareJava(67108964)'
         * something's def wrong with visiblity and stuff. I can't call ownsOfThisColor on the property
         * even tho it extends Buyable and Buyable contains the method (thus it must inherit it).
         * says that the Square doesn't have it, like wtf?
         */
        // System.out.println(player.belongings.get(1).ownsOfThisColor(player, player.belongings.get(0)));
    }    
}
