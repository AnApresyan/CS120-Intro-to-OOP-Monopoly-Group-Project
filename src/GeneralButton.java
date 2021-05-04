import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class GeneralButton extends JButton{

    public GeneralButton(){
        super();
        setCustomDesign();
    }

    public GeneralButton(String text){
        super(text);
        setCustomDesign();
    }

    public void setCustomDesign(){
        this.setFocusable(false);
        this.setBackground(Color.white);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               // setBackground(Color.BLACK);
            //    setBackground(Color.BLACK);
            //     setOpaque(true);
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // setBackground(Color.BLACK);
                // setOpaque(true);
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // setBackground(Color.BLACK);
                // setOpaque(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // setBackground(Color.BLACK);
                // setOpaque(true);
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // setBackground(Color.BLACK);
                // setOpaque(true);
                
            }
        });

    }
    
}
