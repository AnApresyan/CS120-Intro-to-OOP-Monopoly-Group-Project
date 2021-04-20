import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame{
    public MainWindow(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 1500);
        this.setVisible(true);

        JPanel mainMenu = new JPanel();
        JLabel label = new JLabel();

        ImageIcon image = new ImageIcon("./images/logo2.png");

        
        label.setText("Welcome to Alexander's and Anahit's Monopoly");
        /*JButton start = new JButton("START");
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                start.setVisible(false);
                
            }
        });*/
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        mainMenu.add(label);
        this.add(mainMenu);
        
        
    }

    
}
