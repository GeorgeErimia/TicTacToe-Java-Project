package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawingCanvas extends JComponent {

    private int width;
    private int height;

    private static final int GRID_SIZE = 3;

    public DrawingCanvas(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }

    private void drawSquares(Graphics2D g2d) {

    }
}
