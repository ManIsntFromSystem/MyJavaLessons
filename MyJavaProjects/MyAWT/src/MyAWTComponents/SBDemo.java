package MyAWTComponents;

import java.awt.*;
import java.awt.event.*;

public class SBDemo extends Frame implements AdjustmentListener {
    private String msg = "";
    private Scrollbar vertSB, horzSB;

    private SBDemo() throws HeadlessException {
        setLayout(new FlowLayout());

        //create lines scrolls and set their offers size
        vertSB = new Scrollbar(Scrollbar.VERTICAL, 0,1,0,200);
        vertSB.setPreferredSize(new Dimension(100,20));

        horzSB = new Scrollbar(Scrollbar.HORIZONTAL, 0,1,0,100);
        horzSB.setPreferredSize(new Dimension(100,20));

        //include lines scroll in frame
        add(vertSB);
        add(horzSB);

        //add listeners to lines scroll
        vertSB.addAdjustmentListener(this);
        horzSB.addAdjustmentListener(this);

        //include MouseListener
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                vertSB.setValue(y);
                horzSB.setValue(x);
                repaint();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    @Override
    public final void adjustmentValueChanged(AdjustmentEvent e) {
        repaint();
    }
    //show current position mouse
    @Override
    public final void paint(Graphics g) {
        msg = "Vertical: " + vertSB.getValue();
        msg += ", Horizontal: " + horzSB.getValue();
        g.drawString(msg, 20,160);
        g.drawString("*", horzSB.getValue(),vertSB.getValue());
    }

    public static void main(String[] args) {
        SBDemo sbDemo = new SBDemo();
        sbDemo.setSize(new Dimension(300, 220));
        sbDemo.setTitle("SBDemo");
        sbDemo.setVisible(true);
    }
}
