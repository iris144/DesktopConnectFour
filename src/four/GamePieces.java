package four;

public enum GamePieces {
    X_PIECE("X"),
    O_PIECE("O"),
    EMPTY_PIECE(" ");


    private final String name;

    GamePieces(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
