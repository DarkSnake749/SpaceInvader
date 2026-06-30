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

import entity.Cover;
import entity.Player;
import entity.Waves;

public class GamePanel extends JPanel implements Runnable {
    public final int SCREEN_WIDTH = 800;
    public final int SCREEN_HEIGHT = 600;

    BufferedImage bgImg = null;

    KeyHandler keyH = new KeyHandler();
    Clock clockH = new Clock();
    Thread gameThread;
    Player player = new Player(this, keyH);

    final int SCREEN_COVERS_BUFFER = 180;
    final int START_X_COVERS = SCREEN_COVERS_BUFFER / 2;
    List<Cover> covers = new ArrayList<Cover>();
    
    // startY: 90
    Waves wave = new Waves(
        this, 1, 90, 35, 30, 10, 
        5, 30, 60, 10);

    public void initBg() {
        try {
            bgImg = ImageIO.read(getClass().getResourceAsStream("/images/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initCovers(int numOfCover) {
        final int STEP = SCREEN_WIDTH / numOfCover;
        for (int i = 0; i < numOfCover; i++) {
            covers.add(
                new Cover(this, i * STEP + START_X_COVERS, player.y - 70, 9, 4, 10));
        }
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        initBg();
        initCovers(3);
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
        if (wave.height < covers.get(0).y) {
            for (Cover cover : covers) {
                cover.update(player.bullets, wave.enemyBullets);
            }
        }
        wave.update(player.bullets, covers);
        player.update(covers, wave.enemies);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if (bgImg != null) {
            g2.drawImage(bgImg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
        }

        wave.draw(g2);
        
        if (wave.height < covers.get(0).y) {
            for (Cover cover : covers) {
                cover.draw(g2);
            }
        }
        player.draw(g2);

        g2.dispose();
    }

}
