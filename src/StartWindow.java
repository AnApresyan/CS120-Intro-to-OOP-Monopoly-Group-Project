import java.util.ArrayList;
import java.awt.event.*;
//import java.util.concurrent.Flow;
import java.awt.*;
import javax.swing.*;

public class StartWindow extends JFrame{
    private int numberOfPlayers;

    public StartWindow(){
        super("Monopoly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 700);                 //this.setSize(1300, 750);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        //The images
        ImageIcon image = new ImageIcon("./images/LOGO1.png");
        //ImageIcon image = new ImageIcon("./images/logo2.png");
        
        
        //The main panel
        JPanel mainMenu = new JPanel();
        // mainMenu.setBackground(new Color(64, 184, 182));

        //Labels
        JLabel mainLabel = new JLabel("Welcome to Alexander's and Anahit's Monopoly");
        // mainLabel.setFont(new Font("Futura", Font.ROMAN_BASELINE, 14));
        JPanel setUp = new JPanel();        //change
        
        //the array list of players that will be given to the game when it is initialized
        ArrayList<Player> players = new ArrayList<>(); 
        
        //The names of the players
        JLabel namesLabel = new JLabel();
        JTextField nameFromField = new JTextField();
        nameFromField.setPreferredSize(new Dimension(80, 40));
        JButton submitName = new JButton("Submit");
        
        //int numOfNames = 1;
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
                if (players.size() < numberOfPlayers){  
                    namesLabel.setText("Please enter the name of Player " + (players.size() + 1));
                    
                    nameFromField.setText("");
                }
                else
                {
                    // setUp.setVisible(false);
                    // mainMenu.setVisible(false);
                    new MainWindow(players);
                    dispose();
                    
                    
                    

                    // game = new Monopoly(players);
                    // popUpAuction = new AuctionPopUp();
                    //         // initializing player sprites
                    // sprites.add(new JLabel("", new ImageIcon(new ImageIcon("CS120A_Group_Project_Monopoly/images/Player1.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)), JLabel.CENTER));
                    // sprites.add(new JLabel("", new ImageIcon(new ImageIcon("CS120A_Group_Project_Monopoly/images/Player2.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)), JLabel.CENTER));
                    // if (numOfNames >= 3)
                    //     sprites.add(new JLabel("", new ImageIcon(new ImageIcon("CS120A_Group_Project_Monopoly/images/Player3.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)), JLabel.CENTER));
                    // if (numOfNames >= 4)
                    //     sprites.add(new JLabel("", new ImageIcon(new ImageIcon("CS120A_Group_Project_Monopoly/images/Player4.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)), JLabel.CENTER));
                    // setTheFlow();

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
    
}
