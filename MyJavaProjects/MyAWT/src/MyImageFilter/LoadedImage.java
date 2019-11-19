package MyImageFilter;

import java.awt.*;

public class LoadedImage extends Canvas {
    Image img;

    public LoadedImage(Image i) {
        set(i);
    }

    final void set(Image i) {
        img = i;
        repaint();
    }

    @Override
    public final void paint(Graphics g) {
        if (img == null) {
            g.drawString("no image", 10, 30);
        } else {
            g.drawImage(img, 0, 0, this);
        }
    }

    public final Dimension getPreferredSize() {
        return new Dimension(img.getWidth(this),
                img.getHeight(this));
    }

    public final Dimension getMinimumSize() {
        return getPreferredSize();
    }
}
