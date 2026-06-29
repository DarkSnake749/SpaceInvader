package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    int startPosX;
    int startPosY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        init();

    }

    public void init() {
        startPosX = gp.SCREEN_WIDTH / 2;
        startPosY = gp.SCREEN_HEIGHT - 60;

        x = startPosX;
        y = startPosY;
        speed = 3;
    }

    public void movement() {
        if (keyH.up) {
            y -= speed;
        }
        if (keyH.down) {
            y += speed;
        }
        if (keyH.left) {
            x -= speed;
        }
        if (keyH.right) {
            x += speed;
        }
    }

    public void update() {
        movement();
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, 40, 40);
    }
}
