package four;

public enum Player {;
    //PLAYER_X,
    //PLAYER_O;

    private boolean isTurn;

    Player(boolean turn) {
        this.isTurn = turn;
    }
    public boolean isTurn() {
        return isTurn;
    }

    public boolean setTurn() {
        return false;
    }
}
