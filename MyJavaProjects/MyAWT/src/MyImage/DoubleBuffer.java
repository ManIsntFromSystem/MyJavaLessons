package MyImage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DoubleBuffer extends Frame {
    int gap = 3;
    int mx, my;
    boolean flicker = true;
    Image buffer = null;
    int w = 400, h = 400;
    public DoubleBuffer() {
        addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent me) {
                mx = me.getX();
                my = me.getY();
                flicker = false;
                repaint();
            }

            public void mouseMoved(MouseEvent me) {
                mx = me.getX();
                my = me.getY();
                flicker = true;
                repaint();
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    public void paint(Graphics g) {
        Graphics screengc = null;

        if (!flicker) {
            screengc = g;
            g = buffer.getGraphics();
        }
            g.setColor(Color.blue);
            g.fillRect(0,0, w, h);

            g.setColor(Color.red);
            for (int i = 0; i < w; i += gap) {
                g.drawLine(0, i, w - i, h);
            }
            for (int i = 0; i < h; i += gap){
                g.drawLine(0, i, w, h - i);
            }
            g.setColor(Color.black);
            g.drawString("Press mouse button to buffer", 10, h / 2);
            g.setColor(Color.yellow);
            g.fillOval(mx - gap, my - gap, gap * 2 + 1, gap * 2 + 1);
            if (!flicker) {
                screengc.drawImage(buffer, 0, 0, null);
            }
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    public static void main(String[] args) {
        DoubleBuffer doubleBuffer = new DoubleBuffer();

        doubleBuffer.setSize(new Dimension(400, 400));
        doubleBuffer.setTitle("DoubleBuffer");
        doubleBuffer.setVisible(true);

        //create an off-screen buffer
        doubleBuffer.buffer = doubleBuffer.createImage(doubleBuffer.w, doubleBuffer.h);
    }
}
