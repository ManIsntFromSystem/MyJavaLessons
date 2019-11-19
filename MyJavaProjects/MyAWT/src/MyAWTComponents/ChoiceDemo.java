package MyAWTComponents;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChoiceDemo extends Frame implements ItemListener {
    Choice os, browser;
    String msg = "";

    public ChoiceDemo() throws HeadlessException {
        setLayout(new FlowLayout());
        os = new Choice();
        browser = new Choice();

        os.add("Windows");
        os.add("Android");
        os.add("Linux");
        os.add("Mac OS");

        browser.add("Internet Explorer");
        browser.add("Firefox");
        browser.add("Chrome");

        add(os);
        add(browser);

        os.addItemListener(this);
        browser.addItemListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        msg = "Current OS: ";
        msg += os.getSelectedItem();
        g.drawString(msg, 20, 120);
        msg = "Current Browser: ";
        msg += browser.getSelectedItem();
        g.drawString(msg, 20, 140);
    }

    public static void main(String[] args) {
        ChoiceDemo choiceDemo = new ChoiceDemo();

        choiceDemo.setSize(new Dimension(240, 180));
        choiceDemo.setTitle("ChoiceDemo");
        choiceDemo.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        repaint();
    }
}
