import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.*;


public class WinWindow extends JFrame implements ActionListener{
    private JLabel  message;
    private JButton exit;
    private JButton playAgain;

    public WinWindow(Player player){
        super("Monopoly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);                 //this.setSize(1300, 750);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        
        exit = new JButton("exit");
        playAgain = new JButton("Play again");
        exit.addActionListener(this);
        playAgain.addActionListener(this);


        message = new JLabel();
        message.setText("Dear " + player.getName() + "Congratulations! You are a true Monopolist");

        this.add(message, BorderLayout.NORTH);
        this.add(exit, BorderLayout.CENTER);
        this.add(playAgain, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playAgain)
            new StartWindow();
        this.dispose(); 
    }
    
}
