
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {

    public static Stage primaryStage;
    public static Main mainObject;
    public static MusicThread musicThread;

    public static void main(String[] args) {
        Application.launch();
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.mainObject = this;
        Main.musicThread = new MusicThread();
        musicThread.start();
        loginProcess();
//        primaryStage.show();

    }

    public static void switchToGame() {
        mainObject.gameProcess(primaryStage);
    }

    public static void switchToGameOver(Scene scene) {
        Main.primaryStage.setScene(scene);
//        primaryStage.show();
//        primaryStage.close();
//        Stage newStage = new Stage();
//        primaryStage = newStage;
//        newStage.setScene(scene);
//        newStage.show();
    }

    public void gameProcess(Stage primaryStage) {
        Group group = new Group();
        Game game = new Game(group, User.getLastUser());
        Scene scene = new Scene(group, 600, 600, Color.BLACK);
        game.createGame();
        primaryStage.setScene(scene);
//        primaryStage.show();
//        primaryStage.close();
//        Stage newStage = new Stage();
//        primaryStage = newStage;
//        newStage.setScene(scene);
//        newStage.show();
    }

    public static MediaPlayer music() {
        File file = new File("src/music/music.mp3");
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        return mediaPlayer;
    }

    public static void switchToLoginMenu() {
        try {
            mainObject.loginProcess();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginProcess() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(root, 500, 300, Color.WHITE);
        primaryStage.setTitle("SPACE SHIP");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void switchToGameMenu() {
        try {
            mainObject.gameMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameMenu() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GameMenu.fxml"));
        Scene loginScene = new Scene(root, 500, 300, Color.WHITE);
        primaryStage.setTitle("SPACE SHIP");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void switchToRanking(){
        try {
            mainObject.ranking();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ranking()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Ranking.fxml"));
        Scene loginScene = new Scene(root, 500, 300, Color.WHITE);
        primaryStage.setTitle("SPACE SHIP");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void switchToPersonalInfoMenu() {
        try {
            mainObject.personalInfoMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void personalInfoMenu()throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("PersonalInfo.fxml"));
        Scene loginScene = new Scene(root, 500, 300, Color.WHITE);
        primaryStage.setTitle("SPACE SHIP");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

}
