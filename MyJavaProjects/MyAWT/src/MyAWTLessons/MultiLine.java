package MyAWTLessons;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MultiLine extends Frame {

    private int curX = 20, curY = 40;
    private MultiLine() {
        Font f = new Font("SansSerif", Font.PLAIN, 12);
        setFont(f);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
        public final void paint(Graphics g) {
            FontMetrics fm = g.getFontMetrics();
            nextLine("This is on line one.", g);
            nextLine("This is on line two.", g);
            sameLine(" This is on same line.", g);
            sameLine(" This, too.", g);
            nextLine("This is line three.", g);
            curX = 20;
            curY = 40;
        }
        private void nextLine(String s, Graphics g) {
                FontMetrics fm = g.getFontMetrics();
                curY += fm.getHeight();
                curX = 20;
                g.drawString(s, curX, curY);
                curX += fm.stringWidth(s);
            }
        private void sameLine(String s, Graphics g){
                FontMetrics fm = g.getFontMetrics();
                g.drawString(s, curX, curY);
                curX += fm.stringWidth(s);
        }
    public static void main(String[] args) {
        MultiLine multiLine = new MultiLine();

        multiLine.setSize(new Dimension(300, 120));
        multiLine.setTitle("MultiLine");
        multiLine.setVisible(true);
    }
}
