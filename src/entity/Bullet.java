package entity;

import java.awt.Graphics2D;

import main.GamePanel;

public class Bullet extends Entity {
    GamePanel gp;

    public Bullet(GamePanel gp, int startX, int startY) {
        this.x = startX;
        this.y = startY;

        init();
    }

    public void init() {
        speed = 6;
        loadImage("/images/laser.png", 1);
    }

    public void update() {
        y -= speed;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite1, null, x, y);
    }
}
