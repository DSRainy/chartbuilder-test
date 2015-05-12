package com.seniorproject.augmentedreality.algorithm;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.WritableRaster;

/**
 *
 * @author RainWhileLoop
 */
public class ColorConverter {

    public int[] hsvPixel;
    public float[] huePixel, saturationPixel, brightnessPixel;
    public int[] pixel, alphaPixel, redPixel, greenPixel, bluePixel;
    private int imageWidth, imageHeight, imageSize;

    private BufferedImage bufferedImage;
    private Image hueImage, saturationImage, brightnessImage, hsvImage;

    public void init() {
        pixel = new int[imageSize];
        alphaPixel = new int[imageSize];
        redPixel = new int[imageSize];
        greenPixel = new int[imageSize];
        bluePixel = new int[imageSize];
        hsvPixel = new int[imageSize];
        huePixel = new float[imageSize];
        saturationPixel = new float[imageSize];
        brightnessPixel = new float[imageSize];
    }

    public void process() {
        getPixel();
        separateColor();
        convertRGBtoHSV();
        createHSVImage();
    }

    private void createHSVImage() {
        Frame frame = new Frame();
        hsvImage = frame.createImage(new MemoryImageSource(imageWidth, imageHeight, hsvPixel, 0, imageWidth));
    }

    public BufferedImage createHSVImage2() {
        return getImageFromArray(hsvPixel, imageWidth, imageHeight);
    }

    private void convertRGBtoHSV() {
        int r;
        int g;
        int b;
        float max, min;
        float hsv[] = new float[3];
        float delta;
        for (int i = 0; i < imageSize; i++) {
            r = redPixel[i];
            g = greenPixel[i];
            b = bluePixel[i];
//            r /= 255.0f;
//            g /= 255.0f;
//            b /= 255.0f;
//            max = Math.max(Math.max(r, g), b);
//            min = Math.min(Math.min(r, g), b);
//            delta = max - min;
//            hsv[2] = max;
//            hsv[1] = (max == 0.0f) ? 0.0f : delta / max;
//
//            if (r == max) {
//                hsv[0] = 60.0f * (g - b) / delta + (g < b ? 6 : 0);
//            } else if (g == max) {
//                hsv[0] = 60.0f * (b - r) / delta + 2;
//            } else {
//                hsv[0] = 60.0f * (r - g) / delta + 4;
//            }
//            if (hsv[0] < 0.0f) {
//                hsv[0] += 360.0f;
//            }

            hsv = Color.RGBtoHSB(r, g, b, hsv);
            hsvPixel[i] = Color.getHSBColor(hsv[0], 1, 1).getRGB();
//            huePixel[i] = (hsvPixel[i] >> 16) & 0xFF;
//            saturationPixel[i] = (hsvPixel[i] >> 8) & 0xFF;
//            brightnessPixel[i] = hsvPixel[i] & 0xFF;
            huePixel[i] = hsv[0];
            saturationPixel[i] = hsv[1];
            brightnessPixel[i] = hsv[2];
        }
    }

    public void getPixel() {
        int i = 0;
        for (int y = 0; y < imageHeight; y++) {
            for (int x = 0; x < imageWidth; x++) {
                pixel[i] = bufferedImage.getRGB(x, y);
                i++;
            }
        }
    }

    public void separateColor() {
        for (int i = 0; i < imageSize; i++) {
            alphaPixel[i] = pixel[i] >> 24 & 0xFF;
            redPixel[i] = pixel[i] >> 16 & 0xFF;
            greenPixel[i] = pixel[i] >> 8 & 0xFF;
            bluePixel[i] = pixel[i] & 0xFF;
        }
    }

    public Image getHueImage() {
        return hueImage;
    }

    public Image getSaturationImage() {
        return saturationImage;
    }

    public Image getBrightnessImage() {
        return brightnessImage;
    }

    public Image getHsvImage() {
        return hsvImage;
    }

    public void setSourceImage(BufferedImage bufferedImage) {
        bufferedImage.getScaledInstance(150, 140, Image.SCALE_DEFAULT);
        this.bufferedImage = bufferedImage;
        imageWidth = bufferedImage.getWidth();
        imageHeight = bufferedImage.getHeight();
        imageSize = imageWidth * imageHeight;
        init();
    }

    public static BufferedImage getImageFromArray(int[] pixels, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0, 0, width, height, pixels);
        return image;
    }

    public BufferedImage getHsvImageBufferedImage() {
        BufferedImage bimage = new BufferedImage(150, 140, BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr;
        bGr = bimage.createGraphics();
        bGr.drawImage(getHsvImage(), 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    public int[] getHsvPixel() {
        return hsvPixel;
    }

}
