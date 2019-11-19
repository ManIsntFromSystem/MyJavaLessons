package MyImageFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

public class ImageFilterDemo extends Frame implements ActionListener{
    Image img;
    PlugInFilter pif;
    Blur blur;
    Image fImg;
    Image curImg;
    LoadedImage lim;
    Label lab;
    Button reset;

    String[] filters = {"Grayscale", "Invert", "Contrast", "Blur", "Sharpen"};

    public ImageFilterDemo() throws HeadlessException {
        Panel p = new Panel();
        add(p, BorderLayout.SOUTH);

        reset = new Button("Reset");
        reset.addActionListener(this);
        p.add(reset);

        for(String fstr : filters) {
            Button b = new Button(fstr);
            b.addActionListener(this);
            p.add(b);
        }

        //create new up points
        lab = new Label("");
        add(lab, BorderLayout.NORTH);

        try {
            //download image
            File file = new File("ruin.jpg");

            img = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("Cannot load image file.");
            System.exit(0);
        }
        lim = new LoadedImage(img);
        add(lim, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String a = "";
        try {
            a = ae.getActionCommand();
            System.out.println(a);
            if (a.equals("Reset")){
            lim.set(img);
            lab.setText("Normal");
            } else if(a.equals("Contrast")){
                pif = (PlugInFilter) (new Contrast());
                fImg = pif.filter(this, img);
                lim.set(fImg);
                lab.setText("Filtered: " + a);
            } else if(a.equals("Grayscale")){
                pif = (PlugInFilter) (new Grayscale());
                fImg = pif.filter(this, img);
                lim.set(fImg);
                lab.setText("Filtered: " + a);
            } else if(a.equals("Blur")){
                pif = (PlugInFilter) (new Blur());
                fImg = pif.filter(this, img);
                lim.set(fImg);
                lab.setText("Filtered: " + a);
            } else if(a.equals("Sharpen")){
                pif = (PlugInFilter) (new Sharpen());
                fImg = pif.filter(this, img);
                lim.set(fImg);
                lab.setText("Filtered: " + a);
            }//Get the selected filter
            else if(a.equals("Invert")){
                pif = (PlugInFilter) (new Invert());
                //pif = (PlugInFilter) (Class.forName(a))
                //       .getConstructor().newInstance();
                fImg = pif.filter(this, img);
                lim.set(fImg);
                lab.setText("Filtered: " + a);
            }
            repaint();
        } catch (Exception ce) {
            lab.setText("no");
        }/*
        catch (ClassNotFoundException ce){
            lab.setText(a + " not found");
            lim.set(img);
            repaint();
        } catch (InstantiationException ie) {
            lab.setText("couldn't new " + a);
        } catch (IllegalAccessException ile) {
            lab.setText("no access: " + a);
        } catch (NoSuchMethodException | InvocationTargetException nme) {
            lab.setText("Filter creation error: " + nme);
        }*/
    }

    public static void main(String[] args) {
        ImageFilterDemo imageFilterDemo = new ImageFilterDemo();
        imageFilterDemo.setSize(new Dimension(520, 520));
        imageFilterDemo.setTitle("ImageFilterDemo");
        imageFilterDemo.setVisible(true);

    }
}
