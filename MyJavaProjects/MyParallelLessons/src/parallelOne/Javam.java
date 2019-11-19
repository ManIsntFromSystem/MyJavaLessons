package parallelOne;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Javam  extends Frame {
    public Javam() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        g.drawLine(20, 40, 100, 90);
        g.drawLine(20, 90, 100, 40);
        g.drawLine(40, 45, 250, 80);
        g.drawRect(20, 150, 60, 50);
        g.fillRect(110, 150, 60, 50);
        g.drawRoundRect(200, 150, 60, 50, 15, 15);
        g.fillRoundRect(290, 150, 60, 50, 30, 40);
        g.drawOval(20, 250, 50, 50);
        g.fillOval(100, 250, 75, 50);
        g.drawOval(200, 260, 100, 40);
        g.drawArc(20, 350, 70, 70, 0, 180);
        g.fillArc(70, 350, 70, 70, 0, 75);
        int xpoints[] = {20, 200, 20, 200, 20};
        int ypoints[] = {45, 45, 65, 65, 45};
        int num = 5;
        g.drawPolygon(xpoints, ypoints, num);
        g.drawPolygon(new int[]{20, 200, 20, 200, 20}, new int[]{450, 450, 650, 650, 450}, 4);
    }

    public static void main(String[] args) {
        Javam javam = new Javam();
        javam.setSize(new Dimension(370, 700));
        javam.setTitle("GraphicsDemo");
        javam.setVisible(true);
    }
}
