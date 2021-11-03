public class Settings {
    // All settings of the game

    // screen
    private int screenWidth = 500;
    private int screenHeight = 700;

    // plane
    private double planeSpeed = 1.5;

    // bullet
    private double bulletSpeed = 1.5;
    private int bulletMaxNum = 4;

    // refresh
    private long frequency = 2;





    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public double getPlaneSpeed() {
        return planeSpeed;
    }

    public double getBulletSpeed() {
        return bulletSpeed;
    }

    public int getBulletMaxNum() {
        return bulletMaxNum;
    }

    public long getFrequency() {
        return frequency;
    }
}
