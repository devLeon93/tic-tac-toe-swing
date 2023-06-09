package org.tictac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {

    public JButton[][] board;
    public boolean playerX;
    public int numMoves;

    public TicTacToe() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        board = new JButton[3][3];
        playerX = true;
        numMoves = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new JButton("");
                board[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                board[i][j].addActionListener(this);
                add(board[i][j]);
            }
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("")) {
            if (playerX) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            playerX = !playerX;
            numMoves++;


            if (checkForWin()) {
                JOptionPane.showMessageDialog(this, (playerX ? "O" : "X") + " wins!");
                resetGame();
            } else if (numMoves == 9) {
                JOptionPane.showMessageDialog(this, "Tie game!");
                resetGame();
            }
        }
    }

    private boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].getText().equals("") &&
                    board[i][0].getText().equals(board[i][1].getText()) &&
                    board[i][1].getText().equals(board[i][2].getText())) {
                return true;
            }
        }


        for (int j = 0; j < 3; j++) {
            if (!board[0][j].getText().equals("") &&
                    board[0][j].getText().equals(board[1][j].getText()) &&
                    board[1][j].getText().equals(board[2][j].getText())) {
                return true;
            }
        }


        if (!board[0][0].getText().equals("") &&
                board[0][0].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][2].getText())) {
            return true;
        }

        if (!board[0][2].getText().equals("") &&
                board[0][2].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][0].getText())) {
            return true;
        }

        return false;
    }

    private void resetGame() {
        playerX = true;
        numMoves = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}