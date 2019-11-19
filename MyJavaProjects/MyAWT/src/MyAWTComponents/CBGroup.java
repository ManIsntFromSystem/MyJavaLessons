package MyAWTComponents;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CBGroup extends Frame implements ItemListener {
    private String msg = "";
    private Checkbox windows, android, linux, mac;
    private CheckboxGroup cbg;

    private CBGroup() throws HeadlessException {
        setLayout(new FlowLayout());
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
    public final void paint(Graphics g) {
            msg = "Current selection: ";
            msg += cbg.getSelectedCheckbox().getLabel();
            g.drawString(msg, 20, 120);
        }

    @Override
    public final void itemStateChanged(ItemEvent e){
            repaint();
        }
        public static void main(String[] args) {
            CBGroup cbGroup = new CBGroup();

            cbGroup.setSize(new Dimension(240, 180));
            cbGroup.setTitle("CBGroup");
            cbGroup.setVisible(true);
        }
}

