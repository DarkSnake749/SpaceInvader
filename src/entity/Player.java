package entity;

import java.util.*;

import main.GamePanel;
import main.KeyHandler;

import java.awt.Graphics2D;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    int startPosX;
    int startPosY;
    
    public List<Bullet> bullets = new ArrayList<Bullet>(0);

    boolean shoot;
    boolean shot = false;
    
    int currentShootingStep = 0;
    final int SHOOTING_MIN_STEP = 45;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        init();

    }

    public void init() {
        loadImage("/images/ship.png", 1);

        startPosX = gp.SCREEN_WIDTH / 2;
        startPosY = gp.SCREEN_HEIGHT - 60;
        shoot = false;

        width = sprite1.getWidth();
        height = sprite1.getHeight();

        x = startPosX - width / 2;
        y = startPosY;
        speed = 3;
    }

    public void behavior() {
        if (keyH.left) { x -= speed; }
        if (keyH.right) { x += speed; }

        if (keyH.shoot) { shoot = true; }
        else { shoot = false; }
    }

    public void check_collisions(List<Cover> covers) {
        /* 
        for (Cover cover : covers) {
        for (Blocker blocker : cover.blockers) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (
                bullet.y >= blocker.y && bullet.y <= blocker.bottom() &&
                bullet.x >= blocker.x && bullet.x <= blocker.right()
            ) { 
                bullets.remove(i);
                break;
            }
        }
        }
        } */

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
        for (Cover cover : covers) {
            if (
                bullet.x < cover.x || 
                bullet.x > cover.x + cover.width * cover.size
            ) { continue; }
        for (Blocker blocker : cover.blockers) {
            if (
                bullet.y >= blocker.y && bullet.y <= blocker.bottom() &&
                bullet.x >= blocker.x && bullet.x <= blocker.right()
            ) { 
                bullets.remove(i);
                break;
            }
            }
        }
        }
    }

    public void bullets_update(List<Cover> covers) {
        check_collisions(covers);

        if (shoot && !shot) {
            bullets.add(
                new Bullet(gp, x + width / 2, y, bullets.size()));
            shot = true;
            currentShootingStep = 0;
        }

        if (shot) {
            currentShootingStep++;
            shot = (currentShootingStep >= SHOOTING_MIN_STEP) ? false : true;
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();

            if (bullet.y + bullet.height < 0) { 
                bullets.remove(i); 
                continue;
            }
            bullets.set(i, bullet);
        }
    }

    public void update(List<Cover> covers) {
        behavior();
        bullets_update(covers);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite1, null, x, y);

        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }
    }
}
