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
                System.out.println("You have to pay the fine now!");
                activePlayer.receiveMoney(-50);
                activePlayer.setIsPrisoned(false);
                activePlayer.setDaysInJail(1);
                System.out.println("You paid the fine and are free to go.");

                Utility.setDice(activePlayer.throwDice());
                if (activePlayer.holdsDoubles())
                    activePlayer.setDoublesInARow(activePlayer.getDoublesInARow() + 1);
                else
                    activePlayer.setDoublesInARow(0);
                activePlayer.movePlayer(activePlayer.getDice());
                Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
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
                    // Al: the player can be deprived of the ability to roll doubles twice in a row and get an additional move
                    Utility.setDice(activePlayer.throwDice());
                    if (activePlayer.holdsDoubles())
                        activePlayer.setDoublesInARow(activePlayer.getDoublesInARow() + 1);
                    else
                        activePlayer.setDoublesInARow(0);
                    activePlayer.movePlayer(activePlayer.getDice());
                    Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
                    return ;
                }
            }
            // paying the fee VOLUNTARILY 
            if (activePlayer.getMoney() >= 50 && (activePlayer.getDaysInJail() == 1 || activePlayer.getDaysInJail() == 2))
            {
                boolean answer;
                System.out.println("Do you wanna pay $50 to get free? YES/NO");
                // the YES/NO window pops up!
                answer = false;
                // the YES/NO window terminates after receiving the response
                if (answer)
                {
                    activePlayer.receiveMoney(-50);;
                    activePlayer.setIsPrisoned(false);
                    activePlayer.setDaysInJail(1);
                    System.out.println("You paid the fine and are free to go.");
                    // Al: the player can be deprived of the ability to roll doubles twice in a row and get an additional move
                    Utility.setDice(activePlayer.throwDice());
                    if (activePlayer.holdsDoubles())
                        activePlayer.setDoublesInARow(activePlayer.getDoublesInARow() + 1);
                    else
                        activePlayer.setDoublesInARow(0);
                    activePlayer.movePlayer(activePlayer.getDice());
                    Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
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
                activePlayer.movePlayer(activePlayer.getDice());
                Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
                return ;
            }
            System.out.println("You failed to roll doubles. See you on the next turn!");
            activePlayer.setDaysInJail(activePlayer.getDaysInJail() + 1);
        }
    }
    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}