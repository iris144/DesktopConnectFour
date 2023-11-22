package four;

import java.awt.*;

public enum Player {
    X_PIECE(Color.red),
    O_PIECE(Color.blue),
    EMPTY_PIECE(Color.white),
    WINNING_PIECE(Color.green);



    private Color color;

    private Player(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }

   /*
    private final String name;

    GamePieces(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    */
}
