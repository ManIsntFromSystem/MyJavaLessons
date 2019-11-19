package MyAWTComponents;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CheckBoxDemo extends Frame implements ItemListener {
    private String msg = "";
    Checkbox windows, android, linux, mac;
    CheckboxGroup cbg;

    public CheckBoxDemo() throws HeadlessException {
        //setLayout(new FlowLayout());
        setLayout(new FlowLayout(FlowLayout.LEFT));
        cbg = new CheckboxGroup();

        windows = new Checkbox("Windows", cbg, true);
        android = new Checkbox("Android", cbg, false);
        linux = new Checkbox("Linux", cbg, false);
        mac = new Checkbox("Mac OS", cbg, false);

        add(windows);
        add(android);
        add(linux);
        add(mac);

        windows.addItemListener(this);
        android.addItemListener(this);
        linux.addItemListener(this);
        mac.addItemListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    public void paint(Graphics g) {
        msg = "Current state: ";
        g.drawString(msg, 20, 120);
        msg = " Windows: " + windows.getState();
        g.drawString(msg, 20, 140);
        msg = " Android: " + android.getState();
        g.drawString(msg, 20, 160);
        msg = " Solaris: " + linux.getState();
        g.drawString(msg, 20, 180);
        msg = " OS: " + mac.getState();
        g.drawString(msg, 20, 200);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        repaint();
    }
        public static void main(String[] args) {
        CheckBoxDemo checkBoxDemo = new CheckBoxDemo();

        checkBoxDemo.setSize(new Dimension(240, 200));
        checkBoxDemo.setTitle("CheckBoxDemo");
        checkBoxDemo.setVisible(true);
    }
}
