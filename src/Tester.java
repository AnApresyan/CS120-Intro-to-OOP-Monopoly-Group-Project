public class Tester
{
    public static void main (String[] args)
    {
        Player player = new Player("Alex", 1);
        player.belongings.add(new Property(1));
        player.belongings.add(new Property(3));
        System.out.println(player.ownsOfThisColor(player.belongings.get(0)));
    }    
}
