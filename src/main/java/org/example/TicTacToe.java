package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TicTacToe implements ActionListener {
    private static final int GRID_SIZE = 3;

    private gameState state;
    private int[][] grid; // 0 = Empty, 1 = X, 2 = O

    private boolean isPlayerOne = true;

    private Scanner sc;

//    private MyFrame gameFrame;
//
//    private DrawingCanvas canvas;

    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();

    JButton[][] buttons = new JButton[3][3];



    // Constructor
    public TicTacToe() {

        // Initialize the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // Initialize the Text field
        textField.setBackground(new Color(25, 25 ,25));
        textField.setForeground(new Color(25, 255 ,0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        // Initialize the title panel
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0, 800, 100);

        // Add the text field to the title panel
        title_panel.add(textField);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150, 150 ,150));


        // Add the title panel to the frame
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        // Add the buttons to the buttons panel
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }



        // Initialize the grid with 0
        grid = new int[GRID_SIZE][GRID_SIZE];

        // Initialize the game's state to RUNNING
        this.state = gameState.RUNNING;

        sc = new Scanner(System.in);
    }

    public void runManually() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while(state == gameState.RUNNING) {
            // Draw Grid
            printGrid();

            // set the title field
            textField.setText(isPlayerOne ? "X Turn" : "O Turn");

//            // Request coordinates from user
//            System.out.println("Select coord x : ");
//            int x = sc.nextInt();
//            System.out.println("Select coord y : ");
//            int y = sc.nextInt();

//            // Place the X/O on the grid
//            placeMarker(x,y);


            // check for endgame
            state = checkForEndGame();
        }

        printGrid();
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

    public gameState checkForEndGame() {
        // TODO check for endGame possibilities

        // Check lines/columns
        for (int i = 0; i < GRID_SIZE; i++) {

            // There are three identical symbols on the line
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                // Check who won
                if (grid[i][0] == 1) // X = 1    |     O = 2
                    return gameState.WIN_X;
                else if (grid[i][0] == 2)
                    return gameState.WIN_O;
            }

            // There are three identical symbols on the column
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                // Check who won
                if (grid[0][i] == 1) // X = 1    |     O = 2
                    return gameState.WIN_X;
                else if (grid[0][i] == 2)
                    return gameState.WIN_O;
            }
        }

        // Check diagonals
        if((grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2])
        || (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2])) {
            if(grid[1][1] == 1) {
                return gameState.WIN_X;
            } else if (grid[1][1] == 2)
                return gameState.WIN_O;
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
                if(e.getSource() == buttons[i][j]) {
                    // Place the marker on the grid
                    placeMarker(i,j);

                    // Switch to the other player
                    switchPlayers();
                }
            }
        }
    }
}
