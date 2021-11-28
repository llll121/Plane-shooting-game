public class Enemy1 extends Enemy{

    public Enemy1(double X, double Y) {
        super("images/enemy1.png",X,Y, "images/explosion1.png",5);
    }

    @Override
    public void updatePosition() {
        enemyImageview.setY(enemyImageview.getY()+setting.getEnemy1Speed());
    }
}
