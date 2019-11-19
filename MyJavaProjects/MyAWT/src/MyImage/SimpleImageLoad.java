package MyImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class SimpleImageLoad extends Frame {
    Image img;

    public SimpleImageLoad() throws HeadlessException {
        try {
            File imageFile = new File("Решетки.JPG");
            //download image
            img = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Cannot load image file");
            System.exit(0);
        }
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, getInsets().left, getInsets().top, null);
    }

    public static void main(String[] args) {
        SimpleImageLoad simpleImageLoad = new SimpleImageLoad();

        simpleImageLoad.setSize(new Dimension(400, 365));
        simpleImageLoad.setTitle("SimpleImageLoad");
        simpleImageLoad.setVisible(true);
    }
}
