public class Jail extends Square
{
    public Jail(int coordinate)
    {
        super(coordinate);
        setTitle("Jail");
    }
    public void doAction(Player activePlayer)
    {
        if (activePlayer.isPrisoned())
        {
            // forcing the player to leave 
            if (activePlayer.getDaysInJail() > 3)
            {
                System.out.println("You have to pay the fee now!");
                activePlayer.setMoney(activePlayer.getMoney() - Monopoly.JAIL_FINE);
                activePlayer.setIsPrisoned(false);
                activePlayer.setDaysInJail(1);
                System.out.println("You paid the fine and are free to go.");
                // Al: here we have to prevent the passing of the turn to the next player.
                return ;
            }
            // using the getouttajail card
            if (activePlayer.getGetOutOfJail())
            {   // Al: on day 4, the player MUST pay the fee.
                boolean answer;
                System.out.println("Do you wanna spend your 'Get Out of Jail for Free' card? YES/NO");
                // the YES/NO window pops up!
                answer = false;
                // the YES/NO window terminates after receiving the response
                if (answer)
                {
                    activePlayer.setGetOutOfJail(false);
                    activePlayer.setIsPrisoned(false);
                    activePlayer.setDaysInJail(1);
                    System.out.println("You used up your 'Get out of Jail for Free' card.");
                    // Al: here we have to prevent the passing of the turn to the next player.
                    return ;
                }
            }
            // paying the fee VOLUNTARILY 
            if (activePlayer.getMoney() >= Monopoly.JAIL_FINE && (activePlayer.getDaysInJail() == 1 || activePlayer.getDaysInJail() == 2))
            {
                boolean answer;
                System.out.println("Do you wanna pay the fine of $" + Monopoly.JAIL_FINE + "? YES/NO");
                // the YES/NO window pops up!
                answer = false;
                // the YES/NO window terminates after receiving the response
                if (answer)
                {
                    activePlayer.setMoney(activePlayer.getMoney() - Monopoly.JAIL_FINE);
                    activePlayer.setIsPrisoned(false);
                    activePlayer.setDaysInJail(1);
                    System.out.println("You paid the fine and are free to go.");
                    // Al: here we have to prevent the passing of the turn to the next player.
                    return ;
                }
            }
            // throwing doubles
            System.out.println("You have to throw dices now.");
            activePlayer.throwDice();
            if (activePlayer.holdsDoubles())
            {
                activePlayer.setIsPrisoned(false);
                activePlayer.setDaysInJail(1);
                System.out.println("You rolled doubles and are free to go.");
                // Al: here we have to prevent the passing of the turn to the next player.
                // ALSO, we have to preserve the dice value by not letting the player
                // throw the dices again! omg this is nuts
                return ;
            }
            System.out.println("You failed to roll doubles. See you on the next turn!");
            activePlayer.setDaysInJail(activePlayer.getDaysInJail() + 1);
        }
    }

}