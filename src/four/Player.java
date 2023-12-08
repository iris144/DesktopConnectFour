package four;

import java.awt.*;

public enum Player {
    RED(Color.red),
    BLUE(Color.blue),
    EMPTY(Color.white),
    WIN(Color.green);

    private final Color color;
   Player(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

}
