import java.util.ArrayList;
import java.awt.event.*;
//import java.util.concurrent.Flow;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


public class MainWindow extends JFrame implements ActionListener{
    private ArrayList<Player> players = new ArrayList<>();              //need to make this local
    private int numberOfPlayers;
    
    private CustomButton[] buttons = new CustomButton[40];
    private JLayeredPane boardContainer;    //was JPanel

    //board components
    private JPanel board;
    private JPanel top;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JPanel center;
    private TitleDeeds titleDeed;

    private int numOfNames;

    //info components
    private JPanel info;
    private JPanel infoTop;
    private JPanel infoCenter;
    private JPanel infoBottom;

    //infoTop
    private JLabel[] playerInfo;

    //infoCenter
    InfoForBuyable  infoForBuyable;
    JPanel          commands;
    CustomCards     card;
    InfoGoTaxFree   infoGoTaxFree; 

    //infoBottom buttons
    private JButton throwDice;
    private JButton done;
     

    //the actual game (gonna call methods like throwDice() and other stuff from here)
    private Monopoly game;

    private class TitleDeeds extends JPanel{
        private JLabel titleOfDeed;
        private JLabel priceOfDeed;
        private JLabel ownerNameOfDeed;
        private JLabel rentOfDeed;
        //private JLabel mortgageValue;

        private TitleDeeds(int i){
            super();
            this.titleOfDeed = new JLabel();
            this.priceOfDeed = new JLabel();
            this.ownerNameOfDeed = new JLabel();
            this.rentOfDeed = new JLabel();

            setEverything(i);
            this.setLayout(new GridLayout(10, 1));
            this.setPreferredSize(new Dimension(150, 200));
            //this.setBorder(BorderFactory.createLineBorder(Color.black));
            this.add(titleOfDeed);
            this.add(priceOfDeed);
            this.add(ownerNameOfDeed);
            this.add(rentOfDeed);
        }
        private TitleDeeds(){
            super();
        }

        private void setEverything(int i){
            setEmpty();
            setTilteOfDeed(Board.getSquares()[i].getTitle());
            if(Board.getSquares()[i] instanceof Buyable){
                setPriceOfDeed(((Buyable)Board.getSquares()[i]).getPrice());
                setRentOfDeed(((Buyable)Board.getSquares()[i]).getRent());
                if (((Buyable)Board.getSquares()[i]).getOwner() != null)
                    setOwnerNameOfDeed(((Buyable)Board.getSquares()[i]).getOwner().getName());
                
            }


        }
        private void setEmpty(){
            this.priceOfDeed.setText("");
            this.ownerNameOfDeed.setText("");
            this.rentOfDeed.setText("");
        }
        private void setTilteOfDeed(String title){
            this.titleOfDeed.setText(title);
        }
        private void setPriceOfDeed(int price){
            this.priceOfDeed.setText("Price: " + price);
        }
        private void setOwnerNameOfDeed(String ownerName){
            this.ownerNameOfDeed.setText("Owner: " + ownerName);
        }

        private void setRentOfDeed(int rent){
            this.rentOfDeed.setText("Rent: " + rent + " $");
        }

    }
    private class CustomButton extends JButton{
        private Color color;
        private int direction;

        private CustomButton(){}

        private CustomButton(int direction){
            super();
            this.direction = direction;
        }

        private void setColor(Color color){
            this.color = color;
        }



        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if (this.color != null){
                g.setColor(color);
                if (direction == 1)
                    g.fillRect(0, 0, this.getWidth(), this.getHeight()/4);
                else if (direction == 3)
                    g.fillRect(0, 3*this.getHeight()/4, this.getWidth(), this.getHeight()/4);
                else if (direction == 2)
                    g.fillRect(3*this.getWidth()/4, 0, this.getWidth()/4, this.getHeight());
                else if (direction == 4)
                    g.fillRect(0, 0, this.getWidth()/4, this.getHeight());
            }
            
        }
    }   
    
    private class InfoForBuyable extends JPanel implements ActionListener{
        private JButton yesButton;
        private JButton noButton;
        private JLabel instructions;
        private JPanel buttonPanel;

        private InfoForBuyable(){
            this.setLayout(new GridLayout(3, 1));
            //this.setSize(new Dimension(100, 150));
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            yesButton = new JButton("Yes");
            noButton = new JButton("No");
            instructions = new JLabel();

            yesButton.setBackground(new Color(255, 255, 255));
            noButton.setBackground(new Color(255, 255, 255));
            yesButton.addActionListener(this);
            noButton.addActionListener(this);


            
            buttonPanel.add(yesButton);
            buttonPanel.add(noButton);
            this.add(instructions);
            this.add(buttonPanel);
        }

        
        private void setThePanel(){
            if (!(Board.getSquares()[game.getActivePlayerCoordinate()] instanceof Buyable))
                return;
            this.instructions.setVisible(true);
            this.instructions.setText((Board.getSquares()[game.getActivePlayerCoordinate()]).getMessage());
            if (((Buyable)Board.getSquares()[game.getActivePlayerCoordinate()]).getOwner() != null)
                this.buttonPanel.setVisible(false);
            else
                this.buttonPanel.setVisible(true);
        }

        // private void setAllVisible(boolean visibility){
        //     this.buttonPanel.setVisible(visibility);
        //     this.instructions.setVisible(visibility);
        // }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == yesButton){
                ((Buyable)Board.getSquares()[game.getActivePlayerCoordinate()]).setWantsToBuy(true);
            }

            else if (e.getSource() == noButton){
                ((Buyable)Board.getSquares()[game.getActivePlayerCoordinate()]).setWantsToBuy(false);
                ///need to open the auction part
            }

            ((Buyable)Board.getSquares()[game.getActivePlayerCoordinate()]).doAction(game.activePlayer);
            titleDeed.setEverything(game.getActivePlayerCoordinate());
            setUpInfoTop();
            setVisible(false);
            
        }

            
    }
    
    private class CustomCards extends JPanel{
        private JLabel message;
        private JButton ok;
        private CustomCards(){
            //this.setSize(new Dimension(100, 100));
            this.setLayout(new GridLayout(2, 1));
            this.message = new JLabel();
            this.ok = new JButton("OK");
            ok.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    int previousCoordinate = game.getActivePlayerCoordinate();
                    Board.getSquares()[game.getActivePlayerCoordinate()].doAction(game.activePlayer);
                    setVisible(false);
                    if (Board.getSquares()[previousCoordinate].getClass().getName().equals("Chance")){
                        if (((Chance)Board.getSquares()[previousCoordinate]).ifcallDoAction())
                            setUpInfoCenter();
                    }
                    titleDeed.setEverything(game.getActivePlayerCoordinate());
                    setUpInfoTop();
                }

            });
            //ok.setSize(new Dimension(30, 20));
            this.add(message);
            this.add(ok);
            
        }

        private void setTheCard(){ 
            ((Deck) Board.getSquares()[game.getActivePlayerCoordinate()]).drawCard();
            this.message.setText((Board.getSquares()[game.getActivePlayerCoordinate()]).getMessage());
            System.out.println("The message : " + this.message.getText());

        }
    }
    
    private class InfoGoTaxFree extends JPanel implements ActionListener{
        JLabel message;
        JPanel buttons;
        private JButton percent;
        private JButton money;
        private InfoGoTaxFree(){
            this.setLayout(new GridLayout(2, 1));
            
            buttons = new JPanel();
            buttons.setLayout(new FlowLayout());
            message = new JLabel();
            percent = new JButton("10% of your wealth");
            money = new JButton("200$");

            money.addActionListener(this);
            percent.addActionListener(this);

            buttons.add(money);
            buttons.add(percent);

            this.add(message);
            this.add(buttons);
        }

        private void setMessage(){
            if (!Board.getSquares()[game.getActivePlayerCoordinate()].getClass().getName().equals("GOTaxFree")){
                this.setVisible(false);
                return;
            }
            this.message.setText(Board.getSquares()[game.getActivePlayerCoordinate()].getMessage());
            if (game.getActivePlayerCoordinate() == 4)
                this.buttons.setVisible(true);
            else
                this.buttons.setVisible(false);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == money)
                ((GOTaxFree)Board.getSquares()[game.getActivePlayerCoordinate()]).setChoice(1);
            else
                ((GOTaxFree)Board.getSquares()[game.getActivePlayerCoordinate()]).setChoice(2);
            this.setVisible(false);
            Board.getSquares()[game.getActivePlayerCoordinate()].doAction(game.activePlayer);
            setUpInfoTop();
        }
    }
    

    private class infoJail extends JPanel{
        JLabel message;
        JPanel buttons;
        JButton pay;
        JButton useTheCard;

        private infoJail(){
            this.setLayout(new GridLayout(2, 1));
            this.message = new JLabel();
            this.buttons = new JPanel();
            this.pay = new JButton();
            this.useTheCard = new JButton();

            buttons.add(pay);
            buttons.add(useTheCard);

            this.add(message);
            this.add(buttons);
        }

        private void setThePanel(){
            this.message.setText(Board.getSquares()[game.getActivePlayerCoordinate()].getMessage());
            // if ()
            if (game.activePlayer.getGetOutOfJail())
                this.useTheCard.setEnabled(true);
            else
                this.useTheCard.setEnabled(false);

            
        }
    }
    

    public MainWindow(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLayout(new BorderLayout());
        
        //setting up
        //this.board = new JPanel();
        this.info = new JPanel();
        this.infoTop = new JPanel();
        this.infoCenter = new JPanel();
        this.infoBottom = new JPanel();
        numOfNames = 1;
        
    

        //The images
        ImageIcon image = new ImageIcon("./images/logo2.png");
        //ImageIcon image = new ImageIcon("CS120A_Group_Project_Monopoly/images/logo2.png");
        
        
        //The main panel
        JPanel mainMenu = new JPanel();

        //Labels
        JLabel mainLabel = new JLabel("Welcome to Alexander's and Anahit's Monopoly");
        JPanel setUp = new JPanel();        //change
        
        //The names of the players
        JLabel namesLabel = new JLabel();
        JTextField nameFromField = new JTextField();
        nameFromField.setPreferredSize(new Dimension(80, 40));
        JButton submitName = new JButton("Submit");
        submitName.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameFromField.getText();
                if (name.equals("")){
                    namesLabel.setText( "Empty name. " + namesLabel.getText());
                    return;
                }
                for (Player player : players) {
                    if (name.equalsIgnoreCase(player.getName())){
                        namesLabel.setText( "There is already a player with that name. " + namesLabel.getText());
                        nameFromField.setText("");
                        return;
                    }
                }
                players.add(new Player(name));
                /*numberOfPlayers--;*/
                if (numOfNames < numberOfPlayers){  
                    namesLabel.setText("Please enter the name of Player " + (players.size() + 1));
                    
                    nameFromField.setText("");
                    numOfNames++;
                }
                else{
                    setUp.setVisible(false);
                    mainMenu.setVisible(false);
                    game = new Monopoly(players);
                    setTheFlow();

                }  
            }

        });

        namesLabel.setVisible(false);
        nameFromField.setVisible(false);
        submitName.setVisible(false);

        //Number of Players
        SpinnerModel values = new SpinnerNumberModel(2, 2, 4, 1);
        JSpinner howManyPlayers = new JSpinner(values);
        howManyPlayers.setVisible(false);
        JButton submitNumberOfPlayers = new JButton("Submit");
        submitNumberOfPlayers.setVisible(false);
        submitNumberOfPlayers.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = (Integer) howManyPlayers.getValue();
                submitNumberOfPlayers.setVisible(false);
                howManyPlayers.setVisible(false);
                namesLabel.setText("Please enter the name of Player 1");
                namesLabel.setVisible(true);
                nameFromField.setVisible(true);
                submitName.setVisible(true);
            }
        
        });

        //Start button
        JButton start = new JButton("START");
        start.setVerticalAlignment(JButton.BOTTOM);
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                start.setVisible(false);
                howManyPlayers.setVisible(true);
                submitNumberOfPlayers.setVisible(true);         
            }
        });

        //Adding to the setUp           //change
        setUp.add(start); 
        setUp.add(howManyPlayers);
        setUp.add(submitNumberOfPlayers);
        setUp.add(namesLabel);
        setUp.add(nameFromField);
        setUp.add(submitName);

        //Adding to the mainLabel
        mainLabel.setIcon(image);
        mainLabel.setHorizontalTextPosition(JLabel.CENTER);
        mainLabel.setVerticalTextPosition(JLabel.BOTTOM);
        mainLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        //Adding to the mainMenu
        mainMenu.add(mainLabel);
       
        //Adding to the frame
        this.add(mainMenu, BorderLayout.NORTH);
        this.add(setUp, BorderLayout.CENTER);            //change        mainWindiw.add(setUp)

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        
    }



    private void setButtons(){
        for (int i = 0; i < 40; i++){                   //need to change this back, they do not need direction for now
            if (i > 0 && i < 10)
                buttons[i] = new CustomButton(1);
            else if (i > 10 && i < 20)
                buttons[i] = new CustomButton(2);
            else if (i > 20 && i < 30)
                buttons[i] = new CustomButton(3);
            else if (i > 30 && i < 40)
                buttons[i] = new CustomButton(4);
            else{
                buttons[i] = new CustomButton();
            }

            if (i == 1 || i == 3)
                buttons[i].setColor(new Color(192, 57, 43));
            else if (i == 6 || i == 8 || i == 9)
                buttons[i].setColor(new Color(76, 167, 236));
            else if (i == 11 || i == 13 || i == 14)
                buttons[i].setColor(new Color(236, 76, 100));
            else if (i == 16 || i == 18 || i == 19)
                buttons[i].setColor(new Color(255, 133, 51));
            else if (i == 21 || i == 23 || i == 24)
                buttons[i].setColor(new Color(247, 73, 36));
            else if (i == 26 || i == 27 || i == 29)
                buttons[i].setColor(new Color(255, 223, 19));
            else if (i == 31 || i == 32 || i == 34)
                buttons[i].setColor(new Color(31, 136, 64));
            else if (i == 37 || i == 39)
                buttons[i].setColor(new Color(31, 93, 136));

            // if (i != 10 || i != 20 || i != 30 || i != 0)
            //     buttons[i].setText(Board.getSquares()[i].getTitle());
        }
        
    }


    //the board and the info
    private void setTheFlow(){
        this.setSize(new Dimension(1200, 750));

        Border border = BorderFactory.createLineBorder(new Color(192, 192, 192), 1);
        this.setButtons();
        for (int i = 0; i < 40; i++){
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.white);
            buttons[i].setBorder(border);
        }

        this.setLayout(new BorderLayout());
        boardContainer = new JLayeredPane();//JPanel();
        boardContainer.setLayout(new FlowLayout());

        board = new JPanel();
        board.setSize(1000, 700);
        board.setLayout(new BorderLayout());

        top = new JPanel();
        left = new JPanel();
        right = new JPanel();
        bottom = new JPanel();
        center = new JPanel();


        top.setPreferredSize(new Dimension(693,90));
        left.setPreferredSize(new Dimension(90,513));
        right.setPreferredSize(new Dimension(90,513));
        bottom.setPreferredSize(new Dimension(693,90));
        //center.setPreferredSize(new Dimension(90,90));


        this.setUpTop();
        this.setUpBottom();
        this.setUpLeft();
        this.setUpRight();
        this.setUpInfo();
        //setUpColors();


        board.add(top,BorderLayout.NORTH);
        board.add(left,BorderLayout.WEST);
        board.add(right,BorderLayout.EAST);
        board.add(bottom,BorderLayout.SOUTH);
        board.add(center,BorderLayout.CENTER);
    
        
        boardContainer.add(board);
        


        

        //adding to the frame
        this.add(boardContainer, BorderLayout.WEST);
        this.add(info);

    }


    private void setUpInfo(){

        this.titleDeed = new TitleDeeds(0);

        //the components of commands
        this.commands = new JPanel();
        commands.setLayout(new FlowLayout());
        
        this.card = new CustomCards();
        this.infoForBuyable = new InfoForBuyable();
        this.infoGoTaxFree = new InfoGoTaxFree();

        this.card.setVisible(false);
        this.infoForBuyable.setVisible(false);
        this.infoGoTaxFree.setVisible(false);
        //System.out.println(Board.getSquares()[0].getTitle());
       // titleDeed.setTilteOfDeed(Board.getSquares()[0].getTitle());
        
        info.setLayout(new BorderLayout());
        info.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        infoTop.setSize(new Dimension(300, 600));
        infoTop.setLayout(new GridLayout(numberOfPlayers, 1));
       
        infoCenter.setSize(new Dimension(300, 600));
        infoCenter.setLayout(new BorderLayout());           //maybe another layout
        info.setBorder(new EmptyBorder(30, 30, 30, 30));

        
        
        //adding to commands;
        commands.add(infoForBuyable);
        commands.add(card);
        commands.add(infoGoTaxFree);
        //infocenter.add commands and titleDeed;
        infoCenter.add(titleDeed, BorderLayout.WEST);
        infoCenter.add(commands, BorderLayout.CENTER);
        

        infoBottom.setSize(new Dimension(300, 600));


        //infoTop
        playerInfo = new JLabel[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++){
            playerInfo[i] = new JLabel();
            //playerInfo[i].setFont(new Font("Serif", Font.ITALIC, 18));
            infoTop.add(playerInfo[i]);
        }

        setUpInfoTop();
        //for(int i = 0; i < numberOfPlayers; i++)
            

        //JLabel playersInfo = new JLabel(game.infoActivePlayer());
        
        //infoCenter
        //infoCenter.add(titleDeed);
        setUpInfoBottom();
        //infoBottom.add(new JLabel("BOTTOM")); 
        

        //adding to info
        
        info.add(infoTop, BorderLayout.NORTH);
        info.add(infoCenter, BorderLayout.CENTER);
        info.add(infoBottom, BorderLayout.SOUTH);
    }



    private void setUpInfoTop(){
        for (int i = 0; i < numberOfPlayers; i++){
            
            playerInfo[i].setText(Monopoly.getPlayers().get(i).toString());
            if (Monopoly.getPlayers().get(i).equals(game.activePlayer))
                playerInfo[i].setFont(new Font("Serif", Font.BOLD, 18));
            else{
                playerInfo[i].setFont(new Font("Serif", Font.ROMAN_BASELINE, 18));

            }
        }
    }


    private void setUpInfoCenter(){
        infoForBuyable.setVisible(false);
        card.setVisible(false);
        infoGoTaxFree.setVisible(false);

        titleDeed.setEverything(game.getActivePlayerCoordinate());

        if(Board.getSquares()[game.getActivePlayerCoordinate()] instanceof Buyable){
            this.infoForBuyable.setThePanel();
            this.infoForBuyable.setVisible(true);
            titleDeed.setEverything(game.getActivePlayerCoordinate());
        }
        else if (Board.getSquares()[game.getActivePlayerCoordinate()] instanceof Deck){
            // System.out.println("Chance card needs to be visible");
            // card.setVisible(true);
           
            

            this.card.setTheCard();
            // System.out.println("ActivePlayerCoordinate: " + game.getActivePlayerCoordinate());
            // System.out.println("The message : " + (Board.getSquares()[game.getActivePlayerCoordinate()]).getMessage());
            //Board.getSquares()[game.getActivePlayerCoordinate()].doAction(game.activePlayer);
            card.setVisible(true);
        }
        else if (Board.getSquares()[game.getActivePlayerCoordinate()].getClass().getName().equals("GOTaxFree")){
            infoGoTaxFree.setMessage();
            infoGoTaxFree.setVisible(true);
        }
            

        // else if (game.getActivePlayerCoordinate() == 4){
        //     inclomeTaxPanel.setVisible(true);
        // }
        // else(){

        // }
        setUpInfoTop();
    }

    private void setUpInfoBottom(){
        infoBottom.setLayout(new GridLayout());
        this.throwDice = new JButton("Throw the dice");
        this.done = new JButton("Done");
        throwDice.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                game.startGame();
                setUpInfoCenter();
                if (!game.ifPlayerHoldsDoubles()){          //moved to After doAction is complete
                    throwDice.setEnabled(false);
                    done.setEnabled(true);
                }
                setUpInfoTop();
                
            }
            
        });

        done.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                game.changePlayer();
                done.setEnabled(false);
                setUpInfoTop();
                throwDice.setEnabled(true);
                infoForBuyable.setVisible(false);
                card.setVisible(false);
                infoGoTaxFree.setVisible(false);
                //commands.setVisible(false);
                //System.out.println(game.activePlayer.getName());
            }
            
        });
        infoBottom.add(throwDice);
        infoBottom.add(done);

    }


    //board
    private void setUpTop(){                                        //all of these setUps can be compressed to one method probably
        top.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        buttons[20].setPreferredSize(new Dimension(90, 90));
        top.add(buttons[20]);
        for (int i = 21; i < 30; i++){
            buttons[i].setPreferredSize(new Dimension(57, 90));
            top.add(buttons[i]);
        }

        buttons[30].setPreferredSize(new Dimension(90, 90));
        top.add(buttons[30]);
    }

    private void setUpBottom(){
        bottom.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        buttons[10].setPreferredSize(new Dimension(90, 90));
        bottom.add(buttons[10]);
        for (int i = 9; i > 0; i--){
            buttons[i].setPreferredSize(new Dimension(57, 90));
            bottom.add(buttons[i]);
        }

        buttons[0].setPreferredSize(new Dimension(90, 90));
        bottom.add(buttons[0]);
    }

    private void setUpLeft(){
        left.setLayout(new FlowLayout());
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        //left.setLayout(new GridLayout(9, 1, 0, 0));

        for (int i = 19; i > 10; i--){
            buttons[i].setMaximumSize(new Dimension(90, 57));
           
            left.add(buttons[i]);
        }
    }

    private void setUpRight(){
        right.setLayout(new FlowLayout());
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        //left.setLayout(new GridLayout(9, 1, 0, 0));

        for (int i = 31; i < 40; i++){
            buttons[i].setMaximumSize(new Dimension(90, 57));
            right.add(buttons[i]);
        }
    }


    public void actionPerformed(ActionEvent e) {
        
        for (int i = 0; i < 40; i++){
            if (e.getSource() == buttons[i]){
                //System.out.println(Board.getSquares()[i].toString());
                //this.titleDeed.setLayout(new GridLayout(10, 1));
                System.out.println("Square coordinate: " + i);
                // this.titleDeed.removeAll();
                // repaint();
                
                // this.titleDeed.add(new JLabel("hello")); 
 
                titleDeed.setEverything(i);
                
                // if (Board.getSquares()[i].getClass().getName().equals("Property")){
                //    this.titleDeed.add(new JLabel(((Property) Board.getSquares()[i]).getHousePrice() + ""));
                // }
                //this.title.setText("Che inch esim ay axper");
                // infoCenter.add(titleDeed);
                // info.add(infoCenter);
                // this.add(info); 
                break;
            }
        }
    }

}
