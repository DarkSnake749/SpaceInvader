package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;;

public class KeyHandler implements KeyListener {
    public boolean left, right, shoot;

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
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            left = value;
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            right = value;
        }
        if (
            keyCode == KeyEvent.VK_W  ||
            keyCode == KeyEvent.VK_UP ||
            keyCode == KeyEvent.VK_SPACE) {
                shoot = value;
        }
    }
}
