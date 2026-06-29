package entity;

import java.awt.image.BufferedImage;

import main.GamePanel;

public class Enemy extends Entity {
    GamePanel gp;
    public int type;

    BufferedImage[] sprites = {null, null};

    public Enemy(GamePanel gp, int startX, int startY, int type) {
        this.gp = gp;
        this.x = startX;
        this.y = startY;

        init();
    }

    public void init() {
        switch (type) {
            case 0:
                loadImage("/images/enemies/e1-1", 1);
                loadImage("/images/enemies/e1-2", 2);
                break;
            case 1:
                loadImage("/images/enemies/e2-1", 1);
                loadImage("/images/enemies/e2-2", 2);
                break;
            case 2:
                loadImage("/images/enemies/e3-1", 1);
                loadImage("/images/enemies/e3-2", 2);
                break;
        
            default:
                System.err.println("Invalid enemy type");
                break;
        }

        sprites[0] = sprite1;
        sprites[1] = sprite2;
    }
}
