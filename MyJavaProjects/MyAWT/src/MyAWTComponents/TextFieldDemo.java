package MyAWTComponents;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TextFieldDemo extends Frame implements ActionListener {
    private TextField name, pass;

    private TextFieldDemo() {
        setLayout(new FlowLayout());

        Label namep = new Label("Name: ", Label.RIGHT);
        Label passp = new Label("Password: ", Label.RIGHT);
        name = new TextField(12);
        pass = new TextField(8);
        pass.setEchoChar('?');

        add(namep);
        add(name);
        add(passp);
        add(pass);

        name.addActionListener(this);
        pass.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    @Override
       public final void paint(Graphics g) {
        g.drawString("Name: "
                + name.getText(), 20, 100);
        g.drawString("Selected text in name: "
                + name.getSelectedText(), 20, 120);
        g.drawString("Password: "
                + pass.getText(), 20, 140);
    }

    //user passed button Enter<>
    @Override
    public final void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {
        TextFieldDemo textFieldDemo = new TextFieldDemo();
        textFieldDemo.setSize(new Dimension(380, 180));
        textFieldDemo.setTitle("TextFieldDemo");
        textFieldDemo.setVisible(true);
    }
}
