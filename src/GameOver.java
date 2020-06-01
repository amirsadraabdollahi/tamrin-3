import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOver {

    public GameOver() {
    }

    public void gameIsOVer(String show, String points) {
//        Main.primaryStage.close();
        Label gameLabel = new Label(show);
        Label pointsLabel = new Label("points : " + points);
        Button playAgainButton = new Button("PLAY AGAIN");
        playAgainButton.setPrefSize(150, 50);
        Button exitButton = new Button("EXIT");
        exitButton.setTextFill(Color.RED);
        exitButton.setPrefSize(150, 50);
        gameLabel.setFont(new Font("Arial", 50));
        gameLabel.setTextFill(Color.RED);
        pointsLabel.setFont(new Font("Arial", 20));
        pointsLabel.setTextFill(Color.BLACK);
        VBox vbox = new VBox(gameLabel, pointsLabel, playAgainButton, exitButton);
        vbox.setAlignment(Pos.CENTER);
        Scene newScene = new Scene(vbox, 600, 600, Color.BLACK);
        Main.switchToGameOver(newScene);
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exitTheGame();
            }
        });
        playAgainButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchToGame();
            }
        });
    }
    public void exitTheGame(){
        System.exit(0);
    }

    public void switchToGame(){
        Main.switchToGame();
    }
}
