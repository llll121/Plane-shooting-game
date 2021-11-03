import javafx.scene.Group;

public class Bullets {
    private Settings setting = new Settings();
    private Bullet[] bulletsArray = new Bullet[setting.getBulletMaxNum()];
    private int bulletNum = 0;
    private int bulletIndex = 0;




    // methods
    public void updateBullets(Group root) {
        // including two functions: 1.move bullets  2.delete bullets
        for (int i = 0; i < bulletsArray.length; i++) {
            if (bulletsArray[i] != null) {
                bulletsArray[i].updatePosition();   // move bullets
                if (bulletsArray[i].getBulletImageView().getY() < 100) {
                    bulletsArray[i].deleteBullet(root); // delete bullets
                    bulletsArray[i] = null;
                    bulletNum--;
                }
            }
        }
    }



    // setters
    public void setBulletNum(int bulletNum) {
        this.bulletNum = bulletNum;
    }

    public void setBulletIndex(int bulletIndex) {
        this.bulletIndex = bulletIndex;
    }

    // getters
    public int getBulletNum() {
        return bulletNum;
    }

    public Bullet[] getBulletsArray() {
        return bulletsArray;
    }

    public int getBulletIndex() {
        return bulletIndex;
    }
}
