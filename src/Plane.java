import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plane {

    private Image planeImage;
    private ImageView planeImageView;
    private Settings setting = new Settings();
    private double imageWidth;
    private double imageHeight;
    private boolean flag_up = false;
    private boolean flag_Down = false;
    private boolean flag_Left = false;
    private boolean flag_Right = false;


    public Plane(String url, double X, double Y)
    {
        planeImage = new Image(url);
        planeImageView = new ImageView(planeImage);
        imageWidth = planeImage.getWidth();
        imageHeight = planeImage.getHeight();
        planeImageView.setX(X-imageWidth/2);
        planeImageView.setY(Y-planeImage.getHeight());
    }

    public void updatePosition() {
        // 更新飞机的位置
        if(flag_up && planeImageView.getY()>0)
        {
            planeImageView.setY(planeImageView.getY()-setting.getPlaneSpeed());
        }

        if(flag_Down && planeImageView.getY()+planeImage.getHeight()<setting.getScreenHeight())
        {
            planeImageView.setY(planeImageView.getY()+setting.getPlaneSpeed());
        }

        if(flag_Left && planeImageView.getX()>0)
        {
            planeImageView.setX(planeImageView.getX()-setting.getPlaneSpeed());
        }

        if(flag_Right && planeImageView.getX()+planeImage.getWidth()<setting.getScreenWidth())
        {
            planeImageView.setX(planeImageView.getX()+setting.getPlaneSpeed());
        }
    }





    public void setPlaneImageView(String url) {
        planeImageView = new ImageView(url);
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public void setFlag_up(boolean flag_up) { this.flag_up = flag_up; }

    public void setFlag_Down(boolean flag_Down) {
        this.flag_Down = flag_Down;
    }

    public void setFlag_Left(boolean flag_Left) {
        this.flag_Left = flag_Left;
    }

    public void setFlag_Right(boolean flag_Right) {
        this.flag_Right = flag_Right;
    }

    public ImageView getPlaneImageView() {
        return planeImageView;
    }

    public Image getPlaneImage() { return planeImage;}
}
