package MyImageFilter;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class Grayscale extends RGBImageFilter implements PlugInFilter {

    public Grayscale() {
    }
    public Image filter(Frame f, Image in) {
        return f.createImage(new FilteredImageSource(in.getSource(), this));
    }

    @Override
    public int filterRGB(int x, int y, int rgb) {
        int r = 0xff - (rgb >> 16) & 0xff;
        int g = 0xff - (rgb >> 8) & 0xff;
        int b = 0xff - rgb  & 0xff;
        int k = (int) (.56 * g + .33 * r + .11 * b);
        return (0xff000000 | k << 16 | k << 8 | k);
    }
}
