
public class CommunityChest extends Deck
{
    enum CardMessages
    {
        CARD0 ("Advance to GO (Collect $200)."),
        CARD1 ("BANK ERROR IN YOUR FAVOR. COLLECT $200."),
        CARD2 ("DOCTOR'S FEES. PAY $50."),
        CARD3 ("FROM SALE OF STOCK YOU GET $45."),
        CARD4 ("GO DIRECTLY TO JAIL. DO NOT PASS GO. DO NOT COLLECT $200."),
        CARD5 ("GET OUT OF JAIL FREE. THIS CARD MAY BE KEPT UNTIL NEEDED, OR TRADED/SOLD."),
        CARD6 ("GRAND OPERA NIGHT. COLLECT $50 FROM EVERY PLAYER FOR OPENING NIGHT SEATS."),
        CARD7 ("XMAS FUND MATURES. COLLECT $100."),
        CARD8 ("INCOME TAX REFUND. COLLECT $20."),
        CARD9 ("LIFE INSURANCE MATURES. COLLECT $100."),
        CARD10 ("PAY HOSPITAL $100."),
        CARD11 ("PAY SCHOOL TAX OF $150."),
        CARD12 ("RECEIVE FOR SERVICES $25."),
        CARD13 ("YOU ARE ASSESSED FOR STREET REPAIRS: PAY $40 PER HOUSE AND $115 PER HOTEL YOU OWN."),
        CARD14 ("YOU HAVE WON SECOND PRIZE IN A BEAUTY CONTEST. COLLECT $10."),
        CARD15 ("YOU INHERIT $100.");

        private String message;
        private CardMessages(String message)
        {
            this.message = message;
        }
        private String getMessage(){
            return message;
        }
    }


    private CardMessages card;
    public CommunityChest(int square)
    {
        super(square);
        setTitle("Community Chest");
    }

    // TESTED by Al     (and changed by An:D)
    
    public void doAction(Player activePlayer)
    {

        if (getChance() == 0)
        {
            //System.out.println("ADVANCE TO GO (COLLECT $200).");
            while (activePlayer.getCoordinate() != 0)
                activePlayer.movePlayer(1);
        }
        else if (getChance() == 1)
        {
            //System.out.println("BANK ERROR IN YOUR FAVOR. COLLECT $200.");
            activePlayer.receiveMoney(200);
        }
        else if (getChance() == 2)
        {
            //System.out.println("DOCTOR'S FEES. PAY $50.");
            activePlayer.receiveMoney(-50);
        }
        else if (getChance() == 3)
        {
            //System.out.println("FROM SALE OF STOCK YOU GET $45.");
            activePlayer.receiveMoney(45);
        }
        else if (getChance() == 4)
        {
            //System.out.println("GO DIRECTLY TO JAIL. DO NOT PASS GO. DO NOT COLLECT $200.");
            activePlayer.setCoordinate(10);
            activePlayer.setIsPrisoned(true);
        }
        else if (getChance() == 5)
        {
            //System.out.println("GET OUT OF JAIL FREE. THIS CARD MAY BE KEPT UNTIL NEEDED, OR TRADED/SOLD.");
            activePlayer.setGetOutOfJail(true);
        }
        else if (getChance() == 6)
        {
            int i;

            //System.out.println("GRAND OPERA NIGHT. COLLECT $50 FROM EVERY PLAYER FOR OPENING NIGHT SEATS.");
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
        {
            //System.out.println("XMAS FUND MATURES. COLLECT $100.");
            activePlayer.receiveMoney(100);
        }
        else if (getChance() == 8)
        {
            //System.out.println("INCOME TAX REFUND. COLLECT $20.");
            activePlayer.receiveMoney(20);
        }
        else if (getChance() == 9)
        {
            //System.out.println("LIFE INSURANCE MATURES. COLLECT $100.");
            activePlayer.receiveMoney(100);
        }
        else if (getChance() == 10)
        {
            //System.out.println("PAY HOSPITAL $100.");
            activePlayer.receiveMoney(-100);
        }
        else if (getChance() == 11)
        {
            //System.out.println("PAY SCHOOL TAX OF $150.");
            activePlayer.receiveMoney(-150);
        }
        else if (getChance() == 12)
        {
            //System.out.println("RECEIVE FOR SERVICES $25.");
            activePlayer.receiveMoney(25);
        }
        else if (getChance() == 13)
        {
            //System.out.println("YOU ARE ASSESSED FOR STREET REPAIRS: PAY $40 PER HOUSE AND $115 PER HOTEL YOU OWN.");
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
        {
            //System.out.println("YOU HAVE WON SECOND PRIZE IN A BEAUTY CONTEST. COLLECT $10.");
            activePlayer.receiveMoney(10);
        } 
        else if (getChance() == 15) 
        {
            //System.out.println("YOU INHERIT $100.");
            activePlayer.receiveMoney(100);
        }
        activePlayer.enterMortgageLoop(null);
        
    }
    

    public CardMessages getCard()
    {
        return (this.card);
    }

    public void         setCard(int chance)
    {
        
        this.card = CardMessages.values()[getChance()];
    }

    public String getMessage()
    {
        drawCard();
        setCard(getChance());
        return (this.card.getMessage());
    }
}
