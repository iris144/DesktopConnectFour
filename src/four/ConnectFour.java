package four;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ConnectFour extends JFrame {

    private boolean playerX = true;
    private final int row = 6;
    private final int column = 7;
    private final Button[][] checkButtons = new Button[row][column];
    private final JPanel mainPanel;
    private final JPanel lowerPanel;
    private boolean gameEnded = false;
    private final ArrayList<Button> checkCorrectButtons = new ArrayList<>();



    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setTitle("Connect Four");
        setLocationRelativeTo(null);


        // creating possible panel for saying whose turn it is and who won
        JPanel upperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //creating new panel for the playboard
        mainPanel = new JPanel(new GridLayout(6, 7, 10, 10));
        playBoard();

        //creating new panel for the reset button
        lowerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lowerPanel.add(new ResetButton(e -> resetGrid()));


        getContentPane().setLayout(new BorderLayout());
        //getContentPane().add(upperPanel,BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(lowerPanel, BorderLayout.SOUTH);
        mainPanel.setBackground(Color.black);

        setVisible(true);
    }

    public void playBoard() {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                Button button = new Button(column, row);
                mainPanel.add(button);
                button.addActionListener(e -> addPieceTo(button));
                checkButtons[row][column] = button;
            }
        }
    }

    private void resetGrid() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                var c = checkButtons[i][j];
                c.setText(" ");
                c.setPlayer(Player.EMPTY);
                c.setEnabled(true);
            }
        }
        checkCorrectButtons.clear();
        gameEnded = false;
        mainPanel.repaint();
        playerX = true;
    }

    public void playGame(Button button) {
        if (playerX) {
            button.setPlayer(Player.RED);
            playerX = false;
        } else {
            button.setPlayer(Player.BLUE);
            playerX = true;
        }
    }

    public void addPieceTo(Button button) {
        if(gameEnded){
            return;
        }
        if (!Objects.equals(button.player, Player.EMPTY)) {
            return;
        }
        // checks from bottom to up if button is empty. So that when you click it appears at the bottom.
        for (int j = 5; j >= 0; j--) {
            if (Objects.equals(checkButtons[j][button.column].player, Player.EMPTY)) {
                playGame(checkButtons[j][button.column]);
                break;
            }
        }
        if(fourInARow()) {
            gameEnded = true;
        }

    }

    public boolean fourInARow() {

        boolean winDetected = findHorizontal() || findVertical() ||
                findBackwardDiagonal() || findForwardDiagonal();

        if(winDetected){
            changeButtonColour(checkCorrectButtons);
            return true;
        }
        return false;
    }

    public boolean findHorizontal() {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column - 3; j++) {
                Button checkFirstButton = checkButtons[i][j];
                Button checkSecondButton = checkButtons[i][j + 1];
                Button checkThirdButton = checkButtons[i][j + 2];
                Button checkFourthButton = checkButtons[i][j + 3];

                if (find(checkFirstButton, checkSecondButton, checkThirdButton, checkFourthButton)) return true;
            }
        }
        return false;
    }

    private boolean find(Button checkFirstButton, Button checkSecondButton, Button checkThirdButton, Button checkFourthButton) {
        Player playerFirstButton = checkFirstButton.player;
        Player playerSecondButton = checkSecondButton.player;
        Player playerThirdButton = checkThirdButton.player;
        Player playerFourthButton = checkFourthButton.player;

        if ((playerFirstButton.equals(Player.RED) || playerFirstButton.equals(Player.BLUE)) &&
                playerFirstButton.equals(playerSecondButton) &&
                playerSecondButton.equals(playerThirdButton) &&
                playerThirdButton.equals(playerFourthButton)) {


            checkCorrectButtons.add(checkFirstButton);
            checkCorrectButtons.add(checkSecondButton);
            checkCorrectButtons.add(checkThirdButton);
            checkCorrectButtons.add(checkFourthButton);
            return true;
        }
        return false;
    }

    public boolean findVertical() {

        for (int i = 0; i < row - 3; i++) {
            for (int j = 0; j < column; j++) {
                Button checkFirstButton = checkButtons[i][j];
                Button checkSecondButton = checkButtons[i + 1][j];
                Button checkThirdButton = checkButtons[i + 2][j];
                Button checkFourthButton = checkButtons[i + 3][j];

                if (find(checkFirstButton, checkSecondButton, checkThirdButton, checkFourthButton)) return true;
            }
        }
        return false;
    }

    public boolean findForwardDiagonal() {
        for (int i = 3; i < row; i++) {
            for (int j = 0; j < column - 3; j++) {
                Button checkFirstButton = checkButtons[i][j];
                Button checkSecondButton = checkButtons[i - 1][j + 1];
                Button checkThirdButton = checkButtons[i - 2][j + 2];
                Button checkFourthButton = checkButtons[i - 3][j + 3];

                if (find(checkFirstButton, checkSecondButton, checkThirdButton, checkFourthButton)) return true;
            }
        }
        return false;

    }

    public boolean findBackwardDiagonal() {
        for (int i = 0; i < row - 3; i++) {
            for (int j = 0; j < column - 3; j++) {
                Button checkFirstButton = checkButtons[i][j];
                Button checkSecondButton = checkButtons[i + 1][j + 1];
                Button checkThirdButton = checkButtons[i + 2][j + 2];
                Button checkFourthButton = checkButtons[i + 3][j + 3];

                if (find(checkFirstButton, checkSecondButton, checkThirdButton, checkFourthButton)) return true;
            }
        }
        return false;
    }

    public void changeButtonColour(ArrayList<Button> buttons) {
        for (Button button : buttons) {
            button.setPlayer(Player.WIN);
        }
    }
}

