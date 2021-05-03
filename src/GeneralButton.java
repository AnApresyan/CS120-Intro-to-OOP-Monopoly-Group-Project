import java.awt.Color;

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
    }
    
}
