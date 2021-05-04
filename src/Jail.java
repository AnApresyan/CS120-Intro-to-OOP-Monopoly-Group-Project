public class Jail extends Square
{
    private int     userChoice;
    private String  userState;
    private boolean allowCard;
    private boolean allowThrow;


    private String  message;


    public Jail(int coordinate)
    {
        super(coordinate);
        setTitle("Jail");
    }

    public void doAction(Player activePlayer)
    {
        if (activePlayer.isPrisoned())
        {
            if (userChoice == 1)
            {
                activePlayer.receiveMoney(-50);
                activePlayer.setIsPrisoned(false);
                activePlayer.setDaysInJail(1);
                this.setMessage("You paid the fine and can throw the dice to leave the jail.");
            }
            else if (userChoice == 2)
            {
                activePlayer.setGetOutOfJail(false);
                activePlayer.setIsPrisoned(false);
                activePlayer.setDaysInJail(1);
               this.setMessage("You used up your 'Get out of Jail for Free' card. Now you can throw the dice and leave the jail.");
            }
        }
    }

    public void checkTheStateSetMessage(Player activePlayer)
    {
        allowCard = false;
        allowThrow = true;
        if (activePlayer.getGetOutOfJail() && activePlayer.getDaysInJail() <= 3)
            this.allowCard = true;
        if (activePlayer.getDaysInJail() > 3)
            this.allowThrow = false;
        this.setMessage();
    }

    public void setMessage()
    {
        String pay = "• pay 50$";
        String card = "";
        String throwDice = "";
        if (allowCard())
            card = "• use your Get out of Jail card";
        if (allowThrow())
            throwDice = "• throw the dice";
        this.setMessage("You can\n" + pay + "\n" + card + "\n" + throwDice);
    }

    public boolean allowCard()
    {
        return (this.allowCard);
    }

    public boolean allowThrow()
    {
        return (this.allowThrow);
    }

    public String getTheSate()
    {
        return (this.userState);
    }

    @Override
    public String getMessage()
    {    
        return this.message;
    }

    public void setTheState(String state)
    {
        this.userState = state;
    }

    public void setUserChoice(int choice)
    {
        this.userChoice = choice;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}