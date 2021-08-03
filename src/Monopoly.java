import java.util.ArrayList;

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
    private ArrayList<Buyable>          propsToGive;
    private ArrayList<Buyable>          selectedPropsToGive;
    private ArrayList<Buyable>          propsToReceive;
    private ArrayList<Buyable>          selectedPropsToReceive;
    
    public  Monopoly(ArrayList<Player> players)
    {
        new Board();
        this.setPlayers(players);
        this.indexOfPlayer = 0;
        this.indexOfBidder = 0;
        this.activePlayer = players.get(0);
        this.activeBidder = this.activePlayer;
        this.choice = 0; 
    }
    
    public void build(Square property)
    {
        if (property.getClass().getName().equals("Property"))
            this.activePlayer.erectHouse((Property)property);  
    }

    public void destroy(Square property)
    {
        if (property.getClass().getName().equals("Property"))
            this.activePlayer.degradeProperty((Property)property);
    }

    public void mortgage(Square property)
    {
        if (property instanceof Buyable)
            this.activePlayer.mortgageProperty((Buyable)property);
    }

    public void liftMortgage(Square property)
    {
        if (property instanceof Buyable)
            this.activePlayer.liftMortgage((Buyable)property);
    }

    public void play()
    {
        setMessage();    
        Board.getSquares()[this.activePlayer.getCoordinate()].doAction(this.activePlayer);
    }

    public void play(boolean bool)
    {
        if (Board.getSquares()[this.activePlayer.getCoordinate()] instanceof Buyable)
            ((Buyable)Board.getSquares()[this.activePlayer.getCoordinate()]).setWantsToBuy(bool);
        play();
    }

    public void play(int choice)
    {
        if (Board.getSquares()[this.activePlayer.getCoordinate()].getClass().getName().equals("Jail"))
            ((Jail)Board.getSquares()[this.activePlayer.getCoordinate()]).setUserChoice(choice);
        play();
    }

    public void setMessage()
    {
        if (this.activePlayer.getCoordinate() == 10)
            ((Jail)Board.getSquares()[this.activePlayer.getCoordinate()]).checkTheStateSetMessage(this.activePlayer);
        else if (Board.getSquares()[this.activePlayer.getCoordinate()] instanceof Buyable)
            ((Buyable)Board.getSquares()[this.activePlayer.getCoordinate()]).setMessage(this.activePlayer);
    }

    public void removePlayer()
    {
        if (Board.getSquares()[this.activePlayer.getCoordinate()] instanceof Buyable)
        {
            if (((Buyable)Board.getSquares()[this.activePlayer.getCoordinate()]).getOwner() != null){
                for (Buyable belonging : this.activePlayer.getBelongings())
                {
                    ((Buyable)Board.getSquares()[this.activePlayer.getCoordinate()]).getOwner().getBelongings().add(belonging);
                    belonging.setOwner(((Buyable)Board.getSquares()[this.activePlayer.getCoordinate()]).getOwner());
                }
            }
            else
                for (Buyable belonging : this.activePlayer.getBelongings())
                    belonging.setOwner(null);
        }
        for (Player player : players)
            if (player.equals(this.activePlayer))
            {
                players.remove(player);
                this.indexOfPlayer--;
                break;
            }
    }

    public void startGame()
    {
        this.moveToJail = false;
        if (!(this.activePlayer.isPrisoned()))
            Utility.setDice(this.activePlayer.throwDice());
        else
        {
            this.activePlayer.throwDice();
            if (this.activePlayer.holdsDoubles())
            {
                
                this.activePlayer.setIsPrisoned(false);
                this.activePlayer.setDaysInJail(1);
                this.activePlayer.movePlayer(this.activePlayer.getDice());
                Board.getSquares()[this.activePlayer.getCoordinate()].doAction(this.activePlayer);
                return ;
            }
            this.activePlayer.setDaysInJail(this.activePlayer.getDaysInJail() + 1);
        }
        if (this.activePlayer.getDoublesInARow() == 3)
        {
            this.activePlayer.setIsPrisoned(true);
            this.activePlayer.setDoublesInARow(0);
            this.activePlayer.setCoordinate(10);
            this.moveToJail = true;
            return ;
        }
        this.activePlayer.movePlayer(this.activePlayer.getDice());
    }

    public void changePlayer()
    {
        if (this.activePlayer.getDoublesInARow() == 0 || (this.activePlayer.getDoublesInARow() != 0 && activePlayer.isPrisoned()))
        {
            this.indexOfPlayer++;
            this.activePlayer.setDoublesInARow(0);
        }
        if (this.indexOfPlayer == players.size())
        {
            this.indexOfPlayer = 0;
            for (Player p : players)
                p.nullifyDice();
        }
        this.activePlayer = players.get(this.indexOfPlayer);
        this.activeBidder = this.activePlayer;
        this.indexOfBidder = this.indexOfPlayer;
    }

    public void changeBidder()
    {
        this.indexOfBidder++;
        if (indexOfBidder == bidders.size())
            this.indexOfBidder = 0;
        this.activeBidder = bidders.get(this.indexOfBidder);
    }

    public void setBidders(int coordinate)
    {
        bidders = new ArrayList<>();
        bidders.add(this.activeBidder);
        for (Player p : players)
            if (!(p.equals(this.activeBidder)))
                bidders.add(p);
    }

    public void setPropsToGive()
    {
        this.selectedPropsToGive = new ArrayList<>();
        this.propsToGive = new ArrayList<>();
        for (Buyable b : this.activePlayer.getBelongings())
            this.propsToGive.add(b);
    }

    public void setPropsToReceive()
    {
        this.selectedPropsToReceive = new ArrayList<>();
        this.propsToReceive = new ArrayList<>();
        for (Buyable b : this.tradee.getBelongings())
            this.propsToReceive.add(b);
    }

    public void setTradeLists()
    {
        this.setPropsToGive();
        this.setPropsToReceive();
    }

    public void nullifyTradeProps()
    {
        this.propsToGive = null;
        this.propsToReceive = null;
        this.selectedPropsToGive = null;
        this.selectedPropsToReceive = null;
    }

    public void removeActiveBidder()
    {
        for (Player b : bidders)
            if (b.equals(this.activeBidder))
            {
                bidders.remove(b);
                break ;
            }
        this.indexOfBidder--;
        changeBidder();
    }

    public void nullifyBidders()
    {
        bidders = null;
    }

    public boolean activePlayerWon()
    {
        return (players.size() == 1);
    }

    public ArrayList<Buyable> getPropsToReceive()
    {
        return (this.propsToReceive);
    }

    public ArrayList<Buyable> getPropsToGive()
    {
        return (this.propsToGive);
    }

    public ArrayList<Buyable> getSelectedPropsToReceive()
    {
        return (this.selectedPropsToReceive);
    }

    public ArrayList<Buyable> getSelectedPropsToGive()
    {
        return (this.selectedPropsToGive);
    }

    public boolean getMoveToJail()
    {
        return (this.moveToJail);
    }

    public ArrayList<Player> getBidders()
    {
        return (bidders);
    }

    public static ArrayList<Player> getPlayers()
    {
        return (players);
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

    public int getNumberOfPlayers()
    {
        return (players.size());
    }

    public void setTradee(Player tradee)
    {
        this.tradee = tradee;
    }

    public void setPlayers(ArrayList<Player> newplayers)
    {
        players = newplayers;             
    }

    public void setChoice(int choice)
    {
        this.choice = choice;
    }
}
