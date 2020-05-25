
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    public static Stage primaryStage;
    public static Main mainObject;

    public static boolean check = false;
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.mainObject = this;
        loginProcess(primaryStage);
        primaryStage.show();

    }

    public static void switchToGame(){
        mainObject.gameProcess(primaryStage);
    }

    public static void switchToGameOver(Scene scene){
        Main.primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void gameProcess(Stage primaryStage) {
        Group group = new Group();
        Game game = new Game(group);
        Scene scene = new Scene(group, 600, 600, Color.BLACK);
        primaryStage.setScene(scene);
        game.createGame();
        primaryStage.show();
    }

    public void loginProcess(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(root,500, 300, Color.WHITE);
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
