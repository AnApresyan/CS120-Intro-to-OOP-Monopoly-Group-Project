import java.util.ArrayList;


public class Monopoly 
{
    private ArrayList<Player>   players = new ArrayList<>();
    private Player              activePlayer;
    private int[]               dice = new int[2];
    private Square[]            squares = new Square[40];
    private int                 indexOfPlayer;

    public Monopoly(ArrayList<Player> players)
    {
        setPlayers(players);
        indexOfPlayer = 0;          //we should probably change this to allow each player throw dice and the one with the biggest dice value to be the first player

        //Hey Alex, is there a need for a board class? new Boards() for example, but I am not sure at all
        for (int i = 0; i < 40; i++){
            if (i == 0 || i == 4 || i == 12 || i == 28 || i == 38 || i == 20)//separate utility/tax//railroads
                squares[i] = new GoTax(i);              
            else if (i == 2 || i == 17 || i == 33)
                squares[i] = new CommunityChest(i);
            else if (i == 7 || i == 22 || i == 36)
                squares[i] = new Chance(i);
            else if (i == 10)
                squares[i] = new Jail(i); 
            else if (i == 30)
                squares[i] = new GoToJail(i);
            else if (i % 5 == 0)
                squares[i] = new RailRoad(i);
            else
                squares[i] = new Property(i);

        }
    }
    
    public void startGame(){
        printHeader();
        printMap();
        printFooter();
        while (true)
        {
            if (indexOfPlayer == players.size())
                this.indexOfPlayer = 0;
            this.activePlayer = players.get(indexOfPlayer);
            Utility.setDice(throwDice());
            activePlayer.movePlayer(dice);
            squares[activePlayer.getCoordinate()].doAction(dice, activePlayer);
            if (dice[0] != dice[1])
                indexOfPlayer++;
            printHeader();
            printMap();
            printFooter();
            if (players.size() == 1)
                break;
        }
        System.out.println("Congratulations!");
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

    private int throwDice(){
        this.dice[0] = (int)(Math.random() * 6) + 1;
        this.dice[1] = (int)(Math.random() * 6) + 1;
        return (this.dice[0] + this.dice[1]);
    }

    public void setPlayers(ArrayList<Player> players)
    {
        this.players = players;             
    }

}
