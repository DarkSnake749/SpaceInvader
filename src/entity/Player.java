package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.Graphics2D;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    int startPosX;
    int startPosY;
    boolean shoot;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        init();

    }

    public void init() {
        startPosX = gp.SCREEN_WIDTH / 2;
        startPosY = gp.SCREEN_HEIGHT - 60;
        shoot = false;

        x = startPosX;
        y = startPosY;
        speed = 3;

        loadImage("/images/ship.png", 1);
    }

    public void movement() {
        if (keyH.left) { x -= speed; }
        if (keyH.right) { x += speed; }
        if (keyH.shoot) { shoot = true; }
    }

    public void update() {
        movement();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite1, null, x, y);
    }
}
