package MyAWTComponents;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CardLayoutDemo extends Frame {
    private Checkbox windowslO, windows7, windows8, android, solaris, mac;
    private Panel osCards;
    private CardLayout cardLO;
    private Button Win, Other;

    private CardLayoutDemo() {
        setLayout(new FlowLayout());

        Win = new Button("Windows");
        Other = new Button("Other");
        add(Win);
        add(Other);

        cardLO = new CardLayout();
        osCards = new Panel();
        osCards.setLayout(cardLO);

        windows7 = new Checkbox("Windows 7", true);
        windows8 = new Checkbox("Windows 8");
        windowslO = new Checkbox("Windows 10");
        android = new Checkbox("Android");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("OS");

        Panel winPan = new Panel();
        winPan.add(windows7);
        winPan.add(windows8);
        winPan.add(windowslO);

        Panel otherPan = new Panel();
        otherPan.add(android);
        otherPan.add(mac);
        otherPan.add(solaris);

        osCards.add(winPan, "Windows");
        osCards.add(otherPan, "Other");

        add(osCards);

        Win.addActionListener((ae) -> cardLO.show(osCards, "Windows"));
        Other.addActionListener((ae) -> cardLO.show(osCards, "Other"));
        addMouseMotionListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                cardLO.next(osCards);
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        CardLayoutDemo cardLayoutDemo = new CardLayoutDemo();

        cardLayoutDemo.setSize(new Dimension(300, 220));
        cardLayoutDemo.setTitle("CardLayoutDemo");
        cardLayoutDemo.setVisible(true);
    }
}

