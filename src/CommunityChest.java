import java.util.Random;

public class CommunityChest extends Square implements Deck
{
    private enum CardMessages{
        CARD0 ("ADVANCE TO GO (COLLECT $200)."),
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
        private CardMessages(String message){
            this.message = message;
        }


        private String getMessage(){
            return this.message;
        }
    }

    public void randomCardGenerator() {
        chance = new Random().nextInt(CardMessages.values().length);
        this.card =  CardMessages.values()[chance];
        System.out.println("Chance random number: " + chance);
    }


    //private String message;
    private CardMessages card;
    private int chance;

    public CommunityChest(int square)
    {
        super(square);
        //this.message = "";
        setTitle("Community Chest");
    }


    // TESTED by Al     (and change by An:D)

    public void randomCard(){
        System.out.print("You're opening the Community Chance: ");
        this.chance = (int)(Math.random() * 16) + 1;
    }
    
    public void doAction(Player activePlayer)
    {  

        if (this.card.name().equals("CARD0"))
        {
            //System.out.println("ADVANCE TO GO (COLLECT $200).");
            //setMessage("ADVANCE TO GO (COLLECT $200).");
            while (activePlayer.getCoordinate() != 0)
                activePlayer.movePlayer(1);
        }
        else if (this.card.name().equals("CARD1"))
        {
            //System.out.println("BANK ERROR IN YOUR FAVOR. COLLECT $200.");
            //setMessage("BANK ERROR IN YOUR FAVOR. COLLECT $200.");
            activePlayer.receiveMoney(200);
        }
        else if (this.card.name().equals("CARD2"))
        {
            //System.out.println("DOCTOR'S FEES. PAY $50.");
            //setMessage("DOCTOR'S FEES. PAY $50.");
            activePlayer.receiveMoney(-50);
        }
        else if (this.card.name().equals("CARD3"))
        {
            //System.out.println("FROM SALE OF STOCK YOU GET $45.");
            //setMessage("FROM SALE OF STOCK YOU GET $45.");
            activePlayer.receiveMoney(45);
        }
        else if (this.card.name().equals("CARD4"))
        {
            //System.out.println("GO DIRECTLY TO JAIL. DO NOT PASS GO. DO NOT COLLECT $200.");
            //setMessage("GO DIRECTLY TO JAIL. DO NOT PASS GO. DO NOT COLLECT $200.");
            activePlayer.setCoordinate(10);
            activePlayer.setIsPrisoned(true);
        }
        else if (this.card.name().equals("CARD5"))
        {
            //System.out.println("GET OUT OF JAIL FREE. THIS CARD MAY BE KEPT UNTIL NEEDED, OR TRADED/SOLD.");
            //setMessage("GET OUT OF JAIL FREE. THIS CARD MAY BE KEPT UNTIL NEEDED, OR TRADED/SOLD.");
            activePlayer.setGetOutOfJail(true);
        }
        else if (this.card.name().equals("CARD6"))
        {
            int i;

            //System.out.println("GRAND OPERA NIGHT. COLLECT $50 FROM EVERY PLAYER FOR OPENING NIGHT SEATS.");
            //setMessage("GRAND OPERA NIGHT. COLLECT $50 FROM EVERY PLAYER FOR OPENING NIGHT SEATS.");
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
        else if (this.card.name().equals("CARD7"))
        {
            //System.out.println("XMAS FUND MATURES. COLLECT $100.");
            //setMessage("XMAS FUND MATURES. COLLECT $100.");
            activePlayer.receiveMoney(100);
        }
        else if (this.card.name().equals("CARD8"))
        {
            //System.out.println("INCOME TAX REFUND. COLLECT $20.");
            //setMessage("INCOME TAX REFUND. COLLECT $20.");
            activePlayer.receiveMoney(20);
        }
        else if (this.card.name().equals("CARD9"))
        {
            //System.out.println("LIFE INSURANCE MATURES. COLLECT $100.");
            //setMessage("LIFE INSURANCE MATURES. COLLECT $100.");
            activePlayer.receiveMoney(100);
        }
        else if (this.card.name().equals("CARD10"))
        {
            //System.out.println("PAY HOSPITAL $100.");
            //setMessage("PAY HOSPITAL $100.");
            activePlayer.receiveMoney(-100);
        }
        else if (this.card.name().equals("CARD11"))
        {
            //System.out.println("PAY SCHOOL TAX OF $150.");
            //setMessage("PAY SCHOOL TAX OF $150.");
            activePlayer.receiveMoney(-150);
        }
        else if (this.card.name().equals("CARD12"))
        {
            //System.out.println("RECEIVE FOR SERVICES $25.");
            //setMessage("RECEIVE FOR SERVICES $25.");
            activePlayer.receiveMoney(25);
        }
        else if (this.card.name().equals("CARD13"))
        {
            //System.out.println("YOU ARE ASSESSED FOR STREET REPAIRS: PAY $40 PER HOUSE AND $115 PER HOTEL YOU OWN.");
            //setMessage("YOU ARE ASSESSED FOR STREET REPAIRS: PAY $40 PER HOUSE AND $115 PER HOTEL YOU OWN.");
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
        else if (this.card.name().equals("CARD14"))
        {
            //System.out.println("YOU HAVE WON SECOND PRIZE IN A BEAUTY CONTEST. COLLECT $10.");
            //setMessage("YOU HAVE WON SECOND PRIZE IN A BEAUTY CONTEST. COLLECT $10.");
            activePlayer.receiveMoney(10);
        } 
        else if (this.card.name().equals("CARD15")) 
        {
            //System.out.println("YOU INHERIT $100.");
            //setMessage("YOU INHERIT $100.");
            activePlayer.receiveMoney(100);
        }
        activePlayer.enterMortgageLoop(null);
        //System.out.println(this.message);
    }
    


    // public void setMessage(String message){
    //     this.message = message;
    // }
    public String getMessage(){
        return this.card.getMessage();
    }
}
