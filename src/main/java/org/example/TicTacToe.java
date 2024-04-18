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

    private gameState state = gameState.RUNNING;
    private int[][] grid; // 0 = Empty, 1 = X, 2 = O

    private boolean isPlayerOne = true;

    private Scanner sc;



    // Constructor
    public TicTacToe() {
        // Initialize the grid with 0
        grid = new int[GRID_SIZE][GRID_SIZE];


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

            // Check for endgame
            checkForEndGame();

            // Switch to the other player
            switchPlayers();
        }

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

    public void checkForEndGame() {
        // TODO check for endGame possibilities
    }
}
