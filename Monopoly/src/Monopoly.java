

public class Monopoly 
{
    private Player[]    players;
    private Player      activePlayer;
    private int[]       dice = new int[2];
    private Square[]    squares = new Square[40];
    private int         indexOfPlayer;

    public Monopoly(Player[] players)
    {
        setPlayers(players);
        indexOfPlayer = 1;

        // // setting up the squares
        // squares[0] = new
        // squares[1]
        // squares[2]
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
            this.activePlayer.set
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
