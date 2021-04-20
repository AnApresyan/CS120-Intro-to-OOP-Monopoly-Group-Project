import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class MainWindow extends JFrame{
    private static int numberOfPlayers;

    public MainWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 1500);
        this.setVisible(true);
    
        //The images
        ImageIcon image = new ImageIcon("./images/logo2.png");
        
        //The main panel
        JPanel mainMenu = new JPanel();

        //Labels
        JLabel mainLabel = new JLabel("Welcome to Alexander's and Anahit's Monopoly");
        JLabel setUp = new JLabel();

        //Number of Players
        SpinnerModel values = new SpinnerNumberModel(3, 3, 6, 1);
        JSpinner howManyPlayers = new JSpinner(values);
        howManyPlayers.setVisible(false);
        JButton submitNumberOfPlayers = new JButton("Submit");
        submitNumberOfPlayers.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfPlayers = (Integer) howManyPlayers.getValue();
                System.out.println(numberOfPlayers);
            }

        });
        submitNumberOfPlayers.setVisible(false);

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

        //Adding to the setUp
        setUp.add(howManyPlayers);

        //Adding to the mainLabel
        mainLabel.setIcon(image);
        mainLabel.add(start);
        mainLabel.setHorizontalTextPosition(JLabel.CENTER);
        mainLabel.setVerticalTextPosition(JLabel.BOTTOM);
        mainLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        //Adding to the mainMenu
        mainMenu.add(mainLabel);
        mainMenu.add(start);
        mainMenu.add(howManyPlayers);
        mainMenu.add(submitNumberOfPlayers);
        this.add(mainMenu);
        
        
    }

    
}
