package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    final int SCREEN_WIDTH = 1080;
    final int SCREEN_HEIGHT = 720;

    KeyHandler keyH = new KeyHandler();
    Clock clockH = new Clock();
    Thread gameThread;

    // Player
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        clockH.delta = 0;
        clockH.lastTime = System.nanoTime();

        while (gameThread != null) {
            boolean timing = clockH.clockHandler();

            if (!timing) {
                continue;
            }

            update();
            repaint();
        }
    }

    public void update() {
        if (keyH.up) {
            playerY -= playerSpeed;
        }
        if (keyH.down) {
            playerY += playerSpeed;
        }
        if (keyH.left) {
            playerX -= playerSpeed;
        }
        if (keyH.right) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, 40, 40);
        g2.dispose();
    }

}
