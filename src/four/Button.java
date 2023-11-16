package four;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Button extends JButton {
    String columnLabels = "ABCDEFG";
    String rowLabels = "654321";
    int column;
    int row;
    String coordinate;

    public Button(int column, int row) {
        this.column = column;
        this.row = row;
        this.coordinate = (columnLabels.charAt(column) + "" + rowLabels.charAt(row));
        this.setName("Button" + coordinate);
        //setBorder(new RoundedBorder(20));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setBackground(new Color(0, 51, 51));
        setForeground(Color.white);
        setFocusPainted(false);
        setBorderPainted(true);
        setText(GamePieces.EMPTY_PIECE.getName());
    }



    private static class RoundedBorder implements Border {

        private int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }


}
