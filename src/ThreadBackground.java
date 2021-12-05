// Yinghao, X (2020) PlaneShoot. https://github.com/xuyinghao/PlaneShoot
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ThreadBackground extends Thread{
    private Thread t;
    private Settings setting = new Settings();
    private Canvas canvas = new Canvas(setting.getScreenWidth(),setting.getScreenHeight());
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Image background = new Image("images/bg2.jpg");
    int y1 = 0;
    int y2 = (int) -background.getHeight();

    private Group root;

    public ThreadBackground(Group root) {
        this.root = root;
        this.root.getChildren().add(canvas);
    }

    public void run() {
        while (ThreadMain.getQuitFlag() == false) {
            try {
                Thread.sleep(setting.getBackgroundFrequency());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                paintBackground();
                backgroundMove();
            });
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    private void backgroundMove() {
        y1++;
        y2++;
        if (y1 == 0) {
            y2 = (int) -background.getHeight();
        }
        if (y2 == 0) {
            y1 = (int) -background.getHeight();
        }
    }

    private void paintBackground() {
        gc.drawImage(background, 0, y1);
        gc.drawImage(background, 0, y2);
    }
}
