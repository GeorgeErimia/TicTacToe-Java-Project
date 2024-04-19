package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

public class TicTacToe implements ActionListener, MouseListener {
    private static final int GRID_SIZE = 3;
    private gameState state;
    private int[][] grid; // 0 = Empty, 1 = X, 2 = O
    private boolean isPlayerOne = true;
    private Scanner sc;

//    private MyFrame gameFrame;
//
//    private DrawingCanvas canvas;

    private JFrame frame = new JFrame();
    private JPanel title_panel = new JPanel();
    private JPanel button_panel = new JPanel();
    private JLabel textField = new JLabel();
    private JButton[][] buttons = new JButton[3][3];



    // Constructor
    public TicTacToe() {

        initializeComponents();

        // Initialize the grid with 0
        grid = new int[GRID_SIZE][GRID_SIZE];

        // Initialize the game's state to RUNNING
        this.state = gameState.RUNNING;

//        sc = new Scanner(System.in);
    }

    private void initializeComponents() {
        // Initialize the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.addMouseListener(this);

        // Initialize the Text field
        textField.setBackground(new Color(25, 25 ,25));
        textField.setForeground(new Color(25, 255 ,0));
        textField.setFont(new Font("Consolas", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        // Initialize the title panel
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0, 800, 100);

        // Add the text field to the title panel
        title_panel.add(textField);

        // Initialize button panel
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(0,0,50));

        // Add the buttons to the buttons panel
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setForeground(new Color(250,0,0));
            }
        }

        // Add the panels to the frame
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
    }

    public void runManually() {
        // Display the title for 2 seconds before starting the game
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while(state == gameState.RUNNING) {
            // Draw Grid - Intended for testing
            printGrid();

            // set the title field
            textField.setText(isPlayerOne ? "X Turn" : "O Turn");

            // check for endgame
            state = checkForEndGame();
        }

//        printGrid(); - Intended for testing

        // If endgame, print who won or if it's a draw
        System.out.println(state.toString());
        textField.setText(state.toString());
    }

    public void printGrid() {
        // Draw Grid
        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeMarker(int x, int y) {
        if(isPlayerOne) {
            placeX(x,y);
            buttons[x][y].setText("X");
        } else {
            placeO(x,y);
            buttons[x][y].setText("O");
        }
    }

    public void placeX(int x, int y) {
        grid[x][y] = (grid[x][y] == 0 ? 1 : 0);
    }

    public void placeO(int x, int y) {
        grid[x][y] = (grid[x][y] == 0 ? 2 : 0);
    }

    public void switchPlayers() {
        this.isPlayerOne = !this.isPlayerOne;
    }

    public void markButtonGreen(int x, int y) {
        buttons[x][y].setBackground(new Color(15, 150, 0));
        buttons[x][y].setForeground(Color.black);
        buttons[x][y].setContentAreaFilled(true);
    }

    public gameState checkForEndGame() {
        // TODO check for endGame possibilities

        // Check lines/columns
        for (int i = 0; i < GRID_SIZE; i++) {

            // There are three identical symbols on the line
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                // Color the winner squares
                if(grid[i][0] != 0) {
                    markButtonGreen(i,0);
                    markButtonGreen(i,1);
                    markButtonGreen(i,2);
                    if (grid[i][0] == 1) // X = 1    |     O = 2
                        return gameState.WIN_X;
                    else if (grid[i][0] == 2)
                        return gameState.WIN_O;
                }
            }

            // There are three identical symbols on the column
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                if(grid[0][i] != 0) {
                    markButtonGreen(0,i);
                    markButtonGreen(1,i);
                    markButtonGreen(2,i);
                    // Check who won
                    if (grid[0][i] == 1) // X = 1    |     O = 2
                        return gameState.WIN_X;
                    else if (grid[0][i] == 2)
                        return gameState.WIN_O;
                }
            }
        }

        // Check diagonals
        if(grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) {
            if(grid[1][1] != 0) {
                markButtonGreen(2,0);
                markButtonGreen(1,1);
                markButtonGreen(0,2);
                if(grid[1][1] == 1) {
                    return gameState.WIN_X;
                } else if (grid[1][1] == 2)
                    return gameState.WIN_O;
            }
        }

        if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            if(grid[0][0] != 0) {
                markButtonGreen(0,0);
                markButtonGreen(1,1);
                markButtonGreen(2,2);
                if(grid[1][1] == 1) {
                    return gameState.WIN_X;
                } else if (grid[1][1] == 2)
                    return gameState.WIN_O;
            }
        }

        // Check for free spaces
        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
                if(grid[i][j] == 0)
                    return gameState.RUNNING;
            }
        }

        return gameState.DRAW;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                // Listen for button presses
                if(e.getSource() == buttons[i][j] && state == gameState.RUNNING) {
                    // Place the X/O on the grid
                    placeMarker(i,j);

                    // Switch to the other player
                    switchPlayers();
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
//            reset();
        }
    }

//    private void reset() {
//
//        // Reset Grid
//        grid = null;
//        grid = new int[3][3];
//
//        // Reset Game State
//        state = gameState.RUNNING;
//
//        // Reset Buttons
//        for(int i = 0; i < 3; i++) {
//            for(int j = 0; j < 3; j++) {
//                buttons[i][j].setText("");
//                buttons[i][j].setForeground(new Color(250,0,0));
//                buttons[i][j].setContentAreaFilled(false);
//            }
//        }
//    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
