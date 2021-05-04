public class Chance extends Deck
{
    enum    CardMessages
    {
        CARD0   ("Advance to GO (Collect $200)."),
        CARD1   ("Advance to Illinois Ave."),
        CARD2   ("Advance to St. Charles Place. If you pass GO, collect $200."),
        CARD3   ("Advance to the nearest utility. If unowned, you may buy it from the bank. If owned, throw dice and pay owner a total 10 times the amount thrown."),
        CARD4   ("Advance to the nearest railroad. If unowned you may buy it from the bank. If owned, pay owner twice the retail to which they are otherwise entitled."),
        CARD5   ("Bank pays you dividend of $50."),
        CARD6   ("Get out of jail free. This card may be kept until needed, or traded/sold."),
        CARD7   ("Go back 3 spaces."),
        CARD8   ("Go directly to jail. Do not pass go. Do not collect $200."),
        CARD9   ("Make general repairs on all your property: for each house pay $25, for each hotel $100."),
        CARD10  ("Pay poor tax of $15."),
        CARD11  ("Take a trip to reading railroad. If you pass go, collect $200."),
        CARD12  ("Take a walk on the boardwalk. Advance token to boardwalk."),
        CARD13  ("You have been elected chairman of the board. Pay each player $50."),
        CARD14  ("Your building and loan matures. Receive $150."),
        CARD15  ("You have won a crossword competition. Collect $100.");

        private String  message;

        private CardMessages(String message)
        {
            this.message = message;
        }

        private String  getMessage()
        {
            return (this.message);
        }
    }

    private CardMessages    card;
    private boolean         callDoAction;

    public Chance(int coordinate)
    {
        super(coordinate);
        setTitle("Chance");
    }

    public void     doAction(Player activePlayer)
    {
        callDoAction = true;
        if (getChance() == 0)
        {
            while (activePlayer.getCoordinate() != 0)
                activePlayer.movePlayer(1);
            callDoAction = false;
        }
        else if (getChance() == 1)
        {
            while (activePlayer.getCoordinate() != 24)
                activePlayer.movePlayer(1);
        }
        else if (getChance() == 2)
        {
            while (activePlayer.getCoordinate() != 11)
                activePlayer.movePlayer(1);
        }
        else if (getChance() == 3)
        {            
            while (activePlayer.getCoordinate() != 12 && activePlayer.getCoordinate() != 28)
                activePlayer.movePlayer(1);
            if (((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner() != null)
            {
                activePlayer.receiveMoney(-(Utility.setDice(activePlayer.throwDice()) * 10));
                ((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner().receiveMoney((activePlayer.getDice() * 10));
                callDoAction = false;
            }
        }
        else if (getChance() == 4)
        {
            while (activePlayer.getCoordinate() != 5 && activePlayer.getCoordinate() != 15 && activePlayer.getCoordinate() != 25 && activePlayer.getCoordinate() != 35)
                activePlayer.movePlayer(1);
            if (((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner() != null)
            {
                activePlayer.receiveMoney(-(((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getRent() * 2));
                ((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getOwner().receiveMoney(((Buyable) Board.getSquares()[activePlayer.getCoordinate()]).getRent() * 2);
                callDoAction = false;
            }
        }
        else if (getChance() == 5)
        {
            activePlayer.receiveMoney(50);
            callDoAction = false;
        }
        else if (getChance() == 6)
        {
            activePlayer.setGetOutOfJail(true);
            callDoAction = false;
        }
        else if (getChance() == 7)
            activePlayer.setCoordinate(activePlayer.getCoordinate() - 3);
        else if (getChance() == 8)
        {
            activePlayer.setCoordinate(10);
            activePlayer.setIsPrisoned(true);
            callDoAction = false;
        }
        else if (getChance() == 9)
        {
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
            activePlayer.receiveMoney(-15);
            callDoAction = false;
        }
        else if (getChance() == 11)
            while (activePlayer.getCoordinate() != 5)
                activePlayer.movePlayer(1);
        else if (getChance() == 12)
            while (activePlayer.getCoordinate() != 39)
                activePlayer.movePlayer(1);
        else if (getChance() == 13)
        {
            int i;

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
            activePlayer.receiveMoney(150);
            callDoAction = false;
        } 
        else if (getChance() == 15) 
        {
            activePlayer.receiveMoney(100);
            callDoAction = false;
        }
    }

    public boolean  ifcallDoAction()
    {
        return (this.callDoAction);
    }

    public String   getMessage()
    {
        drawCard();
        setCard(getChance());
        return (this.card.getMessage());
    }

    public void     setCard(int chance)
    {
        this.card = CardMessages.values()[getChance()];
    }
}
