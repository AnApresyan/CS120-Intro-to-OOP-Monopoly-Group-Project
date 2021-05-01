public class Jail extends Square
{
    //for user interaction
    private int     userChoice;
    private String  userState;
    private boolean allowPay;
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
            if (userChoice == 1){       //pay
                activePlayer.receiveMoney(-50);
                activePlayer.setIsPrisoned(false);
                activePlayer.setDaysInJail(1);
                this.setMessage("You paid the fine and can throw the dice to leave jail.");
            
            }         
            // forcing the player to leave 
            /*if (activePlayer.getDaysInJail() > 3)
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
            }*/
            // using the getouttajail card
            // if (activePlayer.getGetOutOfJail())
            // {   // Al: on day 4, the player MUST pay the fee.
            //     boolean answer;
            //     System.out.println("Do you wanna spend your 'Get Out of Jail for Free' card? YES/NO");
            //     // the YES/NO window pops up!
            //     answer = false;
            //     // the YES/NO window terminates after receiving the response
                else if (userChoice == 2)        //use the card
                {
                    activePlayer.setGetOutOfJail(false);
                    activePlayer.setIsPrisoned(false);
                    activePlayer.setDaysInJail(1);
                   this.setMessage("You used up your 'Get out of Jail for Free' card. Now you can throw the dice and leave the Jail");
                    // Al: the player can be deprived of the ability to roll doubles twice in a row and get an additional move
                    // Utility.setDice(activePlayer.throwDice());
                    // if (activePlayer.holdsDoubles())
                    //     activePlayer.setDoublesInARow(activePlayer.getDoublesInARow() + 1);
                    // else
                    //     activePlayer.setDoublesInARow(0);
                    // activePlayer.movePlayer(activePlayer.getDice());
                    // Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
                    // return ;
                }
           // }
            // paying the fee VOLUNTARILY 
            /*if (activePlayer.getMoney() >= 50 && (activePlayer.getDaysInJail() == 1 || activePlayer.getDaysInJail() == 2))
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
            }*/
            // throwing doubles
            /*else if (userChoice == 3){
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
            }*/
            //activePlayer.enterMortgageLoop(null);
            
        }
        
    }

    public void checkTheStateSetMessage(Player activePlayer){
        allowPay = false;
        allowCard = false;
        allowThrow = true;
        
        if (activePlayer.getMoney() >= 50){
            this.allowPay = true;
        }
        if (activePlayer.getGetOutOfJail() && activePlayer.getDaysInJail() <= 3){
            this.allowCard = true;
        }
        if (activePlayer.getDaysInJail() > 3){
            this.allowThrow = false;
        }
        System.out.println("allowPay: " + this.allowPay);
        System.out.println("allowCard" + this.allowCard);
        System.out.println("allowThrow " + this.allowThrow);
        this.setMessage();
    }

    public void setMessage(){
        String pay = "";
        String mortgage = "-mortgage any of your owned properties to get out of Jail";
        String card = "";
        String throwDice = "";
        if (allowPay()){
            pay = "-pay 50$";
        }
        if (allowCard()){
            card = "-use your Get out of Jail card";
        }
        if (allowThrow()){
            throwDice = "-throw the dice";
        }
        this.setMessage("You can " +"\n" + pay + "\n" + mortgage + "\n" +card + "\n" + throwDice);
    }

    public boolean allowPay(){
        return allowPay;
    }
    public boolean allowCard(){
        return allowCard;
    }
    public boolean allowThrow(){
        return this.allowThrow;
    }

    public void setTheState(String state){
        this.userState = state;
    }
    public String getTheSate(){
        return this.userState;
    }
    public void setUserChoice(int choice){
        this.userChoice = choice;
    }
    public void setMessage(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        
        return message;
    }

}