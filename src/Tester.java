public class Tester
{
    public static void main (String[] args)
    {
        Player player = new Player("Alex", 1);
        player.getBelongings().add(new Property(1));
        player.getBelongings().add(new Property(3));
        System.out.println(player.ownsOfThisColor(player.getBelongings().get(0)));
    }    
}
