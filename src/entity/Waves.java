package entity;

import java.util.*;
import main.GamePanel;

public class Waves extends Entity {
    GamePanel gp;
    public List<Enemy> enemies = new ArrayList<Enemy>();

    public Waves (GamePanel gp, int startX, int startY) {
        this.gp = gp;

        this.x = startX;
        this.y = startY;
    }

    public void init() {
        width = 10;
        height = 6;

        int type = 2;
        for (int _y = 0; _y < height; y++) {
        for (int _x = 0; _x < width; x++) {
            // TODO -----------------------
        }
        }
    }
}
