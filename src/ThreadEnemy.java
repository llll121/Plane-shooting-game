import javafx.application.Platform;
import javafx.scene.Group;

import java.util.ArrayList;

public class ThreadEnemy extends Thread{
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    private Thread t;
    private Settings setting = new Settings();
    private Group root;

    public ThreadEnemy(Group root) {
        this.root = root;
    }

    public void run() {
        while (ThreadMain.getQuitFlag() == false) {
            try {
                Thread.sleep(setting.getEnemyFrequency());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // same as ThreadBullet.java
            Platform.runLater(() -> {
                if (Math.random() <= 0.8) { // enemy1 will show up if the random number is between 0~0.8
                    Enemy1 enemy1 = new Enemy1(setting.getScreenWidth() * Math.random(), 0);
                    enemies.add(enemy1);
                    root.getChildren().add(enemy1.getEnemyImageview());
                }
                else {
                    Enemy2 enemy2 = new Enemy2(setting.getScreenWidth() * Math.random(), 0);
                    enemies.add(enemy2);
                    root.getChildren().add(enemy2.getEnemyImageview());
                }
            });
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
