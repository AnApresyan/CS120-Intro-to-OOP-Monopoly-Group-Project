import java.util.ArrayList;

public class MethodTester
{
    public static void main (String[] args)
    {

        // players...
        Player Alexander = new Player("Alexander");
        Player Anahit = new Player("Anahit");
        Player Avo = new Player("Avo");
        ArrayList<Player> pl = new ArrayList<>();
        pl.add(Alexander);
        pl.add(Anahit);
        pl.add(Avo);

        Monopoly mono = new Monopoly(pl);

        // System.out.println("List of players:");
        // for (int i = 0; i < Monopoly.getPlayers().size(); i++)
        //     System.out.println(Monopoly.getPlayers().get(i));
        // System.out.println();
        // Anahit.buyProperty((Buyable)Board.getSquares()[15]);
        // Anahit.buyProperty((Buyable)Board.getSquares()[35]);

        // System.out.println("Alex's balance: " + Alexander.getMoney());
        // System.out.println("Alex's GOOJF: " + Alexander.getGetOutOfJail());
        // System.out.println("An's balance: " + Anahit.getMoney());
        // System.out.println("Avo's balance: " + Avo.getMoney());
        // Alexander.buyProperty((Buyable)Board.getSquares()[6]);
        // Alexander.buyProperty((Buyable)Board.getSquares()[8]);
        // Alexander.buyProperty((Buyable)Board.getSquares()[9]);
        // ((Property)Alexander.getBelongings().get(0)).setHouses(1);
        // ((Property)Alexander.getBelongings().get(1)).setHouses(5);
        // ((Property)Alexander.getBelongings().get(2)).setHouses(2);
        Alexander.movePlayer(30);
        System.out.println("Alex's coord: " + Alexander.getCoordinate());
        Board.getSquares()[Alexander.getCoordinate()].doAction(Alexander);

        // System.out.println("Alex's balance: " + Alexander.getMoney());
        // System.out.println("Alex's GOOJF: " + Alexander.getGetOutOfJail());
        // System.out.println("An's balance: " + Anahit.getMoney());
        // System.out.println("Avo's balance: " + Avo.getMoney());
        System.out.println("Alex's coord: " + Alexander.getCoordinate());
        System.out.println("Alex's isPrisoned: " + Alexander.isPrisoned());

        // for (int i = 0; i < Alexander.getBelongings().size(); i++)
        // {
        //     System.out.println(Alexander.getBelongings().get(i));
        // }

        // // ...throws a dice...
        // Alexander.throwDice();
        // System.out.println("Alex's dice: " + Alexander.getDice());
        // // ...moves...
        // Alexander.movePlayer(Alexander.getDice());

        // Anahit.buyProperty((Buyable)Board.getSquares()[3]);
        // Anahit.buyProperty((Buyable)Board.getSquares()[1]);
        // ((Property)Board.getSquares()[3]).setHouses(5);


        // System.out.println("Anahit owns:");
        // for (int i = 0; i < Anahit.getBelongings().size(); i++)
        //     System.out.println(Anahit.getBelongings().get(i));
        // // Anahit.setCoordinate(3);
        // // Board.getSquares()[Anahit.getCoordinate()].doAction(Anahit);
        // Alexander.buyProperty((Buyable)Board.getSquares()[5]);
        // Alexander.buyProperty((Buyable)Board.getSquares()[28]);
        // Alexander.buyProperty((Buyable)Board.getSquares()[6]);
        // Alexander.buyProperty((Buyable)Board.getSquares()[8]);
        // Alexander.buyProperty((Buyable)Board.getSquares()[9]);
        // ((Property)Board.getSquares()[9]).setHouses(1);
        // Alexander.setMoney(449);

        // System.out.println("Alexander owns:");
        // for (int i = 0; i < Alexander.getBelongings().size(); i++)
        //     System.out.println(Alexander.getBelongings().get(i));

        // Alexander.setCoordinate(3);
        // // System.out.println("Alex's coord: " + Alexander.getCoordinate());

        // // // ...activates a square...
        // System.out.println("Alex's balance: " + Alexander.getMoney());
        // Board.getSquares()[Alexander.getCoordinate()].doAction(Alexander);

        // System.out.println("List of players:");
        // for (int i = 0; i < Monopoly.getPlayers().size(); i++)
        //     System.out.println(Monopoly.getPlayers().get(i));
        // System.out.println();

        // System.out.println("Alex's balance: " + Alexander.getMoney());

        // System.out.println("Alexander owns:");
        // for (int i = 0; i < Alexander.getBelongings().size(); i++)
        //     System.out.println(Alexander.getBelongings().get(i));
        // System.out.println("Anahit owns:");
        // for (int i = 0; i < Anahit.getBelongings().size(); i++)
        //     System.out.println(Anahit.getBelongings().get(i));
        // System.out.println("Alex's balance: " + Alexander.getMoney());
        // System.out.println("Alex's BELONGINGS size: " + Alexander.getBelongings().size());
        // for (int i = 0; i < Alexander.getBelongings().size(); i++)
        // {
        //     System.out.println(Alexander.getBelongings().get(i));
        // }
        // System.out.println(((Buyable)Board.getSquares()[Alexander.getCoordinate()]).getOwner());
    }
}
