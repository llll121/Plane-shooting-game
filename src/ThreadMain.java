import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Iterator;


public class ThreadMain extends Thread{
    private Thread t;
    private int point = 0;
    private static boolean quitFlag = false;
    private static int counter = 0;    // it will increase by 1 each loop

    private Settings setting = new Settings();
    private Plane plane;
    private Scene scene;
    private Group root;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private Text killPoint;


    public ThreadMain(Plane plane,Scene scene, Group root, ArrayList<Bullet> bullets, ArrayList<Enemy> enemies, Text killPoint) {
        this.plane = plane;
        this.scene = scene;
        this.root = root;
        this.bullets = bullets;
        this.enemies = enemies;
        this.killPoint = killPoint;
    }


    public void run() {
        while (quitFlag == false) {
            try {
                Thread.sleep(setting.getFrequency());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                counter++;
                scene.setOnKeyPressed(this::processKeyPress);
                scene.setOnKeyReleased(this::processKeyRelease);
                plane.updatePosition();
                killPoint.setText("Killing points: " + point);
                killPoint.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-fill: yellow;");

                // collision detection
                enemies.forEach((enemy) -> {
                    bullets.forEach((bullet) -> {
                        if((bullet.getBulletImageView().getX()-enemy.getEnemyImageview().getX() >= -bullet.getImageWidth() &&
                                bullet.getBulletImageView().getX()-enemy.getEnemyImageview().getX() <= enemy.getImageWidth()) &&
                                (bullet.getBulletImageView().getY()-enemy.getEnemyImageview().getY() >= -bullet.getImageHeight() &&
                                        bullet.getBulletImageView().getY()-enemy.getEnemyImageview().getY() <= enemy.getImageHeight()))
                        {
                            enemy.setState("collision");
                            bullet.setCollisionFlag(true);
                            point = point + enemy.getAward();
                        }
                    });
                    if((plane.getPlaneImageView().getX()-enemy.getEnemyImageview().getX() >= -plane.getImageWidth()*2/3 &&
                            plane.getPlaneImageView().getX()-enemy.getEnemyImageview().getX() <= enemy.getImageWidth()*2/3) &&
                            (plane.getPlaneImageView().getY()-enemy.getEnemyImageview().getY() >= -plane.getImageHeight()*2/3 &&
                                    plane.getPlaneImageView().getY()-enemy.getEnemyImageview().getY() <= enemy.getImageHeight()*2/3))
                    {
                        quitFlag = true;
                        Text endWord = new Text(120, 250,"You get "+point+" points!");
                        endWord.setStyle("-fx-font-size: 30; -fx-font-weight: bold; -fx-fill: yellow;");
                        root.getChildren().add(endWord);
                    }
                });

                // update bullets
                Iterator<Bullet> itBullet = bullets.iterator();
                while(itBullet.hasNext()) {
                    Bullet bullet = itBullet.next();
                    if(bullet.getBulletImageView().getY()<0 || bullet.isCollisionFlag()==true) {
                        itBullet.remove();
                        root.getChildren().remove(bullet.getBulletImageView());
                    } else bullet.updatePosition();
                }

                // update enemies
                Iterator<Enemy> itEnemy = enemies.iterator();
                while(itEnemy.hasNext()) {
                    Enemy enemy = itEnemy.next();
                    if (enemy.getEnemyImageview().getY()>setting.getScreenHeight()) {
                        itEnemy.remove();
                        root.getChildren().remove(enemy.getEnemyImageview());
                    } else if (enemy.getState() == "collision") {
                        root.getChildren().remove(enemy.getEnemyImageview());
                        enemy.setState("explosion");    // change state of the enemy from collision to explosion
                        enemy.setExplosionCounterPoint(counter+15); // the enemy's explosion imageview will disappear after corresponding counters
                        enemy.getExplosionImageview().setX(enemy.enemyImageview.getX()+enemy.getImageWidth()/2-enemy.getExplosionWidth()/2);    // set the center point of the explosion image equaling to the center point of the enemy image
                        enemy.getExplosionImageview().setY(enemy.enemyImageview.getY()+enemy.getImageHeight()/2-enemy.getExplosionHeight()/2);
                        root.getChildren().add(enemy.getExplosionImageview());  // add explosion image of the enemy to the screen
                    } else if(enemy.getState() == "explosion" && counter == enemy.getExplosionCounterPoint()) {
                        root.getChildren().remove(enemy.getExplosionImageview());   // remove the explosion image
                        itEnemy.remove();
                    } else enemy.updatePosition();
                }
            });

        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
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

    public static boolean getQuitFlag() {
        return quitFlag;
    }

    public static int getCounter() {
        return counter;
    }

}
