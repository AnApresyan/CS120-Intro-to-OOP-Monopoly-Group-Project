import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class GeneralButton extends JButton
{
    public  GeneralButton()
    {
        super();
        setCustomDesign();
    }

    public  GeneralButton(String text)
    {
        super(text);
        setCustomDesign();
    }

    public void setCustomDesign()
    {
        this.setFocusable(false);
        this.setBackground(Color.white);
        this.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {

            }

            @Override
            public void mouseReleased(MouseEvent e)
            {

            }

            @Override
            public void mouseEntered(MouseEvent e)
            {

            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                
            }
        });
    }
}
