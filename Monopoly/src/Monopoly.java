public class Monopoly 
{
    private Player[]    players;
    private Player      activePlayer;
    // hey Anahit, how are we gon' pass the dice value to Utilities, to calculate the rent?
    // each time we call throwDice, we must pass the value to Utils.
    private int[]       dice = new int[2];
    private Square[]    squares = new Square[40];
    private int         indexOfPlayer;

    public Monopoly(Player[] players)
    {
        setPlayers(players);
        indexOfPlayer = 1;
        // I'm thinking about splitting RailUtil into Railroad and Utility. if you agree,
        // then pls split them
        squares[0] = new GoTax(1);
        squares[1] = new Property(2);
        squares[2] = new Deck(3);
        squares[3] = new Property(4);
        squares[4] = new GoTax(5);
        squares[5] = new RailUtil(6);
        // ...
    }
    
    public void startGame(){
        printHeader();
        printMap();
        printFooter();
        while (true)
        {
            if (indexOfPlayer == players.length)
                this.indexOfPlayer = 0;
            this.activePlayer = players[indexOfPlayer];
            throwDice();
            // this.activePlayer.setCoordinate
        }
    }
    private void printHeader()
    {

    }
    private void printMap()
    {

    }
    private void printFooter()
    {

    }

    private void throwDice(){
        this.dice[0] = (int)(Math.random() * 6) + 1;
        this.dice[1] = (int)(Math.random() * 6) + 1;
    }
    public void setPlayers(Player[] players)
    {
        this.players = players.clone();
    }

}
