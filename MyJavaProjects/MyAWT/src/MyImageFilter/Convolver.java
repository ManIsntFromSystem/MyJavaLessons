package MyImageFilter;

import java.awt.*;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.MemoryImageSource;

abstract class Convolver implements ImageConsumer, PlugInFilter {
    int width, height;

    int imgpixels[], newimgpixels[];
    boolean imageReady = false;

    abstract void convolve(); //here follows filter

    public Image filter(Frame f, Image in) {
        imageReady = false;
        in.getSource().startProduction(this);
        waitForimage();
        newimgpixels = new int[width*height];
    try {
        convolve();
    } catch (Exception e) {
        System.out.println("Convolver failed: " + e);
        e.printStackTrace();
    }
    return f.createImage( new MemoryImageSource(width, height, newimgpixels,0, width));
    }
    synchronized void waitForimage() {
        try {
            while (!imageReady)
                wait();
        } catch (Exception e) {
            System.out.println("Interrupted");
        }
    }
    public void setProperties(java.util.Hashtable<?, ?> dummy){}
    public void setColorModel(ColorModel dummy) { }
    public void setHints(int dummy) { }

    public synchronized void imageComplete(int dummy) {
        imageReady = true;
        notifyAll();
    }
    public void setDimensions(int x, int y) {
        width = x;
        height = y;
        imgpixels = new int[x*y];
        }

        public void setPixels(int xl, int yl, int w, int h, ColorModel model,
                              byte pixels[], int off, int scansize) {
            int pix, x, y, x2, y2, sx, sy;
            x2 = xl+w;
            y2 = yl+h;
            sy = off;
            for(y=yl; y<y2; y++) {
                sx = sy;
                for(x=xl; x<x2; x++) {
                    pix = model.getRGB(pixels[sx++]);
                if( (pix & 0xff000000) == 0)
                    pix = 0x00ffffff;
                imgpixels[y*width+x] = pix;
                }
                sy += scansize;
            }
        }
        public void setPixels(int xl, int yl, int w, int h,
                              ColorModel model, int pixels[], int off, int scansize) {
        int pix, x, y, x2, y2, sx, sy;
        x2 = xl+w;
        y2 = yl+h;
        sy = off;
        for (y=yl; y<y2; y++) {
            sx = sy;
            for(x=xl; x<x2; x++) {
                pix = model.getRGB(pixels[sx++]);
                if ((pix & 0xff000000) == 0)
                    pix = 0x00ffffff;
                imgpixels[y * width + x] = pix;
            }
            sy += scansize;
        }
    }
}