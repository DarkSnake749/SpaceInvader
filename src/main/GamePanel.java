package main;

import java.util.*;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Bullet;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    public final int SCREEN_WIDTH = 1080;
    public final int SCREEN_HEIGHT = 720;

    BufferedImage bgImg = null;

    KeyHandler keyH = new KeyHandler();
    Clock clockH = new Clock();
    Thread gameThread;

    Player player = new Player(this, keyH);
    List<Bullet> bullets = new ArrayList<Bullet>(0);

    // Shooting timing
    final int SHOOTING_STEP = 50;
    int currentShootingStep = 0;
    boolean shot = false;

    public void init_bg() {
        try {
            bgImg = ImageIO.read(getClass().getResourceAsStream("/images/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();

            if (bullet.y + bullet.height < 0) { 
                bullets.remove(i); 
                continue;
            }
            bullets.set(i, bullet);
        }

        player.update();
        if (player.shoot && !shot) {
            bullets.add(
                new Bullet(this, player.x + player.width / 2, player.y));
            shot = true;
            currentShootingStep = 0;
        }

        if (shot) {
            currentShootingStep++;
            shot = (currentShootingStep >= SHOOTING_STEP) ? false : true;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if (bgImg != null) {
            g2.drawImage(bgImg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g2);
        }

        player.draw(g2);

        g2.dispose();
    }

}
