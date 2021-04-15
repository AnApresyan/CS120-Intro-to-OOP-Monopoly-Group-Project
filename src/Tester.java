public class Tester
{
    public static void main (String[] args)
    {
        Player player = new Player("Alex", 1);
        // player.getBelongings().add(new Property(1));
        // player.getBelongings().add(new Property(3));
        // player.getBelongings().get(0).setOwner(player);
        // player.getBelongings().get(1).setOwner(player);
        // ((Property) player.getBelongings().get(1)).houses = 1;

        //System.out.println(player.ownsOfThisColor(player.getBelongings().get(0)));
        Property prop = (Property) player.getBelongings().get(0);
        System.out.println(prop.isImproved());
    }    
}
