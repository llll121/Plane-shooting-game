import javafx.application.Platform;
import javafx.scene.Group;
import java.util.ArrayList;

public class ThreadBullet extends Thread{
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();    // a bullet list

    private Thread t;
    private Settings setting = new Settings();
    private Plane plane;
    private Group root;

    public ThreadBullet(Plane plane, Group root) {
        this.plane = plane;
        this.root = root;
    }

    public void run() {
        while (ThreadMain.getQuitFlag() == false) {
            try {
                Thread.sleep(setting.getBulletFrequency());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                // new a bullet object
                Bullet bullet = new Bullet("images/bullet1.png",
                        plane.getPlaneImageView().getX() + plane.getPlaneImage().getWidth()/2,
                        plane.getPlaneImageView().getY());
                bullets.add(bullet);    // add the object to a list
                root.getChildren().add(bullet.getBulletImageView());    // put the imageview of bullet to the scene
            });
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }
}
