package org.example;


import java.util.Scanner;

public class TicTacToe {
    private static final int GRID_SIZE = 3;
    private enum gameState {
        RUNNING,
        WIN_X,
        WIN_O,
        DRAW
    }

    private gameState state;
    private int[][] grid; // 0 = Empty, 1 = X, 2 = O

    private boolean isPlayerOne = true;

    private Scanner sc;



    // Constructor
    public TicTacToe() {
        // Initialize the grid with 0
        grid = new int[GRID_SIZE][GRID_SIZE];

        // Initialize the game's state to RUNNING
        this.state = gameState.RUNNING;

        sc = new Scanner(System.in);
    }

    public void runManually() {
        while(state == gameState.RUNNING) {
            // Draw Grid
            printGrid();

            // Request coordinates from user
            System.out.println("Select coord x : ");
            int x = sc.nextInt();
            System.out.println("Select coord y : ");
            int y = sc.nextInt();

            // Place the X/O on the grid
            placeMarker(x,y);

            // Switch to the other player
            switchPlayers();

            // check for endgame
            state = checkForEndGame();
        }

        printGrid();
        System.out.println(state.toString());
    }

    public void printGrid() {
        // Draw Grid
        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void placeMarker(int x, int y) {
        if(isPlayerOne) {
            placeX(x,y);
        } else {
            placeO(x,y);
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
}
