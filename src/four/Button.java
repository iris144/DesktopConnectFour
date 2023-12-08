package four;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Button extends JButton {
    String columnLabels = "ABCDEFG";
    String rowLabels = "654321";
    int column;
    int row;
    String coordinate;
    Player player = Player.EMPTY;

    public void setPlayer(Player player) {
        this.player = player;
        this.repaint();
    }

    public Button(int column, int row) {
        this.column = column;
        this.row = row;
        this.coordinate = (columnLabels.charAt(column) + "" + rowLabels.charAt(row));
        this.setName("Button" + coordinate);
        this.setRolloverEnabled(false);
        setForeground(Color.black);
        setFocusPainted(false);
    }



    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int diameter = Math.min(getWidth(), getHeight());
        int xOffset = (getWidth() - diameter) / 2;
        int yOffset = (getHeight() - diameter) / 2;
        Shape circle = new Ellipse2D.Float(xOffset, yOffset, diameter, diameter);

        g2d.setColor(getColor());
        g2d.fill(circle);
        g2d.dispose();
    }

    private Color getColor() {
        return player.getColor();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Suppress painting the border
    }


}









