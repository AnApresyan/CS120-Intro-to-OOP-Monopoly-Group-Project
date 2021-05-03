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
 * CLASSES TESTED: Board, Buyable, Chance, Chest, GOTaxFree, GoToJail, Railroad, Utility, Square
 * CLASSES PENGING TESTING: Jail, Property (housing features)
 * An:
 * 10) Completely changed the board structure:D ;       // Al: haha it must've happened some day...
 * 11) Added some colors to the board;
 * 12) Added the logic for the information of titleDeeds when clicked
 * 13) Changed getRent() in Property, Utility, and Railroad not to throw null pointer Exception
 * 14) Not to forget: need to change the titleDeed layout, do the getRent() methods need to return 0 if the squares do not have Owner (Railroad, Utility)
 * P.S. I do not like writing, but it doesn't mean that I do not do work:D  // Al: yes dear i noticed xdddd no worries, everyone knows you do 10 times more you write about
 * 
 *                          04/24/2021: Alexander & Anahit had to take a break to rest and do some Calculus...
 * 
 * MONOPOLY 0.8.0           04/25/2021
 * Al:
 * 1) testing of methods completely finished. the logic is ready to be connected with visuals;
 * 2) improved the double-rolling mechanics: new static instance variable in Monopoly class was created;
 * 3) rolling doubles 3 times in a row now results in imprisonment;
 * 4) implemented trading (RAW);
 * 5) implemented auction (RAW);
 * 6) fixed an issue causing a removed player's properties to remain owned by them when failing to break 
 *    out of the mortgageLoop by taking a Chance/Chest card;
 * An:
 * 7) added buttons THROW_DICE and DONE, and tied them to corresponding methods;
 * 8) made the info header refresh each time DONE is pressed;
 * 9) overall improvement of the UI.
 * 
 * MONOPOLY 0.9.0           04/26/2021
 * Al:
 * 1) added dummy images for sprites;
 * An:
 * 2) fixed an issue causing a player's name to be displayed in each title deed card as the owner after a
 *    property is bought for the first time;
 * 3) fixed an issue causing the player's turn not to end;
 * 4) fixed an issue causing the balance and coordinate refresh only after DONE is pressed;
 * 5) properly connected Buyable to the interface. the program now correctly handles YES and NO input through
 *    the corresponding buttons;
 * 6) properly connected Chance&Chest to the interface with corresponding message pop-ups.
 * 
 * MONOPOLY 1.0.0           04/27/2021
 * An:
 * 1) created an enum for cards in Chance&Chest holding messages to easily display them;
 * 2) added a getMessage() to collect the card desriptions in the Square class;
 * 3) overall improvement of interface connection.
 * 
 * MONOPOLY 1.0.1           04/28/2021
 * Al:
 * 1) implemented liftMortgage() in the Player class;
 * 2) lower-cased the card messages so that Anahit doesn't get annoyed by capsed letters;
 * An:
 * 3) merged GoToJail class with GOTaxFree class;
 * 
 * MONOPOLY 1.0.2           04/29/2021
 * Al:
 * 1) implememted the sprites (RAW);
 * An:
 * 2) changed the size of certain windows and edited the spacing.
 * 3) reworked the MainWindow and Monopoly classes so that Monopoly is the game manager now;
 * 4) the logic now is evenly implemented both in MainWindow and in Monopoly.
 * 
 * MONOPOLY 1.0.3           04/30/2021
 * An:
 * 1) properly connected "Lift Mortgage", "Mortgage", "Buy House", "Sell House" to the interface.
 * 
 * MONOPOLY 1.0.4           05/01/2021
 * An:
 * 1) implemented a panel for the owned properties;
 * 2) a popUpWIndow each time a button from that panel is clicked will show up with different options;
 * 3) I don't completely remember the methods I changed, but I commented the
 *    loops for build/destroy houses and mortgage/liftMortgage;
 * 4) Instead, added/changed those methods, as well as the methods canBeImproved, canBeMortgaged and the others;
 * 5) Moved some of the methods from Player to the Property or Buyable, such as canBeImproved, etc, because it's not 
 *    the player who can be improved, but the property:D
 * 6) Please appreciate my work, thanks:D   // Al: done. you cannot believe what pleasure it is to work with you in a team. love you endlessly <3
 * Al:
 * 7) info in the window pop-ups is now more detailed (mortgage state, houses, housePrice added);
 * 8) fixed an issue causing the title deeds not be refreshed after a button in the pop-ups is pressed;
 * 9) fixed an issue causing Erect House button stay active after 5 houses have been built;
 * 10) numberOfPlayers is now correctly accessed and refreshed;
 * 11) auction pop-up created;
 * An:
 * Thanks for the kind words:D
 * 12) Separated the windows in the beginning from the MainWindow to make it look more organized;
 * 13) Along with that got rid of the reduntant variable numOfNames.
 * 
 * MONOPOLY 1.1.0           05/02/2021
 * Al:
 * 1) AuctionPopUp created; NO now properly start the auction;
 * 2) fixed an issue causing the winner pay 2 times the bid;
 * 3) fixed an issue causing the labels not get refreshed properly in bidders list;
 * 4) made the auction window unclosable by the user;
 * 5) the auction now automatically eliminates players who don't have enough money;
 * An:
 * 6) fixed an issue causing the players not to get added to bidders when their balance is less than
 *    the price of the property being auctioned;
 * 7) fixed an issue causing the belongings buttons display incorrectly on School 42's IMacs;
 * Al:
 * 8) fixed an issue causing severe sync problems with the players list in infoTop (now utilizing arraylist
 *    of JLabels instead of an array);
 * An:
 * 9) added winning screen with options to exit and replay;
 * 10) added basic support for the mortgage loop;
 * 11) added the removal of a lost player;
 * 12) fixed an issue allowing Player 1 pass the turn right away because Done is enabled from the beginning;
 * 13) fixed an issue causing Done stay enabled upon landing on Buyable and receiving YES/NO input;
 * 14) fixed an issue causing Done stay enabled when OK is prompted during Chance&Chest interaction;
 * 15) fixed an issue causing action buttons to light up incorrectly during a Jail interaction;
 * 16) added some visuals to the board.
 * 
 * MONOPOLY 1.2.0           05/03/2021
 * Al:
 * 1) the dices rolled are now displayed on the playing screen;
 * 2) added a Trade button which now allows to initialize trade. 
 * 
 * KNOWN ISSUES:
 * 1. sprites are duplicated after GoToJail interaction
 */
public class Monopoly 
{
    private static ArrayList<Player>    players = new ArrayList<>();
    private Player                      activePlayer;
    private static ArrayList<Player>    bidders;
    private Player                      activeBidder;
    private int                         indexOfPlayer;
    private int                         indexOfBidder;
    private boolean                     moveToJail;
    private int                         choice;
    private Player                      tradee;
    
    public Monopoly(ArrayList<Player> players)
    {
        new Board();
        setPlayers(players);
        indexOfPlayer = 0;
        indexOfBidder = 0;
        this.activePlayer = players.get(0);
        this.activeBidder = this.activePlayer;
        this.choice = 0; 
        
        // players.get(1).setMoney(1);
        // players.get(1).getBelongings().add((Buyable)Board.getSquares()[39]);
        // ((Buyable)Board.getSquares()[39]).setOwner(players.get(1));
        //((Buyable)Board.getSquares()[3]).setIsMortgaged(true);
    }
                                                    // An: we should probably change this to allow each player throw dice and the one with the biggest dice value to be the first player
                                                    // Al: good idea, but let's leave it for later, if we have time 
    


    public boolean getActivePlayerState(){
        return this.activePlayer.state();
    }

    public void build(Square property){
        if (property.getClass().getName().equals("Property")){
            activePlayer.erectHouse((Property)property);  
        }
        
    }

    public void destroy(Square property){
        if (property.getClass().getName().equals("Property")){
            activePlayer.degradeProperty((Property)property);
        }
    }

    public void mortgage(Square property){
        if (property instanceof Buyable){
            activePlayer.mortgageProperty((Buyable)property);
        }
    }

    public void liftMortgage(Square property){
        if(property instanceof Buyable){
            activePlayer.liftMortgage((Buyable)property);
        }
    }

    public void play(){
        setMessage();   //just added
        
        Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
        System.out.println("Player coordinates: " + activePlayer.getCoordinate());
    }

    public void play(boolean bool)
    {
        if (Board.getSquares()[activePlayer.getCoordinate()] instanceof Buyable)
            ((Buyable)Board.getSquares()[activePlayer.getCoordinate()]).setWantsToBuy(bool);
        play();
    }

    public void play(int choice){
        if (Board.getSquares()[activePlayer.getCoordinate()].getClass().getName().equals("Jail") ){
            ((Jail)Board.getSquares()[activePlayer.getCoordinate()]).setUserChoice(choice);
        }
        play();
    }



    public boolean ifPlayerIsPrisoned()
    {
        System.out.println(activePlayer.isPrisoned());
        return activePlayer.isPrisoned();
    }

    public void setMessage(){
        if (activePlayer.getCoordinate() == 10){
            ((Jail)Board.getSquares()[activePlayer.getCoordinate()]).checkTheStateSetMessage(activePlayer);
            System.out.println("setMessageOfJail");
        }
        else if (Board.getSquares()[activePlayer.getCoordinate()] instanceof Buyable){
            ((Buyable)Board.getSquares()[activePlayer.getCoordinate()]).setMessage(activePlayer);
        }
    }

    public void removePlayer(){
        if (Board.getSquares()[activePlayer.getCoordinate()] instanceof Buyable){
            if (((Buyable)Board.getSquares()[activePlayer.getCoordinate()]).getOwner() != null){
                for (Buyable belonging : activePlayer.getBelongings()){
                    ((Buyable)Board.getSquares()[activePlayer.getCoordinate()]).getOwner().getBelongings().add(belonging);
                    belonging.setOwner(((Buyable)Board.getSquares()[activePlayer.getCoordinate()]).getOwner());
                }
            }
            else{
                for (Buyable belonging : activePlayer.getBelongings())
                    belonging.setOwner(null);
            }
        }
    
        for (Player player : players){
            if (player.equals(activePlayer)){
                players.remove(player);
                this.indexOfPlayer--;
                break;
            }
        }
    }
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
            
            moveToJail = false;
            if (!(activePlayer.isPrisoned()))
                Utility.setDice(activePlayer.throwDice());
            else
            {
                //System.out.println("You have to throw dices now.");
                activePlayer.throwDice();
                if (activePlayer.holdsDoubles())
                {
                    
                    activePlayer.setIsPrisoned(false);
                    activePlayer.setDaysInJail(1);
                    //System.out.println("You rolled doubles and are free to go.");
                    activePlayer.movePlayer(activePlayer.getDice());
                    Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
                    System.out.println("Player coordinates: " + activePlayer.getCoordinate());
                    return ;
                }
                //System.out.println("You failed to roll doubles. See you on the next turn!");
                activePlayer.setDaysInJail(activePlayer.getDaysInJail() + 1);
            }
           
            System.out.println("Double in a row: " + activePlayer.getDoublesInARow());
            if (activePlayer.getDoublesInARow() == 3)
            {
                this.activePlayer.setIsPrisoned(true);
                this.activePlayer.setDoublesInARow(0);
                activePlayer.setCoordinate(10);
                this.moveToJail = true;
                return ;
            }
            // Al: updated movePlayer() such that if the player's prisoned, the coords do not change.
            activePlayer.movePlayer(activePlayer.getDice());
            // Al: having the dice not thrown, the player not moved, and the coords = 10 after landing 
            // on "Go To Jail", the jail's doAction() will fire asking for more options 
            //Board.getSquares()[activePlayer.getCoordinate()].doAction(activePlayer);
            // if (!(activePlayer.holdsDoubles()))
            //     indexOfPlayer++;
            // printHeader();
            // printMap();
            // printFooter();
            // if (activePlayer.getDoublesInARow() == 3)
            // {
            //     activePlayer.setIsPrisoned(true);
            //     activePlayer.setCoordinate(10);
            //     activePlayer.setDoublesInARow(0);
            // }
                //break;
    //     }
    //     System.out.println("Congratulations, " + players.get(0).getName() + "! You are the ultimate monopolist!");
    }

    public boolean activePlayerWon()
    {
       return (players.size() == 1);
    }

    public boolean getMoveToJail()
    {
       return this.moveToJail;
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

    public void changePlayer()
    {
        if (activePlayer.getDoublesInARow() == 0 || (activePlayer.getDoublesInARow() != 0 && activePlayer.isPrisoned()))
        {
            indexOfPlayer++;
            activePlayer.setDoublesInARow(0);
        }
        //else if (activePlayer.getDoublesInARow() != 0 && activePlayer.)
        if (indexOfPlayer == players.size())
        {
            this.indexOfPlayer = 0;
            for (Player p : players)
                p.nullifyDice();
        }
        System.out.println("The index of player: " + indexOfPlayer);            //for testing
        this.activePlayer = players.get(indexOfPlayer);
        this.activeBidder = this.activePlayer;
        this.indexOfBidder = this.indexOfPlayer;
    }

    public void changeBidder()
    {
        System.out.println("STARTED CHANGING BIDDER. INDEX OF BIDDER: " + this.indexOfBidder);
        this.indexOfBidder++;
        if (indexOfBidder == bidders.size())
            this.indexOfBidder = 0;
        this.activeBidder = bidders.get(indexOfBidder);
        System.out.println("bidder now: " + this.activeBidder.toString());
        System.out.println("ENDED CHANGING BIDDER. INDEX OF BIDDER: " + this.indexOfBidder);
    }

    public void setBidders(int coordinate)
    {
        bidders = new ArrayList<>();
        //if (this.activeBidder.getMoney() >= ((Buyable) Board.getSquares()[coordinate]).getPrice())
        bidders.add(this.activeBidder);
        for (Player p : players)
            if (!(p.equals(this.activeBidder)))
                bidders.add(p);
        // Al: testing
        System.out.println("the bidders:");
        for (Player b : bidders)
            System.out.println(b);
    }

    public void removeActiveBidder()
    {
        for (Player b : bidders)
            if (b.equals(activeBidder))
            {
                System.out.println("Removed " + b + " from bidders");
                bidders.remove(b);
                break ;
            }
        this.indexOfBidder--;
        // Al: testing
        System.out.println("the bidders after removal:");
        for (Player b : bidders)
            System.out.println(b);
        changeBidder();
    }

    public void nullifyBidders()
    {
        bidders = null;
    }

    public ArrayList<Player> getBidders()
    {
        return (bidders);
    }

    public int getActivePlayerCoordinate()
    {
        return (this.activePlayer.getCoordinate());
    }

    public Player getActivePlayer()
    {
        return (this.activePlayer);
    }

    public Player getActiveBidder()
    {
        return (this.activeBidder);
    }

    public int getChoice()
    {
        return (this.choice);
    }

    public void setChoice(int choice)
    {
        this.choice = choice;
    }

    public int getActivePlayerIndex()
    {
        return (this.indexOfPlayer);
    }

    public int getActiveBidderIndex()
    {
        return (this.indexOfBidder);
    }

    public Player getTradee()
    {
        return (this.tradee);
    }

    public void setTradee(Player tradee)
    {
        this.tradee = tradee;
    }
    public int  getNumberOfPlayers()
    {
        return (players.size());
    }
}
