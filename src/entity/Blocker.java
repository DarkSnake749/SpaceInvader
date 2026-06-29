package entity;

import java.util.*;

import java.awt.Graphics2D;
import java.awt.Color;

import main.GamePanel;

public class Blocker extends Entity {
    GamePanel gp;

    public boolean visible = true;

    public Blocker(GamePanel gp, int posX, int posY, int size) {
        this.gp = gp;
        this.x = posX;
        this.y = posY;
        this.width = size;
        this.height = size;
    }

    public void check_collisions(List<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            if (
                bullet.y >= y && bullet.y <= bottom() &&
                bullet.x >= x && bullet.x <= right()
            ) { visible = false; }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.fillRect(x, y, 10, 10);
    }
}
