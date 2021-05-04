public class CommunityChest extends Deck
{
    enum    CardMessages
    {
        CARD0   ("Advance to GO (Collect $200)."),
        CARD1   ("Bank error in your favor. Collect $200."),
        CARD2   ("Doctor's fees. Pay $50."),
        CARD3   ("From sale of stock you get $45."),
        CARD4   ("Go directly to jail. Do not pass go. Do not collect $200."),
        CARD5   ("Get out of jail free. This card may be kept until needed, or traded/sold."),
        CARD6   ("Grand Opera Night. Collect $50 from every player for opening night seats."),
        CARD7   ("Xmas fund matures. Collect $100."),
        CARD8   ("Income tax refund. Collect $20."),
        CARD9   ("Life insurance matures. Collect $100."),
        CARD10  ("Pay hospital $100."),
        CARD11  ("Pay school tax of $150."),
        CARD12  ("Receive for services $25."),
        CARD13  ("You are assessed for street repairs: pay $40 per house and $115 per hotel you own."),
        CARD14  ("You have won second prize in a beauty contest. Collect $10."),
        CARD15  ("You inherit $100.");

        private String  message;

        private CardMessages(String message)
        {
            this.message = message;
        }

        private String getMessage()
        {
            return (this.message);
        }
    }

    private CardMessages    card;

    public CommunityChest(int square)
    {
        super(square);
        setTitle("Community Chest");
    }
    
    public void doAction(Player activePlayer)
    {

        if (getChance() == 0)
            while (activePlayer.getCoordinate() != 0)
                activePlayer.movePlayer(1);
        else if (getChance() == 1)
            activePlayer.receiveMoney(200);
        else if (getChance() == 2)
            activePlayer.receiveMoney(-50);
        else if (getChance() == 3)
            activePlayer.receiveMoney(45);
        else if (getChance() == 4)
        {
            activePlayer.setCoordinate(10);
            activePlayer.setIsPrisoned(true);
        }
        else if (getChance() == 5)
            activePlayer.setGetOutOfJail(true);
        else if (getChance() == 6)
        {
            int i;

            i = 0;
            while (i < Monopoly.getPlayers().size())
            {
                if (!(activePlayer.equals(Monopoly.getPlayers().get(i))))
                {
                    Monopoly.getPlayers().get(i).receiveMoney(-50);
                    activePlayer.receiveMoney(50);
                }
                i++;
            }
        }
        else if (getChance() == 7)
            activePlayer.receiveMoney(100);
        else if (getChance() == 8)
            activePlayer.receiveMoney(20);
        else if (getChance() == 9)
            activePlayer.receiveMoney(100);
        else if (getChance() == 10)
            activePlayer.receiveMoney(-100);
        else if (getChance() == 11)
            activePlayer.receiveMoney(-150);
        else if (getChance() == 12)
            activePlayer.receiveMoney(25);
        else if (getChance() == 13)
        {
            int loss;

            loss = 0;
            for (int i = 0; i < activePlayer.getBelongings().size(); i++)
            {
                if (activePlayer.getBelongings().get(i).getClass().getName().equals("Property"))
                {
                    if (((Property) activePlayer.getBelongings().get(i)).getHouses() <= 4)
                        loss += ((Property) activePlayer.getBelongings().get(i)).getHouses() * 40;
                    else if (((Property) activePlayer.getBelongings().get(i)).getHouses() == 5)
                        loss += 115;
                }
            }
            activePlayer.receiveMoney(-loss);
        }
        else if (getChance() == 14)
            activePlayer.receiveMoney(10);
        else if (getChance() == 15)
            activePlayer.receiveMoney(100);
    }

    public CardMessages getCard()
    {
        return (this.card);
    }

    public String       getMessage()
    {
        drawCard();
        setCard(getChance());
        return (this.card.getMessage());
    }

    public void         setCard(int chance)
    {
        this.card = CardMessages.values()[getChance()];
    }
}
