import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import java.util.concurrent.Flow;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;



public class MainWindow extends JFrame implements ActionListener{
    private ArrayList<Player> players = new ArrayList<>();              //need to make this local
    private int numberOfPlayers;
    
    private CustomButton[] buttons = new CustomButton[40];
    JPanel boardContainer;

    //board components
    JPanel board;
    JPanel top;
    JPanel left;
    JPanel right;
    JPanel bottom;
    JPanel center;
    TitleDeeds titleDeed;


    //info components
    JPanel info;
    JPanel infoTop;
    JPanel infoCenter;
    JPanel infoBottom;

    //the actual game (gonna call methods like throwDice() and other stuff from here)
    Monopoly game;

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
            setTilteOfDeed(Board.getSquares()[i].getTitle());
            if(Board.getSquares()[i] instanceof Buyable){
                setPriceOfDeed(((Buyable)Board.getSquares()[i]).getPrice());
                setRentOfDeed(((Buyable)Board.getSquares()[i]).getRent());
                if (((Buyable)Board.getSquares()[i]).getOwner() != null)
                    setOwnerNameOfDeed(((Buyable)Board.getSquares()[i]).getOwner().getName());
                
            }
            else{
                this.priceOfDeed.setText("");
                this.ownerNameOfDeed.setText("");
                this.rentOfDeed.setText("");
            }
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
        
    

        //The images
        ImageIcon image = new ImageIcon("./images/logo2.png");
        
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
                numberOfPlayers--;
                if (numberOfPlayers >= 1){  
                    namesLabel.setText("Please enter the name of Player " + (players.size() + 1));
                    
                    nameFromField.setText("");
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



    //helper methods (not to have everything packed in the constructor)

    // private void setDefaultTitleDeed(){
    //     this.titleDeed.setPreferredSize(new Dimension(300, 500));
    //     this.titleDeed.setBorder(BorderFactory.createLineBorder(Color.black));
    //     // this.title = new JLabel();
    //     // title.setText("Of aman esim e");
    //     //titleDeed.add(title);
    //     this.titleDeed.setLayout(new GridLayout(10, 1));
    //     //this.titleDeed.add(new JLabel(Board.getSquares()[0].getTitle()));
    // }

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
        Border border = BorderFactory.createLineBorder(new Color(192, 192, 192), 1);
        this.setButtons();
        for (int i = 0; i < 40; i++){
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.white);
            buttons[i].setBorder(border);
        }

        this.setLayout(new BorderLayout());
        boardContainer = new JPanel();
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
        //System.out.println(Board.getSquares()[0].getTitle());
       // titleDeed.setTilteOfDeed(Board.getSquares()[0].getTitle());
        
        info.setLayout(new BorderLayout());
        info.setBorder(new EmptyBorder(30, 30, 30, 30));
        
        infoTop.setSize(new Dimension(300, 600));
        infoTop.setLayout(new GridLayout(numberOfPlayers, 1));
       
        infoCenter.setSize(new Dimension(300, 600));
        infoCenter.setLayout(new BorderLayout());           //maybe another layout
        info.setBorder(new EmptyBorder(30, 30, 30, 30));

        //titleDeed.add(new JLabel("Off"));
        infoCenter.add(titleDeed, BorderLayout.WEST);

        infoBottom.setSize(new Dimension(300, 600));


        //infoTop
            for (int i = 0; i < Monopoly.getPlayers().size(); i++){
                JLabel playerInfo = new JLabel(Monopoly.getPlayers().get(i).toString());
                playerInfo.setFont(new Font("Serif", Font.ITALIC, 18));
                infoTop.add(playerInfo);
            }

        //JLabel playersInfo = new JLabel(game.infoActivePlayer());
        
        //infoCenter
        //infoCenter.add(titleDeed);
        infoBottom.add(new JLabel("BOTTOM")); 
        

        //adding to info
        info.add(infoTop, BorderLayout.NORTH);
        info.add(infoCenter, BorderLayout.CENTER);
        info.add(infoBottom, BorderLayout.SOUTH);
    }


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
                System.out.println(i);
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
