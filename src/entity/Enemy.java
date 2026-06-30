package entity;

import java.util.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Enemy extends Entity {
    GamePanel gp;

    public int type;
    public boolean visible = true;

    public Enemy(GamePanel gp, int startX, int startY, int type, int size) {
        this.gp = gp;

        this.x = startX;
        this.y = startY;

        this.width = size;
        this.height = size;

        this.type = type;
    }

    public void checkCollisions(List<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            if (bullet.y >= y && bullet.y <= bottom() && 
                bullet.x >= x && bullet.x <= right() ) 
            { visible = false; }
        }
    }

    public void draw(Graphics2D g2, BufferedImage img) {
        g2.drawImage(img, x, y, width, height, null);
    }
}
