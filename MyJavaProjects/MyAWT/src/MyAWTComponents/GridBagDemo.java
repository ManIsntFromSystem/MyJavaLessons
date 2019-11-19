package MyAWTComponents;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GridBagDemo extends Frame implements ItemListener {
    private String msg = "";
    private Checkbox windows, android, solaris, mac;
    private GridBagDemo() {
        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gbag);

        windows = new Checkbox("Windows ", true);
        android = new Checkbox("Android");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("Mac OS");

        gbc.weightx = 1.0;

        gbc.ipadx = 200;

        gbc.insets = new Insets(0, 6, 0,0);

        gbc.anchor = GridBagConstraints.NORTHEAST;

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(windows, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(android, gbc);

        gbc.weighty = 1.0;

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbag.setConstraints(solaris, gbc);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbag.setConstraints(mac, gbc);

        add(windows);
        add(android);
        add(solaris);
        add(mac);
        //зарегистрировать приемники событий элементах
        windows.addItemListener(this);
        android.addItemListener(this);
        solaris.addItemListener(this);
        mac.addItemListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
        //воспроизвести повторно, когда изменится состояние флажка
    public final void itemStateChanged(ItemEvent ie) {
        repaint();
    }
    // отобразить текущее состояние флажков
    public final void paint(Graphics g) {
        msg = "Current state: ";
        g.drawString(msg, 20, 100);
        msg = " Windows: " + windows.getState();
        g.drawString(msg, 30, 120);
        msg = " Android: " + android.getState();
        g.drawString(msg, 30, 140);
        msg = " Solaris: " + solaris.getState();
        g.drawString(msg, 30, 160);
        msg = " Мае: " + mac.getState();
        g.drawString(msg, 30, 180);
    }
    public static void main(String[] args) {
        GridBagDemo gridBagDemo = new GridBagDemo();

        gridBagDemo.setSize(new Dimension(250, 200));
        gridBagDemo.setTitle("GridBagDemo");
        gridBagDemo.setVisible(true);
    }
}
