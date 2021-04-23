public class CommunityChest extends Square
{
    public CommunityChest(int square)
    {
        super(square);
        setTitle("Community Chest");
    }

    // TESTED by Al
    public void doAction(Player activePlayer)
    {
        int chance;

        System.out.print("You're opening the Community Chance: ");
        // chance = (int)(Math.random() * 16) + 1;
        chance = 7;
        if (chance == 1)
        {
            System.out.println("ADVANCE TO GO (COLLECT $200).");
            while (activePlayer.getCoordinate() != 0)
                activePlayer.movePlayer(1);
        }
        else if (chance == 2)
        {
            System.out.println("BANK ERROR IN YOUR FAVOR. COLLECT $200.");
            activePlayer.receiveMoney(200);
        }
        else if (chance == 3)
        {
            System.out.println("DOCTOR'S FEES. PAY $50.");
            activePlayer.receiveMoney(-50);
        }
        else if (chance == 4)
        {
            System.out.println("FROM SALE OF STOCK YOU GET $45.");
            activePlayer.receiveMoney(45);
        }
        else if (chance == 5)
        {
            System.out.println("GO DIRECTLY TO JAIL. DO NOT PASS GO. DO NOT COLLECT $200.");
            activePlayer.setCoordinate(10);
            activePlayer.setIsPrisoned(true);
        }
        else if (chance == 6)
        {
            System.out.println("GET OUT OF JAIL FREE. THIS CARD MAY BE KEPT UNTIL NEEDED, OR TRADED/SOLD.");
            activePlayer.setGetOutOfJail(true);
        }
        else if (chance == 7)
        {
            int i;

            System.out.println("GRAND OPERA NIGHT. COLLECT $50 FROM EVERY PLAYER FOR OPENING NIGHT SEATS.");
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
        else if (chance == 8)
        {
            System.out.println("XMAS FUND MATURES. COLLECT $100.");
            activePlayer.receiveMoney(100);
        }
        else if (chance == 9)
        {
            System.out.println("INCOME TAX REFUND. COLLECT $20.");
            activePlayer.receiveMoney(20);
        }
        else if (chance == 10)
        {
            System.out.println("LIFE INSURANCE MATURES. COLLECT $100.");
            activePlayer.receiveMoney(100);
        }
        else if (chance == 11)
        {
            System.out.println("PAY HOSPITAL $100.");
            activePlayer.receiveMoney(-100);
        }
        else if (chance == 12)
        {
            System.out.println("PAY SCHOOL TAX OF $150.");
            activePlayer.receiveMoney(-150);
        }
        else if (chance == 13)
        {
            System.out.println("RECEIVE FOR SERVICES $25.");
            activePlayer.receiveMoney(25);
        }
        else if (chance == 14)
        {
            System.out.println("YOU ARE ASSESSED FOR STREET REPAIRS: PAY $40 PER HOUSE AND $115 PER HOTEL YOU OWN.");
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
        else if (chance == 15)
        {
            System.out.println("YOU HAVE WON SECOND PRIZE IN A BEAUTY CONTEST. COLLECT $10.");
            activePlayer.receiveMoney(10);
        } 
        else if (chance == 16) 
        {
            System.out.println("YOU INHERIT $100.");
            activePlayer.receiveMoney(100);
        }
        activePlayer.enterMortgageLoop(null);
    }
}
