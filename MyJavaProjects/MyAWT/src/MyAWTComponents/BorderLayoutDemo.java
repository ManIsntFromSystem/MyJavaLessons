package MyAWTComponents;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BorderLayoutDemo extends Frame {
    public BorderLayoutDemo() throws HeadlessException {

        add(new Button("This is across the top."), BorderLayout.NORTH);
        add(new Button("The footer massage might go here."), BorderLayout.SOUTH);
        add(new Button("Right"), BorderLayout.EAST);
        add(new Button("Left"), BorderLayout.WEST);

        String msg = "The reasonable man adapts" +
                "himself to the world; \n" +
                "the unreasonable one persists in" +
                "trying to adapt the world to himself.\n"
                + "Therefore all progress depends " +
                "the man.\n\n" +
                "           - George Bernard Shaw\n";
        add(new TextArea(msg), BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        BorderLayoutDemo borderLayoutDemo = new BorderLayoutDemo();

        borderLayoutDemo.setSize(new Dimension(300, 220));
        borderLayoutDemo.setTitle("BorderLayoutDemo");
        borderLayoutDemo.setVisible(true);
    }
}
