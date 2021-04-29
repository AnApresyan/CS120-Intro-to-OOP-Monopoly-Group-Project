public class Chance extends Deck
{
    enum CardMessages
    {
        CARD0 ("Advance to GO (Collect $200)."),
        CARD1 ("Advance to Illinois Ave."),
        CARD2 ("Advance to St. Charles Place. If you pass GO, collect $200."),
        CARD3 ("Advance to the nearest utility. If unowned, you may buy it from the bank. If owned, throw dice and pay owner a total 10 times the amount thrown."),
        CARD4 ("Advance to the nearest railroad. IF UNOWNED YOU MAY BUY IT FROM the BANK. IF OWNED, PAY OWNER TWICE the RETAIL TO WHICH theY ARE OtheRWISE ENTITLED."),
        CARD5 ("BANK PAYS YOU DIVIDEND OF $50."),
        CARD6 ("GET OUT OF JAIL FREE. THIS CARD MAY BE KEPT UNTIL NEEDED, OR TRADED/SOLD."),
        CARD7 ("GO BACK 3 SPACES."),
        CARD8 ("GO DIRECTLY TO JAIL. DO NOT PASS GO. DO NOT COLLECT $200."),
        CARD9 ("MAKE GENERAL REPAIRS ON ALL YOUR PROPERTY: FOR EACH HOUSE PAY $25, FOR EACH HOTEL $100."),
        CARD10 ("PAY POOR TAX OF $15."),
        CARD11 ("TAKE A TRIP TO READING RAILROAD. IF YOU PASS GO, COLLECT $200."),
        CARD12 ("TAKE A WALK ON the BOARDWALK. ADVANCE TOKEN TO BOARDWALK."),
        CARD13 ("YOU HAVE BEEN ELECTED CHAIRMAN OF the BOARD. PAY EACH PLAYER $50."),
        CARD14 ("YOUR BUILDING AND LOAN MATURES. RECEIVE $150."),
        CARD15 ("YOU HAVE WON A CROSSWORD COMPETITION. COLLECT $100.");

        private String message;
        private CardMessages(String message)
        {
            this.message = message;
        }

        private String getMessage()
        {
            return (this.message);
        }
    }

    //private String message;
    private boolean callDoAction;

    public boolean ifcallDoAction(){
        return callDoAction;
    }

    public Chance (int coordinate)
    {
        super(coordinate);
        //this.message = "";
        setTitle("Chance");
    }

    // TESTED by Al
    public void doAction(Player activePlayer)
    {
        callDoAction = true;
        // int chance;

        // System.out.print("You're taking a chance: ");
        // chance = (int)(Math.random() * 16) + 1;
        if (getChance() == 0)
        {
            //System.out.println("ADVANCE TO GO (COLLECT $200).");
            //setMessage("ADVANCE TO GO (COLLECT $200).");
            while (activePlayer.getCoordinate() != 0)
                activePlayer.movePlayer(1);
            callDoAction = false;
        }
        else if (getChance() == 1)
        {
            //System.out.println("ADVANCE TO ILLINOIS AVE.");
            //setMessage("ADVANCE TO ILLINOIS AVE.");
            while (activePlayer.getCoordinate() != 24)
                activePlayer.movePlayer(1);
            //Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (getChance() == 2)
        {
            //System.out.println("ADVANCE TO ST. CHARLES PLACE. IF YOU PASS GO, COLLECT $200.");
            //setMessage("ADVANCE TO ST. CHARLES PLACE. IF YOU PASS GO, COLLECT $200.");
            while (activePlayer.getCoordinate() != 11)
                activePlayer.movePlayer(1);
            //Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (getChance() == 3)
        {
            //System.out.println("ADVANCE TO the NEAREST UTILITY. IF UNOWNED YOU MAY BUY IT FROM the BANK. IF OWNED, THROW DICE AND PAY OWNER A TOTAL 10 TIMES the AMOUNT THROWN.");
            //setMessage("ADVANCE TO the NEAREST UTILITY. IF UNOWNED YOU MAY BUY IT FROM the BANK. IF OWNED, THROW DICE AND PAY OWNER A TOTAL 10 TIMES the AMOUNT THROWN.");
            
            while (activePlayer.getCoordinate() != 12 && activePlayer.getCoordinate() != 28)
                activePlayer.movePlayer(1);
            if (((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner() != null){
            //     Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
            // else
            // {
                activePlayer.receiveMoney(-(Utility.setDice(activePlayer.throwDice()) * 10));
                ((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner().receiveMoney((activePlayer.getDice() * 10));
                callDoAction = false;
            }
        }
        else if (getChance() == 4)
        {
            //System.out.println("ADVANCE TO the NEAREST RAILROAD. IF UNOWNED YOU MAY BUY IT FROM the BANK. IF OWNED, PAY OWNER TWICE the RETAIL TO WHICH theY ARE OtheRWISE ENTITLED.");
            //setMessage("ADVANCE TO the NEAREST RAILROAD. IF UNOWNED YOU MAY BUY IT FROM the BANK. IF OWNED, PAY OWNER TWICE the RETAIL TO WHICH theY ARE OtheRWISE ENTITLED.");
            while (activePlayer.getCoordinate() != 5 && activePlayer.getCoordinate() != 15 && activePlayer.getCoordinate() != 25 && activePlayer.getCoordinate() != 35)
                activePlayer.movePlayer(1);
            if (((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner() != null){
            //     Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
            // else
            // {
                activePlayer.receiveMoney(-(((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getRent() * 2));
                ((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner().receiveMoney(((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getRent() * 2);
                callDoAction = false;
            }
        }
        else if (getChance() == 5)
        {
            //System.out.println("BANK PAYS YOU DIVIDEND OF $50.");
            //setMessage("BANK PAYS YOU DIVIDEND OF $50.");
            activePlayer.receiveMoney(50);
            callDoAction = false;
        }
        else if (getChance() == 6)
        {
            //System.out.println("GET OUT OF JAIL FREE. THIS CARD MAY BE KEPT UNTIL NEEDED, OR TRADED/SOLD.");
            //setMessage("GET OUT OF JAIL FREE. THIS CARD MAY BE KEPT UNTIL NEEDED, OR TRADED/SOLD.");
            activePlayer.setGetOutOfJail(true);
            callDoAction = false;
        }
        else if (getChance() == 7)
        {
            //System.out.println("GO BACK 3 SPACES.");
            //setMessage("GO BACK 3 SPACES.");
            activePlayer.setCoordinate(activePlayer.getCoordinate() - 3);
            //Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (getChance() == 8)
        {
            //System.out.println("GO DIRECTLY TO JAIL. DO NOT PASS GO. DO NOT COLLECT $200.");
            //setMessage("GO DIRECTLY TO JAIL. DO NOT PASS GO. DO NOT COLLECT $200.");
            activePlayer.setCoordinate(10);
            activePlayer.setIsPrisoned(true);
            callDoAction = false;
        }
        else if (getChance() == 9)
        {
            //System.out.println("MAKE GENERAL REPAIRS ON ALL YOUR PROPERTY: FOR EACH HOUSE PAY $25, FOR EACH HOTEL $100.");
            //setMessage("MAKE GENERAL REPAIRS ON ALL YOUR PROPERTY: FOR EACH HOUSE PAY $25, FOR EACH HOTEL $100.");
            int loss;

            loss = 0;
            for (int i = 0; i < activePlayer.getBelongings().size(); i++)
            {
                if (activePlayer.getBelongings().get(i).getClass().getName().equals("Property"))
                {
                    if (((Property) activePlayer.getBelongings().get(i)).getHouses() <= 4)
                        loss += ((Property) activePlayer.getBelongings().get(i)).getHouses() * 25;
                    else if (((Property) activePlayer.getBelongings().get(i)).getHouses() == 5)
                        loss += 100;
                }
            }
            activePlayer.receiveMoney(-loss);
            callDoAction = false;
        }
        else if (getChance() == 10)
        {
            //System.out.println("PAY POOR TAX OF $15.");
            //setMessage("PAY POOR TAX OF $15.");
            activePlayer.receiveMoney(-15);
            callDoAction = false;
        }
        else if (getChance() == 11)
        {
            //System.out.println("TAKE A TRIP TO READING RAILROAD. IF YOU PASS GO, COLLECT $200.");
            //setMessage("TAKE A TRIP TO READING RAILROAD. IF YOU PASS GO, COLLECT $200.");
            while (activePlayer.getCoordinate() != 5)
                activePlayer.movePlayer(1);
            //Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (getChance() == 12)
        {
            //System.out.println("TAKE A WALK ON the BOARDWALK. ADVANCE TOKEN TO BOARDWALK.");
            //setMessage("TAKE A WALK ON the BOARDWALK. ADVANCE TOKEN TO BOARDWALK.");
            while (activePlayer.getCoordinate() != 39)
                activePlayer.movePlayer(1);
            //Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        }
        else if (getChance() == 13)
        {
            int i;

            //System.out.println("YOU HAVE BEEN ELECTED CHAIRMAN OF the BOARD. PAY EACH PLAYER $50.");
            //setMessage("YOU HAVE BEEN ELECTED CHAIRMAN OF the BOARD. PAY EACH PLAYER $50.");
            i = 0;
            while (i < Monopoly.getPlayers().size())
            {
                if (!(activePlayer.equals(Monopoly.getPlayers().get(i))))
                {
                    Monopoly.getPlayers().get(i).receiveMoney(50);
                    activePlayer.receiveMoney(-50);
                }
                i++;
            }
            callDoAction = false;
        }
        else if (getChance() == 14)
        {
            //System.out.println("YOUR BUILDING AND LOAN MATURES. RECEIVE $150.");
            //setMessage("YOUR BUILDING AND LOAN MATURES. RECEIVE $150.");
            activePlayer.receiveMoney(150);
            callDoAction = false;
        } 
        else if (getChance() == 15) 
        {
            //System.out.println("YOU HAVE WON A CROSSWORD COMPETITION. COLLECT $100.");
            //setMessage("YOU HAVE WON A CROSSWORD COMPETITION. COLLECT $100.");
            activePlayer.receiveMoney(100);
            callDoAction = false;
        }
        activePlayer.enterMortgageLoop(null);
    }

    // public void setMessage(String message){
    //     this.message = message;
    // }
    public String getMessage()
    {
        return getCard().getMessage();
    }

}
