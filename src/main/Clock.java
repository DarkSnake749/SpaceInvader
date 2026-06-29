package main;

public class Clock {
    final double FPS = 60.;
    final double DRAW_INTERVAL = 1_000_000_000 / FPS;

    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    public boolean clockHandler() {
        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / DRAW_INTERVAL;
        lastTime = currentTime;

        if (delta >= 1) {
            delta--;
            return true;
        }
        return false;
    }
}
