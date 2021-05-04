import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;


public class WinWindow extends JFrame implements ActionListener{
    private JLabel          message;
    private GeneralButton   exit;
    private GeneralButton   playAgain;
    private JPanel          buttons;
    private JPanel          text;
    private JLabel          center;

    public WinWindow(Player player){
        super("Monopoly");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);                 //this.setSize(1300, 750);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        //this.getContentPane().setBackground(new Color(252, 243, 207));
        //this.getContentPane().setOpaque(true);
        
        center = new JLabel();

        ImageIcon icon = new ImageIcon("./images/icon.png");
        this.setIconImage(icon.getImage());

        // ImageIcon bg = new ImageIcon("./images/try.png");
        // center.setIcon(bg);

        buttons = new JPanel();
        text    = new JPanel();
        text.setLayout(new FlowLayout());

        buttons.setLayout(new GridLayout(1, 2));
        exit = new GeneralButton("Exit");
        playAgain = new GeneralButton("Play again");
        exit.addActionListener(this);
        playAgain.addActionListener(this);

        
        message = new JLabel("Dear " + player.getName() + ", Congratulations! You are a true Monopolist");
        message.setForeground(new Color(146, 43, 33));
        //message1.setPreferredSize(new Dimension(100, 20));
        message.setFont(new Font("Futura", Font.BOLD, 15));
       
       
       
        text.add(message);
        // text.setBackground(new Color(252, 243, 207));
        // text.setOpaque(true);
        
        
        buttons.add(exit);
        buttons.add(playAgain);

        this.add(text, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playAgain)
            new StartWindow();
        this.dispose(); 
    }
    
}
