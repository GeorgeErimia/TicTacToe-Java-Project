package org.example;

import javax.swing.*;
import java.awt.*;

public class GridDrawing extends JComponent {

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(5,5,800,800);


    }
}
