package org.example;

import javax.swing.*;
import java.awt.*;

public class ShapeDrawing extends JComponent {

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(100, 150, 60, 200);
        g2d.drawOval(0,0, 10,10);
    }
}
