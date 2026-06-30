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

    public void checkCollisions(List<Cover> covers, List<Enemy> enemies) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (bullet.checkCollisions(covers, enemies)) { bullets.remove(i); }
        }
    }

    public void bullets_update(List<Cover> covers, List<Enemy> enemies) {
        checkCollisions(covers, enemies);

        if (shoot && !shot) {
            bullets.add(
                new Bullet(gp, x + width / 2, y, false));
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

    public void update(List<Cover> covers, List<Enemy> enemies) {
        behavior();
        bullets_update(covers, enemies);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(sprite1, null, x, y);

        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }
    }
}
