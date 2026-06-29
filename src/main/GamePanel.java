package main;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    public final int SCREEN_WIDTH = 1080;
    public final int SCREEN_HEIGHT = 720;

    BufferedImage bgImg = null;

    KeyHandler keyH = new KeyHandler();
    Clock clockH = new Clock();
    Thread gameThread;

    Player player = new Player(this, keyH);

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

        init_bg();
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
        player.update();
    }

    public void init_bg() {
        try {
            bgImg = ImageIO.read(getClass().getResourceAsStream("/images/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if (bgImg != null) {
            g2.drawImage(bgImg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
        }

        player.draw(g2);

        g2.dispose();
    }

}
