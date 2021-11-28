public class Enemy2 extends Enemy{
    private boolean xIncreaseFlag;

    public Enemy2(double X, double Y) {
        super("images/enemy2.png",X,Y, "images/explosion2.png",10);
    }

    @Override
    public void updatePosition() {
        enemyImageview.setY(enemyImageview.getY()+setting.getEnemy2YSpeed());
        if (ThreadMain.getCounter()%350 == 1) { // enemy2 may change moving direction after each 350 time counters, which means it will move a certain length in one direction
            if (Math.random() >= 0.5) { // there is 50% possibility that the plane will change its direction
                xIncreaseFlag = true;
            } else {
                xIncreaseFlag = false;
            }
        }

        if (enemyImageview.getX()<=0) { // make sure that plane will not exceed the edge
            xIncreaseFlag = true;
        } else if (enemyImageview.getX()+imageWidth>=setting.getScreenWidth()){
            xIncreaseFlag = false;
        }

        if (xIncreaseFlag == true) {
            enemyImageview.setX(enemyImageview.getX()+setting.getEnemy2XSpeed());
        } else {
            enemyImageview.setX(enemyImageview.getX()-setting.getEnemy2XSpeed());
        }
    }

}