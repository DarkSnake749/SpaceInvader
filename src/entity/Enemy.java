package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Enemy extends Entity {
    GamePanel gp;
    public int type;
    public boolean visible = true;

    int spriteIdx = 0;
    BufferedImage[] sprites = {null, null};

    public Enemy(GamePanel gp, int startX, int startY, int type, int size) {
        this.gp = gp;

        this.x = startX;
        this.y = startY;

        this.width = size;
        this.height = size;

        init();
    }

    public void init() {
        switch (type) {
        case 0:
            loadImage("/images/enemies/e1-1.png", 1);
            loadImage("/images/enemies/e1-2.png", 2);
            break;
        case 1:
            loadImage("/images/enemies/e2-1.png", 1);
            loadImage("/images/enemies/e2-2.png", 2);
            break;
        case 2:
            loadImage("/images/enemies/e3-1.png", 1);
            loadImage("/images/enemies/e3-2.png", 2);
            break;
        
        default:
            System.err.println("Invalid enemy type");
            break;
        }

        sprites[0] = sprite1;
        sprites[1] = sprite2;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(sprites[spriteIdx], x, y, width, height, null);
    }
}
