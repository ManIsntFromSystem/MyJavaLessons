package MyAWTLessons;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SampleFonts extends Frame {
    int next = 0;
    Font f;
    String msg;
    public SampleFonts() {
        f = new Font("Dialog", Font.PLAIN, 12);
        msg = "Dialog";
        setFont(f);
        addMouseListener(new MyMouseAdapter(this));
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    @Override
    public final void paint(Graphics g) {
    g.drawString(msg,10,60);
    }

    public static void main(String[] args) {
        SampleFonts sampleFonts = new SampleFonts();

        sampleFonts.setSize(new Dimension(200, 100));
        sampleFonts.setTitle("SampleFonts");
        sampleFonts.setVisible(true);
    }
}

 class MyMouseAdapter extends MouseAdapter {
     SampleFonts smpF;
    public MyMouseAdapter(SampleFonts sampleFonts) {
        this.smpF = sampleFonts;
        }

     @Override
     public void mousePressed(MouseEvent e) {
         smpF.next++;
         switch (smpF.next) {
             case 0:
                 smpF.f = new Font("Dialog", Font.PLAIN, 12);
                 smpF.msg = "Dialog";
                 break;
             case 1:
                 smpF.f = new Font("DialogInput", Font.PLAIN, 12);
                 smpF.msg = "Dialoglnput";
                 break;
             case 2:
                 smpF.f = new Font("SansSerif", Font.PLAIN, 12);
                 smpF.msg = "SansSerif";
                 break;
             case 3:
                 smpF.f = new Font("Serif", Font.PLAIN, 12);
                 smpF.msg = "Serif";
                 break;
             case 4:
                 smpF.f = new Font("Monospaced", Font.PLAIN, 12);
                 smpF.msg = "Monospaced";
                 break;
         }
         if (smpF.next == 4) smpF.next = -1;
         smpF.setFont(smpF.f);
         smpF.repaint();
     }
 }
