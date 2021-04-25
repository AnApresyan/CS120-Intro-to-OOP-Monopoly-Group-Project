import java.util.ArrayList;
/**
 * >>>>> CHANGELOG <<<<<
 * MONOPOLY 0.1.0           04/13/2021
 * An & Al:
 * 1) created the whole hierarchy of the game;
 * 2) created Player class.
 * 
 * MONOPOLY 0.2.0           04/14/2021
 * An & Al:
 * 1) implemented the main gameplay loop;
 * 2) created the rest of trivial classes.
 * 
 * MONOPOLY 0.2.1           04/15/2021
 * Al:
 * 1) added getRent() computation method for Utility & Railroad;
 * 2) improved overall performance of the gameplay loop;
 * 3) implemented detection of Monopoly and count of owned props of the same color.
 * An:
 * 3) implemented mortgaging;
 * 4) implemented canBeMortgaged() and the bankruptcy loop.
 * 
 * MONOPOLY 0.3.0.          04/18/2021
 * Al:
 * 1) moved dice[2] to Player;
 * 2) created holdsDoubles() for Player class;
 * 3) added getters and setters here and there;
 * 4) added "Go To Jail" functionality and changed the startGame loop accordingly;
 * 5) cleaned up the code a bit;
 * 6) added daysInJail int in Player to force the player leave the jail after 3 days;
 * 7) fixed a bug causing the player omit GO each time a new round around the map is passed;
 * 8) modified movePlayer() such that if GO is passed, the player is granted $200.
 * 
 * MONOPOLY 0.4.0           04/19/2021
 * An:
 * 1) added basic Swing support;
 * 2) added a tester for play window; added basic interface;
 * 3) added logo on the starting screen; added START button.
 * 
 * MONOPOLY 0.4.1           04/20/2021
 * An:
 * 1) fixed an issue causing the content not to display until the window is resized.
 * 
 * MONOPOLY 0.5.0           04/21/2021
 * Al: 
 * 1) movePlayer() now receives an int;
 * 2) implemented the functionality of Community Chest and Chance squares;
 * 3) movePlayer() improved further such that the renewal of coordinates depenends on getCoordinate();
 * 4) scrapped index instance variable in Player class (was unused);
 * 5) moved the board setup into separate static Board class to access it everywhere.
 * An:
 * 6) overall improvement of interface.
 * 
 * MONOPOLY 0.6.0           04/22/2021
 * An:
 * 1) added the basic grid for interface;
 * 2) created a visual representation of the playboard;
 * 3) created buttons that correspond to each square;
 * 4) created basic layout of the second infobox;
 * 5) overall polishing of the interface;
 * Al:
 * 6) added functionality of the Community Chest;
 * 7) made the board static to be able to access it everywhere;
 * 8) made the players static to be able to access them everywhere;
 * 9) implemented housing mechanics (RAW).
 * 
 * MONOPOLY 0.7.0           04/23/2021
 * Al:
 * 1) commenced a major, comprehensive testing of all existing methods;
 * 2) enterMortgageLoop() is now a separate method;
 * 3) rent of unimproved monopolized properties is now doubled;
 * 4) fixed an issue causing one of the inner mortgage loops to run endlessly;
 * 5) implemented removePlayer() method, with passing props when losing;
 * 6) added a price of $200 to Railroad class members;
 * 7) Chance and Community Chest now properly trigger the mortgage loop;
 * 8) mortgage loop now offers an option to sell houses;
 * 9) added Utilities' and Railroads' coordinates to the COLORS array;
 * An:
 * 1) Completely changed the board structure:D ;
 * 2) Added some colors to the board;
 * 3) Added the logic for the information of titleDeeds when clicked
 * 4) Changed getRent() in Property, Utility, and Railroad not to throw null pointer Exception
 * 5) Not to forget: need to change the titleDeed layout, do the getRent() methods need to return 0 if the squares do not have Owner (Railroad, Utility)
 * P.S. I do not like writing, but it doesn't mean that I do not do work:D
 */
public class Monopoly 
{
    public static final int    JAIL_FINE = 50;

    private static ArrayList<Player>    players = new ArrayList<>();
    public Player                      activePlayer;               //public for testing
    private int                         indexOfPlayer;

    public Monopoly(ArrayList<Player> players)
    {
        new Board();
        setPlayers(players);
        indexOfPlayer = 0; 
        this.activePlayer = players.get(0);         // An: we should probably change this to allow each player throw dice and the one with the biggest dice value to be the first player
                                                    // Al: good idea, but let's leave it for later, if we have time 
    }
    
    // public String infoActivePlayer(){
    //     return activePlayer.toString();
    // }

    public void startGame(){
        // printHeader();
        // printMap();
        // printFooter();
        // while (true)
        // {
            // if (indexOfPlayer == players.size())
            //     this.indexOfPlayer = 0;
            // this.activePlayer = players.get(indexOfPlayer);
            // Al: added a check on throwing the dice to move for if the player's prisoned.
            // if they are, then the dice will not be thrown, and the player won't move.
            if (!(activePlayer.isPrisoned()))
                Utility.setDice(activePlayer.throwDice());
            // Al: updated movePlayer() such that if the player's prisoned, the coords do not change.
            activePlayer.movePlayer(activePlayer.getDice());
            // Al: having the dice not thrown, the player not moved, and the coords = 10 after landing 
            // on "Go To Jail", the jail's doAction() will fire asking for more options 
            Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
            // if (!(activePlayer.holdsDoubles()))
            //     indexOfPlayer++;
            // printHeader();
            // printMap();
            // printFooter();
            if (players.size() == 1)
                System.out.println("Game Over");
                //break;
    //     }
    //     System.out.println("Congratulations, " + players.get(0).getName() + "! You are the ultimate monopolist!");
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

    public void setPlayers(ArrayList<Player> newplayers)
    {
        players = newplayers;             
    }

    public static ArrayList<Player> getPlayers()
    {
        return (players);
    }

    public boolean ifPlayerHoldsDoubles(){
        return this.activePlayer.holdsDoubles();
    }
    public void changePlayer(){
        indexOfPlayer++;
        if (indexOfPlayer == players.size())
                this.indexOfPlayer = 0;
        this.activePlayer = players.get(indexOfPlayer);
    }
}
