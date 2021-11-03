import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    private Settings setting = new Settings();
    private Group root = new Group();
    private Plane plane = new Plane("images/plane.png", setting.getScreenWidth()/2, setting.getScreenHeight());
    private Bullets bullets = new Bullets();



    @Override
    public void start(Stage primaryStage) {

        root.getChildren().add(plane.getPlaneImageView());
        Scene scene = new Scene(root,setting.getScreenWidth(),setting.getScreenHeight());

        // new thread
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(setting.getFrequency());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> {
                    scene.setOnKeyPressed(this::processKeyPress);
                    scene.setOnKeyReleased(this::processKeyRelease);
                    scene.setOnMouseClicked(this::processMouseClick);
                    plane.updatePosition();
                    bullets.updateBullets(root);
                });
            }
        }).start();

        primaryStage.setTitle("Plane Shooting");
        primaryStage.setScene(scene);
        primaryStage.setWidth(setting.getScreenWidth()+150);    // plus 150 and 130 because the sides of stage and scene are different
        primaryStage.setHeight(setting.getScreenHeight()+130);
        primaryStage.setResizable(false);   // fix edges of stage
        primaryStage.show();



    }

    public void processKeyPress(KeyEvent event) {
        // event when key is pressed
        switch (event.getCode()) {
            case W:
                plane.setFlag_up(true);
                break;

            case S:
                plane.setFlag_Down(true);
                break;

            case A:
                plane.setFlag_Left(true);
                break;

            case D:
                plane.setFlag_Right(true);
                break;

            default:
                break;
        }
    }

    public void processKeyRelease(KeyEvent event) {
        // event when key is released
        switch (event.getCode()) {
            case W:
                plane.setFlag_up(false);
                break;

            case S:
                plane.setFlag_Down(false);
                break;

            case A:
                plane.setFlag_Left(false);
                break;

            case D:
                plane.setFlag_Right(false);
                break;

            default:
                break;
        }
    }

    public void processMouseClick(MouseEvent event) {
        // event when mouse is clicked
        if (bullets.getBulletNum() < 4) {
            bullets.getBulletsArray()[bullets.getBulletIndex()] = new Bullet("images/bullet1.png", plane.getPlaneImageView().getX()+60, plane.getPlaneImageView().getY());
            root.getChildren().add(bullets.getBulletsArray()[bullets.getBulletIndex()].getBulletImageView());
            bullets.setBulletIndex(bullets.getBulletIndex()+1);
            bullets.setBulletIndex(bullets.getBulletIndex() % 4);
            bullets.setBulletNum(bullets.getBulletNum()+1);
        }
    }


}
