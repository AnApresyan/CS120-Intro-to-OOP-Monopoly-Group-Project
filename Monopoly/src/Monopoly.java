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
    
    public void setPlayers(Player[] players)
    {
        this.players = players.clone();
    }
}
