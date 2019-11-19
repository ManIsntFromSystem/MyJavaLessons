package MyAWTLessons;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShowFonts extends Frame {
    String msg = "First five fonts: ";
    GraphicsEnvironment ge;
    private ShowFonts() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] localFonts = ge.getAvailableFontFamilyNames();

        for (int i = 0; (i < 5) && (i < localFonts.length); i++) {
            msg += localFonts[i] + " | ";
        }
    }
    @Override
    public final void paint(Graphics g) {
        g.drawString(msg, 10, 60);
    }

    public static void main(String[] args) {
        ShowFonts showFonts = new ShowFonts();
        showFonts.setSize(new Dimension(500, 100));
        showFonts.setTitle("ShowFonts");
        showFonts.setVisible(true);
    }
}
