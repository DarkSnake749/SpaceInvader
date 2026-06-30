package entity;

import java.util.*;
import java.awt.Graphics2D;

import main.GamePanel;

public class Bullet extends Entity {
    GamePanel gp;
    public boolean enemyType;

    boolean visible = true;

    public Bullet(GamePanel gp, int startX, int startY, boolean enemyType) {
        this.gp = gp;
        
        this.x = startX;
        this.y = startY;
        
        this.enemyType = enemyType;

        init();
    }

    public void init() {
        speed = 6;

        if (enemyType) {loadImage("/images/enemies/e-laser.png", 1);}
        else { loadImage("/images/laser.png", 1); }
    }

    public boolean checkCollisions(List<Cover> covers, List<Enemy> enemies) {
        // Collisions for blocker block
        for (Cover cover : covers) {
            if (
                x < cover.x || 
                x > cover.x + cover.width * cover.size
            ) { continue; }
        for (Blocker blocker : cover.blockers) {
            if (
                y >= blocker.y && y <= blocker.bottom() &&
                x >= blocker.x && x <= blocker.right()
            ) { 
                return true;
            }
            }
        }

        if (enemyType) { return false; }
        
        // Collision for enemies
        for (Enemy enemy : enemies) {
            if (
                y >= enemy.y && y <= enemy.bottom() &&
                x >= enemy.x && x <= enemy.right()
            ) {
                return true;
            }
        }
        return false;
    }

    public void update() {
        if (y < 0 || y > gp.SCREEN_HEIGHT) { 
            visible = false; 
            return;
        }

        if (enemyType) {
            y += speed / 2;
            return;
        }
        y -= speed;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (!visible) { return; }
        g2.drawImage(sprite1, null, x, y);
    }
}
