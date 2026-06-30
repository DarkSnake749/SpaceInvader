package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.*;

import main.GamePanel;

public class Waves extends Entity {
    GamePanel gp;
    public List<Enemy> enemies = new ArrayList<Enemy>();
    public List<Bullet> enemyBullets = new ArrayList<Bullet>();

    Random randomGen = new Random();

    int speedY;

    int size;
    int spacingX;
    int spacingY;

    int spriteIdx = 0;
    BufferedImage[][] sprites = {{null, null}, {null, null}, {null, null}};

    int moveMinStep;
    int speedIncrease;
    int currentMoveStep = 0;
    int moveDir = 1;

    public Waves (
        GamePanel gp, 
        int startX, int startY, 
        int size, int spacingX, int spacingY, 
        int speed, int speedY,
        int startStep, int speedIncrease ){

        this.gp = gp;

        this.x = startX;
        this.y = startY;

        this.size = size;
        this.spacingX = spacingX;

        this.speed = speed;
        this.speedY = speedY;
        this.moveMinStep = startStep;
        this.speedIncrease = speedIncrease;

        init();
    }

    public void init() {
        loadImgs();

        width = 10;
        height = 6;

        int type = 2;
        int stepX = size + spacingX;
        int stepY = size + spacingY;

        for (int _y = 0; _y < height; _y++) {
            if (_y == 1) { type = 1; }
            if (_y == 3) { type = 0; }
        for (int _x = 0; _x < width; _x++) {

            enemies.add(
                new Enemy(gp, _x * stepX + x, _y * stepY + y, type, size));
        }
        }

        width *= stepX;
        height *= stepY;
    }

    private void loadImgs() {
        loadImage("/images/enemies/e1-1.png", 1);
        sprites[0][0] = sprite1;
        loadImage("/images/enemies/e1-2.png", 1);
        sprites[0][1] = sprite1;

        loadImage("/images/enemies/e2-1.png", 1);
        sprites[1][0] = sprite1;
        loadImage("/images/enemies/e2-2.png", 1);
        sprites[1][1] = sprite1;

        loadImage("/images/enemies/e3-1.png", 1);
        sprites[2][0] = sprite1;
        loadImage("/images/enemies/e3-2.png", 1);
        sprites[2][1] = sprite1;
    }

    public boolean needToShoot() {
        return randomGen.nextInt(401) == 0 ? true : false;
    }

    public int randomIdx() {
        return randomGen.nextInt(enemies.size());
    }

    public void updateBullets(List<Cover> covers) {
        for (int i = 0; i < enemyBullets.size(); i++) {
            Bullet eBullet = enemyBullets.get(i);
            if (!eBullet.visible) { enemyBullets.remove(i); }
            if (eBullet.checkCollisions(covers, enemies)) { enemyBullets.remove(i); continue; }
            eBullet.update();
        }
    }

    public void update(List<Bullet> bullets, List<Cover> covers) {
        int oldMoveDir = moveDir;
        boolean yMove = false;
        boolean xMove = false;

        boolean needShot = needToShoot();
        int shootIdx = needShot ? randomIdx() : 0;

        currentMoveStep++;
        if (currentMoveStep >= moveMinStep) {
            spriteIdx = spriteIdx == 1 ? 0 : 1;
            currentMoveStep = 0;
            xMove = true;
            
            x += speed * moveDir;
            if (right() >= gp.SCREEN_WIDTH || x <= 0) {
                yMove = true;
                moveMinStep = moveMinStep > 8 ? moveMinStep - speedIncrease : 8;
                moveDir = -moveDir;
                y += speed;
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (!enemy.visible) { 
                enemies.remove(i); 
                continue;
            }

            if (needShot && i == shootIdx) { enemyBullets.add(
                new Bullet(gp, enemy.x + enemy.width / 2, enemy.y + enemy.height, true)); }

            if (xMove) { enemy.x += speed * oldMoveDir; }
            if (yMove) { 
                enemy.y += speedY; 
            }

            enemy.checkCollisions(bullets);
        }

        updateBullets(covers);
    }

    @Override
    public void draw(Graphics2D g2) {
        for (Enemy enemy : enemies) {
            if (!enemy.visible) { continue; }
            enemy.draw(g2, sprites[enemy.type][spriteIdx]);
        }
        for (Bullet eBullet : enemyBullets) {
            eBullet.draw(g2);
        }
    }
}
