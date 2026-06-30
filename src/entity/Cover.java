package entity;

import java.util.*;
import java.awt.Graphics2D;

import main.GamePanel;

public class Cover extends Entity {
    GamePanel gp;
    
    public List<Blocker> blockers = new ArrayList<Blocker>();
    public int size;

    public Cover (GamePanel gp, int posX, int posY, int width, int height, int size) {
        this.gp = gp;

        this.x = posX;
        this.y = posY;

        this.width = width;
        this.height = height;

        this.size = size;

        init();
    }

    public void init() {
        for (int _y = 0; _y < height; _y++) {
        for (int _x = 0; _x < width; _x++) {
            blockers.add(
                new Blocker(gp, _x * size + x, _y * size + y, size));
        }
        }
    }

    public void update(List<Bullet> bullets, List<Bullet> enemyBullets) {
        for (int i = 0; i < blockers.size(); i++) {
            Blocker blocker = blockers.get(i);

            if (!blocker.visible) {
                blockers.remove(i);
                continue;
            }

            blocker.check_collisions(bullets);
            blocker.check_collisions(enemyBullets);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Blocker blocker : blockers) {
            if (blocker.visible) {
                blocker.draw(g2);
            }
        }
    }
}
