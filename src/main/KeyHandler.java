package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;;

public class KeyHandler implements KeyListener {
    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        setKeyValues(keyCode, true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        setKeyValues(keyCode, false);
    }

    private void setKeyValues(int keyCode, boolean value) {
        if (keyCode == KeyEvent.VK_W) {
            up = value;
        }
        if (keyCode == KeyEvent.VK_S) {
            down = value;
        }
        if (keyCode == KeyEvent.VK_A) {
            left = value;
        }
        if (keyCode == KeyEvent.VK_D) {
            right = value;
        }
    }
}
