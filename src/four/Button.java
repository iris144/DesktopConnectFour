package four;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Button extends JButton {
    String columnLabels = "ABCDEFG";
    String rowLabels = "654321";
    int column;
    int row;
    String coordinate;

    public void setPlayer(Player player) {
        this.player = player;
        this.repaint();
    }

    Player player = Player.EMPTY_PIECE;

    public Button(int column, int row) {
        this.column = column;
        this.row = row;
        this.coordinate = (columnLabels.charAt(column) + "" + rowLabels.charAt(row));
        this.setName("Button" + coordinate);
        //setBorder(new RoundedBorder(100));
        //setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setForeground(Color.black);
        setFocusPainted(false);
        setBorderPainted(true);


    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight());
        Shape circle = new Ellipse2D.Float(0, 0, diameter, diameter);

        g2.setColor(getColor());
        g2.fill(circle);
//        g2.setColor(getForeground());
//        g2.drawString(getText(), getWidth() / 2 - g2.getFontMetrics().stringWidth(getText()) / 2, getHeight() / 2 + g2.getFontMetrics().getAscent() / 2);

        g2.dispose();
    }

    private Color getColor() {
        return player.getColor();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Suppress painting the border
    }
}









