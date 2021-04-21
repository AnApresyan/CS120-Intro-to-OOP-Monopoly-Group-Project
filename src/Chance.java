public class Chance extends Deck 
{
    public Chance (int coordinate)
    {
        super(coordinate);
        setTitle("Chance");
    }
    public void doAction(Player activePlayer)
    {
        int chance;

        System.out.print("You're taking a chance: ");
        chance = (int)(Math.random() * 16) + 1;
        if (chance == 1)
        {
            System.out.println("ADVANCE TO GO (COLLECT $200).");
            while (activePlayer.getCoordinate() != 0)
                activePlayer.movePlayer(1);
            board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (chance == 2)
        {
            System.out.println("ADVANCE TO ILLINOIS AVE.");
            while (activePlayer.getCoordinate() != 24)
                activePlayer.movePlayer(1);
            Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (chance == 3)
        {
            System.out.println("ADVANCE TO ST. CHARLES PLACE. IF YOU PASS GO, COLLECT $200.");
            while (activePlayer.getCoordinate() != 11)
                activePlayer.movePlayer(1);
            Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (chance == 4)
        {
            System.out.println("ADVANCE TO THE NEAREST UTILITY. IF UNOWNED YOU MAY BUY IT FROM THE BANK. IF OWNED, THROW DICE AND PAY OWNER A TOTAL 10 TIMES THE AMOUNT THROWN.");
            while (activePlayer.getCoordinate() != 12 && activePlayer.getCoordinate() != 28)
                activePlayer.movePlayer(1);
            if (((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner() == null)
                Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
            else
            {
                activePlayer.setMoney(activePlayer.getMoney() - (activePlayer.throwDice() * 10));
                ((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner().setMoney(((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner().getMoney() + (activePlayer.getDice() * 10));
            }
        }
        else if (chance == 5)
        {
            System.out.println("ADVANCE TO THE NEAREST RAILROAD. IF UNOWNED YOU MAY BUY IT FROM THE BANK. IF OWNED, PAY OWNER TWICE THE RETAIL TO WHICH THEY ARE OTHERWISE ENTITLED.");
            while (activePlayer.getCoordinate() != 5 && activePlayer.getCoordinate() != 15 && activePlayer.getCoordinate() != 25 && activePlayer.getCoordinate() != 35)
                activePlayer.movePlayer(1);
            if (((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner() == null)
                Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
            else
            {
                activePlayer.setMoney(activePlayer.getMoney() - (((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getRent() * 2));
                ((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner().setMoney(((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner().getMoney() + (((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getRent() * 2));
            }
        }
        else if (chance == 6)
        {
            System.out.println("BANK PAYS YOU DIVIDEND OF $50.");
            activePlayer.setMoney(activePlayer.getMoney() + 50);
        }
        else if (chance == 7)
        {
            System.out.println("GET OUT OF JAIL FREE. THIS CARD MAY BE KEPT UNTIL NEEDED, OR TRADED/SOLD.");
            activePlayer.setGetOutOfJail(true);
        }
        else if (chance == 8)
        {
            System.out.println("GO BACK 3 SPACES.");
            activePlayer.setCoordinate(activePlayer.getCoordinate() - 3);
        }
        else if (chance == 9)
        {
            System.out.println("GO DIRECTLY TO JAIL. DO NOT PASS GO. DO NOT COLLECT $200.");
            activePlayer.setCoordinate(10);
        }
        else if (chance == 10)
        {
            System.out.println("MAKE GENERAL REPAIRS ON ALL YOUR PROPERTY: FOR EACH HOUSE PAY $25, FOR EACH HOTEL $100.");
            // Al: have to implement housing first.
        }
        else if (chance == 11)
        {
            System.out.println("PAY POOR TAX OF $15.");
            activePlayer.setMoney(activePlayer.getMoney() - 15);
        }
        else if (chance == 12)
        {
            System.out.println("TAKE A TRIP TO READING RAILROAD. IF YOU PASS GO, COLLECT $200.");
            while (activePlayer.getCoordinate() != 5)
                activePlayer.movePlayer(1);
            Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (chance == 13)
        {
            System.out.println("TAKE A WALK ON THE BOARDWALK. ADVANCE TOKEN TO BOARDWALK.");
            while (activePlayer.getCoordinate() != 39)
                activePlayer.movePlayer(1);
            Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (chance == 14)
        {
            System.out.println("YOU HAVE BEEN ELECTED CHAIRMAN OF THE BOARD. PAY EACH PLAYER $50.");
            // Al: we have to find a way to access the list of the players from the Monopoly class.
        }
        else if (chance == 15)
        {
            System.out.println("YOUR BUILDING AND LOAN MATURES. RECEIVE $150.");
            activePlayer.setMoney(activePlayer.getMoney() + 150);
        } 
        else if (chance == 16) 
        {
            System.out.println("YOU HAVE WON A CROSSWORD COMPETITION. COLLECT $100.");
            activePlayer.setMoney(activePlayer.getCoordinate() + 100);
        }
        // IF PLAYER'S BALANCE IS NEGATIVE, START THE MORTGAGING LOOP
        // Al: we should make the mortgage loop accessible everywhere to be able to call it in many other
        // places.
    }
}
