
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.nio.file.Paths;


public class Main extends Application {

    public static Stage primaryStage;
    public static Main mainObject;
    public static MusicThread musicThread;

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        User.users.add(new User("sadra"));
        Main.primaryStage = primaryStage;
        Main.mainObject = this;
        Main.musicThread = new MusicThread();
        musicThread.start();
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

    public static MediaPlayer music(){
        String path = "src/music.mp3";
        Media media = new Media(Paths.get(path).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }

    public void loginProcess(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(root,500, 300, Color.WHITE);
        primaryStage.setTitle("SPACE SHIP");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}
