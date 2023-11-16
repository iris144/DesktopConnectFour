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
    private boolean gameEnded = false;
    private final ArrayList<Button> checkCorrectButtons = new ArrayList<>();



    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        setTitle("Connect Four");

        mainPanel = new JPanel(new GridLayout(6, 7, 10, 10));
        mainPanel.setPreferredSize(new Dimension(300, 300));
        playBoard();

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.addActionListener(e -> resetGrid());
        resetButton.setBackground(new Color(51, 51, 51));
        resetButton.setForeground(Color.white);
        resetButton.setFocusPainted(false);
        resetButton.setEnabled(true);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(resetButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().setBackground(new Color(204, 229, 204));

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
                c.setBackground(new Color(0, 51, 51));
                c.setEnabled(true);
            }
        }
        checkCorrectButtons.clear();


        //mainPanel.removeAll();
        //playBoard();
        gameEnded = false;
        mainPanel.revalidate();
        mainPanel.repaint();
        playerX = true;
    }

    public void playGame(Button button) {
        if (playerX) {
            button.setText(GamePieces.X_PIECE.getName());
            playerX = false;
        } else {
            button.setText(GamePieces.O_PIECE.getName());
            playerX = true;
        }
    }

    public void addPieceTo(Button button) {
        if(gameEnded){
            return;
        }
        if (!Objects.equals(button.getText(), GamePieces.EMPTY_PIECE.getName())) {
            return;
        }
        // checks from bottom to up if button is empty. So that when you click it appears at the bottom.
        for (int j = 5; j >= 0; j--) {
            if (Objects.equals(checkButtons[j][button.column].getText(), GamePieces.EMPTY_PIECE.getName())) {
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

                String textFirstButton = checkFirstButton.getText();
                String textSecondButton = checkSecondButton.getText();
                String textThirdButton = checkThirdButton.getText();
                String textFourthButton = checkFourthButton.getText();

                if ((textFirstButton.equals("X") || textFirstButton.equals("O")) &&
                        textFirstButton.equals(textSecondButton) &&
                        textSecondButton.equals(textThirdButton) &&
                        textThirdButton.equals(textFourthButton)) {


                    checkCorrectButtons.add(checkFirstButton);
                    checkCorrectButtons.add(checkSecondButton);
                    checkCorrectButtons.add(checkThirdButton);
                    checkCorrectButtons.add(checkFourthButton);
                    return true;
                }
            }
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

                String textFirstButton = checkFirstButton.getText();
                String textSecondButton = checkSecondButton.getText();
                String textThirdButton = checkThirdButton.getText();
                String textFourthButton = checkFourthButton.getText();

                if ((textFirstButton.equals("X") || textFirstButton.equals("O")) &&
                        textFirstButton.equals(textSecondButton) &&
                        textSecondButton.equals(textThirdButton) &&
                        textThirdButton.equals(textFourthButton)) {

                    checkCorrectButtons.add(checkFirstButton);
                    checkCorrectButtons.add(checkSecondButton);
                    checkCorrectButtons.add(checkThirdButton);
                    checkCorrectButtons.add(checkFourthButton);
                    return true;
                }
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

                String textFirstButton = checkFirstButton.getText();
                String textSecondButton = checkSecondButton.getText();
                String textThirdButton = checkThirdButton.getText();
                String textFourthButton = checkFourthButton.getText();

                if ((textFirstButton.equals("X") || textFirstButton.equals("O")) &&
                        textFirstButton.equals(textSecondButton) &&
                        textSecondButton.equals(textThirdButton) &&
                        textThirdButton.equals(textFourthButton)) {

                    checkCorrectButtons.add(checkFirstButton);
                    checkCorrectButtons.add(checkSecondButton);
                    checkCorrectButtons.add(checkThirdButton);
                    checkCorrectButtons.add(checkFourthButton);
                    return true;
                }
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

                String textFirstButton = checkFirstButton.getText();
                String textSecondButton = checkSecondButton.getText();
                String textThirdButton = checkThirdButton.getText();
                String textFourthButton = checkFourthButton.getText();

                if ((textFirstButton.equals("X") || textFirstButton.equals("O")) &&
                        textFirstButton.equals(textSecondButton) &&
                        textSecondButton.equals(textThirdButton) &&
                        textThirdButton.equals(textFourthButton)) {



                    checkCorrectButtons.add(checkFirstButton);
                    checkCorrectButtons.add(checkSecondButton);
                    checkCorrectButtons.add(checkThirdButton);
                    checkCorrectButtons.add(checkFourthButton);

                    return true;
                }
            }
        }
        return false;
    }

    public void changeButtonColour(ArrayList<Button> buttons) {
        for (Button button : buttons) {
            button.setBackground(Color.GREEN);
        }
    }
}

