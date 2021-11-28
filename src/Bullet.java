import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet {
    private Image bulletImage;
    private ImageView bulletImageView;
    private Settings setting = new Settings();
    private double imageWidth;
    private double imageHeight;
    private boolean collisionFlag = false;

    public Bullet(String url, double X, double Y)
    {
        bulletImage = new Image(url);
        bulletImageView = new ImageView(bulletImage);
        imageWidth = bulletImage.getWidth();
        imageHeight = bulletImage.getHeight();
        bulletImageView.setX(X-imageWidth/2);   // change the start point to the middle bottom point of the bullet
        bulletImageView.setY(Y-imageHeight);
    }


    // methods
    public void updatePosition() {
        bulletImageView.setY(bulletImageView.getY()-setting.getBulletSpeed());
    }

    public void deleteBullet(Group root) {
        root.getChildren().remove(bulletImageView);
    }

    // setters
    public void setCollisionFlag(boolean collisionFlag) {
        this.collisionFlag = collisionFlag;
    }


    // getters

    public ImageView getBulletImageView() {
        return bulletImageView;
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public boolean isCollisionFlag() {
        return collisionFlag;
    }
}
