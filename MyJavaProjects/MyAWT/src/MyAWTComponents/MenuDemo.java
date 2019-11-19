package MyAWTComponents;

import java.awt.*;
import java.awt.event.*;

public class MenuDemo extends Frame {
    String msg = "";
    private CheckboxMenuItem debug, test;
    private MenuDemo() {
        MenuBar mBar = new MenuBar();
        setMenuBar(mBar);

        Menu file = new Menu("File");
        MenuItem item1, item2, item3, item4, item5;

        file.add(item1 = new MenuItem("New... "));
        file.add(item2 = new MenuItem("Open... "));
        file.add(item3 = new MenuItem("Close"));
        file.add(item4 = new MenuItem("-"));
        file.add(item5 = new MenuItem("Quit... "));
        mBar.add(file);

        Menu edit = new Menu("Edit");
        MenuItem item6, item7, item8, item9;
        edit.add(item6 = new MenuItem("Cut"));
        edit.add(item7 = new MenuItem("Copy"));
        edit.add(item8 = new MenuItem("Paste"));
        edit.add(item9 = new MenuItem("-"));

        Menu sub = new Menu("Special");
        MenuItem item10, item11, item12;
        sub.add(item10 = new MenuItem("First"));
        sub.add(item11 = new MenuItem("Second"));
        sub.add(item12 = new MenuItem("Third"));
        edit.add(sub);

        edit.add(debug = new CheckboxMenuItem("Debug"));
        edit.add(debug);
        test = new CheckboxMenuItem( "Testing");
        edit.add(test);

        mBar.add(edit);

        MyMenuHandler handler = new MyMenuHandler();
        item1.addActionListener(handler);
        item2.addActionListener(handler);
        item3.addActionListener(handler);
        item4.addActionListener(handler);
        item6.addActionListener(handler);
        item7.addActionListener(handler);
        item8.addActionListener(handler);
        item9.addActionListener(handler);
        item10.addActionListener(handler);
        item11.addActionListener(handler);
        item12.addActionListener(handler);

        debug.addItemListener(handler);
        test.addItemListener(handler);


        item5.addActionListener((ae) -> System.exit(0));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public final void paint(Graphics g) {
        g.drawString(msg, 10, 220);
        if(debug.getState())
            g.drawString("Debug is on. ", 10, 240);
        else
            g.drawString("Debug is off. ", 10, 240);

        if(test.getState())
            g.drawString("Testing is on. ", 10, 260);
        else
            g.drawString("Testing is off. ", 10, 260);
    }

    public static void main(String[] args) {
        MenuDemo menuDemo = new MenuDemo();

        menuDemo.setSize(new Dimension(250, 300));
        menuDemo.setTitle("MenuDemo");
        menuDemo.setVisible(true);
    }

    class MyMenuHandler implements ActionListener, ItemListener {
        @Override
        public final void actionPerformed(ActionEvent e) {
            msg = "You selected ";
            String arg = e.getActionCommand();
            switch (arg) {
                case "New...":
                    msg += "New.";
                    break;
                case "Open...":
                    msg += "Open.";
                    break;
                case "Close...":
                    msg += "Close.";
                    break;
                case "Edit":
                    msg += "Edit.";
                    break;
                case "Cut":
                    msg += "Cut.";
                    break;
                case "Copy":
                    msg += "Copy.";
                    break;
                case "Paste":
                    msg += "Paste.";
                    break;
                case "First":
                    msg += "First.";
                    break;
                case "Second":
                    msg += "Second.";
                    break;
                case "Third":
                    msg += "Third.";
                    break;
                case "Debug":
                    msg += "Debug.";
                    break;
                case "Testing":
                    msg += "Testing.";
                    break;
            }
            repaint();
        }

        @Override
        public final void itemStateChanged(ItemEvent e) {
            repaint();
        }
    }
}
