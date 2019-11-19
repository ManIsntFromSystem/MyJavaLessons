package MyAWTLessons;

import java.awt.*;
import java.awt.event.*;

public class MyAWTOne extends Frame implements MouseListener, MouseMotionListener {
    String msg = "";
    int mouseX = 0, mouseY = 0;

    // координаты положения курсора мыши
    public MyAWTOne() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(new MyWindowAdapter());
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        msg = msg + " -- click received";
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Button down";
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Button Released";
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        mouseX = 100;
        mouseY = 100;
        msg = "Mouse entered.";
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent me) {
        mouseX = 100;
        mouseY = 100;
        msg = "Mouse exited.";
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        //save coordinates
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "*" + " mouse at " + mouseX + "";
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        msg = "Moving mouse at " + me.getX() + ", " + getY();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString(msg, mouseX, mouseY);
    }

    public static void main(String[] args) {
        MyAWTOne appwin = new MyAWTOne();

        appwin.setSize(new Dimension (300, 300));
        appwin.setTitle("Mou seEventsDemo");
        appwin.setVisible(true);
    }
}

    class MyWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }