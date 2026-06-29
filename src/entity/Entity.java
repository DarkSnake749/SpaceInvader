package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity {
    public int x, y, width, height;
    int speed;

    public BufferedImage sprite1, sprite2;

    public void loadImage(String path, int sprite) {
        try {
            if (sprite == 1) {
                sprite1 = ImageIO.read(getClass().getResourceAsStream(path));
                return;
            }

            sprite2 = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            System.err.println("Wasn't able to load resource at " + path);
            e.printStackTrace();
        }
    }

    public int bottom() {
        return y + height;
    }

    public int right() {
        return x + width;
    }

    public void draw(Graphics2D g2) {
    }
}