import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//import java.util.concurrent.Flow;
import java.awt.*;

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



public class MainWindow extends JFrame implements ActionListener{
    private ArrayList<Player> players = new ArrayList<>();              //need to make this local
    private int numberOfPlayers;
    JPanel board = null;
    JPanel info = null;
    JPanel infoTop = null;
    JPanel infoCenter = null;
    JPanel infoBottom = null;
    JButton[] buttons = new JButton[40];
    Monopoly game;

    public MainWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLayout(new BorderLayout());
        
        //setting up
        this.board = new JPanel();
        this.info = new JPanel();
        this.infoTop = new JPanel();
        this.infoCenter = new JPanel();
        this.infoBottom = new JPanel();
    
        for (int i = 0; i < 40; i++){
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
        }

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


    //the board and the info
    public void setTheFlow(){
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(1800, 1000));

        board.setLayout(new BorderLayout());


        board.setLayout(new GridLayout(11, 11));
        board.setPreferredSize(new Dimension(900, 900));


        for (int i = 10; i >= 0; i--){
            for (int j = 10; j >= 0; j--){
                if (i == 0 && j != 10)
                    board.add(buttons[j]);
                else if (i != 10 && j == 10)
                    board.add(buttons[i + j]);
                else if (i == 10 && j != 0)
                    board.add(buttons[20 + (10-j)]);
                else if (i != 0 && j == 0)
                    board.add(buttons[30 + (10-i)]);
                else
                    board.add(new JLabel(" "));
            }
        }


        info.setLayout(new BorderLayout());

        infoTop.setSize(new Dimension(300, 900));
        infoCenter.setSize(new Dimension(300, 900));
        infoBottom.setSize(new Dimension(300, 900));


        //infoTop
            for (int i = 0; i < Monopoly.getPlayers().size(); i++){
                infoTop.add(new JLabel(Monopoly.getPlayers().get(i).toString()));
            }

        //JLabel playersInfo = new JLabel(game.infoActivePlayer());
        
        infoCenter.add(new JLabel("CENTER")); 

        infoBottom.add(new JLabel("BOTTOM")); 





        //adding to info
        info.add(infoTop, BorderLayout.NORTH);
        info.add(infoCenter, BorderLayout.CENTER);
        info.add(infoBottom, BorderLayout.SOUTH);

        //adding to the frame
        this.add(board);
        this.add(info);

    }

    public void actionPerformed(ActionEvent e) {
        
        for (int i = 0; i < 40; i++){
            if (e.getSource() == buttons[i]){
                System.out.println(i);
                System.out.println(Board.getSquares()[i].toString());
                break;
            }
        }
    }


    
}
