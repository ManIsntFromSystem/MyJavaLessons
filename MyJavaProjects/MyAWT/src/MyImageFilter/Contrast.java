package MyImageFilter;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class Contrast extends RGBImageFilter implements PlugInFilter {
    @Override
    public Image filter(Frame f, Image in) {
        return f.createImage(new FilteredImageSource(in.getSource(), this));
    }

    private int multclamp(int in, double factor) {
        in = (int)  (in * factor);
        return in > 255 ? 255 : in;
    }
    double gain = 1.2;
    private  int count(int in) {
        return (in < 128) ? (int) (in/gain) : (multclamp(in, gain));
    }
    @Override
    public int filterRGB(int x, int y, int rgb) {
        int r = count(0xff - (rgb >> 16) & 0xff);
        int g = count(0xff - (rgb >> 8) & 0xff);
        int b = count(0xff - rgb  & 0xff);
        return (0xff000000 | r << 16 | g << 8 | b);
    }
}
