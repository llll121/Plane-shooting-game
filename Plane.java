import javafx.scene.image.ImageView;

public class Plane {

    private ImageView planeImageView;
    private Settings setting = new Settings();
    private boolean flag_up = false;
    private boolean flag_Down = false;
    private boolean flag_Left = false;
    private boolean flag_Right = false;


    public Plane(String url, double X, double Y)
    {
        planeImageView = new ImageView(url);
        planeImageView.setScaleX(0.5);
        planeImageView.setScaleY(0.5);
        planeImageView.setX(X);
        planeImageView.setY(Y);
    }

    public void updatePosition() {
        // 更新飞机的位置
        if(flag_up && planeImageView.getY()>=0)
        {
            planeImageView.setY(planeImageView.getY()-setting.getPlaneSpeed());
        }

        if(flag_Down && planeImageView.getY()<=setting.getScreenHeight())
        {
            planeImageView.setY(planeImageView.getY()+setting.getPlaneSpeed());
        }

        if(flag_Left && planeImageView.getX()>=0)
        {
            planeImageView.setX(planeImageView.getX()-setting.getPlaneSpeed());
        }

        if(flag_Right && planeImageView.getX()<=setting.getScreenWidth())
        {
            planeImageView.setX(planeImageView.getX()+setting.getPlaneSpeed());
        }
    }





    public void setPlaneImageView(String url) {
        planeImageView = new ImageView(url);
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

    public boolean isFlag_up() {
        return flag_up;
    }

    public boolean isFlag_Down() {
        return flag_Down;
    }

    public boolean isFlag_Left() {
        return flag_Left;
    }

    public boolean isFlag_Right() {
        return flag_Right;
    }

    public ImageView getPlaneImageView() {
        return planeImageView;
    }
}
