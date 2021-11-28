import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private Settings setting = new Settings();
    private Group root = new Group();
    private Plane plane = new Plane("images/plane.png", setting.getScreenWidth()/2, setting.getScreenHeight()*9/10);
    private Text killPoint = new Text(20,30,"Killing points: 0");
    private ImageView background = new ImageView("images/startBg.jpg");
    private StackPane stackpane = new StackPane();
    private AnchorPane root2 = new AnchorPane();


    @Override
    public void start(Stage primaryStage) {

        // start button
        Button startButton = new Button("Start Game");
        startButton.setLayoutX(setting.getScreenWidth()/2-50);
        startButton.setLayoutY(setting.getScreenHeight()/2);
        startButton.setStyle("-fx-font-size: 20; -fx-background-color: Orange;");
        Pane startButtonPane = new Pane(startButton);

        // background
        background.setFitHeight(setting.getScreenHeight());
        background.setFitWidth(setting.getScreenWidth());


        stackpane.getChildren().addAll(background,startButtonPane);
        root2.getChildren().addAll(stackpane);
        Scene sceneStart = new Scene(root2,setting.getScreenWidth(),setting.getScreenHeight());

        startButton.setOnAction(event -> {

            ThreadBackground threadBackground = new ThreadBackground(root); // background thread
            threadBackground.start();

            root.getChildren().addAll(plane.getPlaneImageView(),killPoint);
            Scene scene = new Scene(root,setting.getScreenWidth(),setting.getScreenHeight());

            ThreadBullet threadBullet = new ThreadBullet(plane,root);   // bullet thread
            threadBullet.start();

            ThreadEnemy threadEnemy = new ThreadEnemy(root);    // enemy thread
            threadEnemy.start();

            ThreadMain threadMain = new ThreadMain(plane, scene, root, threadBullet.getBullets(), threadEnemy.getEnemies(), killPoint); // main thread
            threadMain.start();
            primaryStage.setScene(scene);
        });
        primaryStage.setTitle("Plane Shooting");
        primaryStage.setScene(sceneStart);
        primaryStage.setWidth(setting.getScreenWidth()+14.5);    // plus 15 and 38 because the sides of stage and scene are different
        primaryStage.setHeight(setting.getScreenHeight()+37);
        primaryStage.setResizable(false);   // fix edges of stage
        primaryStage.show();


    }

}
