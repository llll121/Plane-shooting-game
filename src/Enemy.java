import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public abstract class Enemy {
    // father class of all kinds of enemy

    protected Settings setting = new Settings();
    protected int award;
    private int explosionCounterPoint;
    private String state = "survive";

    protected Image enemyImage;
    protected ImageView enemyImageview;
    protected double imageWidth;    // width of image of enemy
    protected double imageHeight;

    protected Image explosionImage;
    protected ImageView explosionImageview;
    protected double explosionWidth;
    protected double explosionHeight;




    public Enemy() {}

    public Enemy(String url, double X, double Y, String explosionUrl, int award) {
        enemyImage = new Image(url);
        enemyImageview = new ImageView(enemyImage);
        imageWidth = enemyImage.getWidth();
        imageHeight = enemyImage.getHeight();
        enemyImageview.setX(X-imageWidth/2);
        enemyImageview.setY(Y-imageHeight);

        explosionImage = new Image(explosionUrl);
        explosionImageview = new ImageView(explosionImage);
        explosionWidth = explosionImage.getWidth();
        explosionHeight = explosionImage.getHeight();

        this.award = award;
    }

    // methods
    public abstract void updatePosition();

    // setters
    public void setState(String state) {
        this.state = state;
    }

    public void setExplosionCounterPoint(int explosionCounterPoint) {
        this.explosionCounterPoint = explosionCounterPoint;
    }

    // getters
    public ImageView getEnemyImageview() {
        return enemyImageview;
    }

    public int getAward() {
        return award;
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public ImageView getExplosionImageview() {
        return explosionImageview;
    }

    public String getState() {
        return state;
    }

    public int getExplosionCounterPoint() {
        return explosionCounterPoint;
    }

    public double getExplosionWidth() {
        return explosionWidth;
    }

    public double getExplosionHeight() {
        return explosionHeight;
    }
}
