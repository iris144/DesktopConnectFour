package four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ResetButton extends JButton
{

    public ResetButton(ActionListener resetGrid){

        this.setName("ButtonReset");
        this.setText("Reset");

        this.addActionListener(resetGrid);

        this.setBackground(new Color(51, 51, 51));
        this.setForeground(Color.white);
        this.setFocusPainted(false);
    }
}
