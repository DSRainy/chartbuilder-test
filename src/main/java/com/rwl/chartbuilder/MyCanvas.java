/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rwl.chartbuilder;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author RainWhileLoop
 */
public class MyCanvas extends Canvas {

    private Image image;
    private int width = 0;
    private int height = 0;

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, this.width, this.height, null);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;

    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
