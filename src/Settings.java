public class Settings {
    // All settings of the game

    // screen
    private int screenWidth = 500;
    private int screenHeight = 700;

    // plane
    private double planeSpeed = 1.5;

    // bullet
    private double bulletSpeed = 1.7;

    // enemy
    private double enemy1Speed = 0.5;
    private double enemy2YSpeed = 0.8;
    private double enemy2XSpeed = 0.5;

    // refresh
    private long frequency = 2;
    private long bulletFrequency = 200;
    private long enemyFrequency = 300;
    private long backgroundFrequency = 15;


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

    public double getEnemy1Speed() {
        return enemy1Speed;
    }

    public double getEnemy2YSpeed() {
        return enemy2YSpeed;
    }

    public double getEnemy2XSpeed() {
        return enemy2XSpeed;
    }

    public long getFrequency() {
        return frequency;
    }

    public long getBulletFrequency() {
        return bulletFrequency;
    }

    public long getEnemyFrequency() {
        return enemyFrequency;
    }

    public long getBackgroundFrequency() {
        return backgroundFrequency;
    }
}
