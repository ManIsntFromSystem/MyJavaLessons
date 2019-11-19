package MyImage;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;

public class MemoryImageGenerator extends Frame {
    Image img;
    int w = 512, h = 512;
    public MemoryImageGenerator() throws HeadlessException {
        int pixels[] = new int[w * h];
        int i = 0;
        for (int j = 0; j < h; j++) {
            for (int k = 0; k < w; k++) {
                int r = (j ^ k) & 0xff;
                int g = (j*2 ^ k*2) & 0xff;
                int b = (j*4 ^ k*4) & 0xff;
                pixels[i++] = (255 << 24) | (r << 16) | (g << 8) | b;
            }
        }
        img = createImage(new MemoryImageSource(w, h, pixels,0, w));
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
        MemoryImageGenerator memoryImageGenerator = new MemoryImageGenerator();
        memoryImageGenerator.setSize(new Dimension(528,550));
        memoryImageGenerator.setTitle("MemoryImageGenerator");
        memoryImageGenerator.setVisible(true);
    }
}
