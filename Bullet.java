import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class Bullet {
    private ImageView bulletImageView;
    private Settings setting = new Settings();

    public Bullet(String url, double X, double Y)
    {
        bulletImageView = new ImageView(url);
        bulletImageView.setX(X);
        bulletImageView.setY(Y);
    }


    // methods
    public void updatePosition() {
        bulletImageView.setY(bulletImageView.getY()-setting.getBulletSpeed());
    }

    public void deleteBullet(Group root) {
        root.getChildren().remove(bulletImageView);
    }



    // getters




    // setters


    public ImageView getBulletImageView() {
        return bulletImageView;
    }
}
