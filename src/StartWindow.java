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
        this.setSize(700, 400);                 //this.setSize(1300, 750);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        //this.getContentPane().setBackground(new Color(1, 1, 1));

        

        //The images
        ImageIcon image = new ImageIcon("./images/LOGO1.png");
        ImageIcon icon = new ImageIcon("./images/icon.png");
        this.setIconImage(icon.getImage());
        //ImageIcon image = new ImageIcon("./images/logo2.png");
        
        
        //The main panel
        JPanel mainMenu = new JPanel();
        // mainMenu.setBackground(new Color(255, 244, 246 ));
        // mainMenu.setOpaque(true);
        // mainMenu.setBackground(new Color(64, 184, 182));

        //Labels
        JLabel mainLabel = new JLabel("Welcome to Alexander's and Anahit's Monopoly");
        //mainLabel.setForeground(Color.white);
        // mainLabel.setFont(font);
        // mainLabel.setFont(new Font("Futura", Font.ROMAN_BASELINE, 14));
        JPanel setUp = new JPanel();        //change
        
        // setUp.setBackground(new Color(255, 244, 246 ));
        // setUp.setOpaque(true);


        //the array list of players that will be given to the game when it is initialized
        ArrayList<Player> players = new ArrayList<>(); 
        
        //The names of the players
        JLabel namesLabel = new JLabel();
        JTextField nameFromField = new JTextField();
        nameFromField.setPreferredSize(new Dimension(80, 40));
        GeneralButton submitName = new GeneralButton("Submit");
        
        //int numOfNames = 1;
        submitName.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameFromField.getText();
                if (name.equals("")){
                    namesLabel.setText( "Empty name. Please enter the name of Player " + (players.size() + 1));
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
                if (players.size() < numberOfPlayers){  
                    namesLabel.setText("Please enter the name of Player " + (players.size() + 1));
                    
                    nameFromField.setText("");
                }
                else
                {
                    new MainWindow(players);
                    dispose();
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
        
        GeneralButton submitNumberOfPlayers = new GeneralButton("Submit");

        submitNumberOfPlayers.setVisible(false);
        submitNumberOfPlayers.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = (Integer) howManyPlayers.getValue();
                submitNumberOfPlayers.setVisible(false);
                howManyPlayers.setVisible(false);
                namesLabel.setText("Please enter the name of Player 1");
                //namesLabel.setForeground(Color.white);;
                namesLabel.setVisible(true);
                nameFromField.setVisible(true);
                submitName.setVisible(true);
            }
        
        });

        //Start button
        GeneralButton start = new GeneralButton("START");
        
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
        this.add(setUp, BorderLayout.CENTER);            

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
