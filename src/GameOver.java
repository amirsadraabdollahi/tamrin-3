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
        User.onlineUser.addPoint(Integer.parseInt(points));
        Label gameLabel = new Label(show);
        Label pointsLabel = new Label("points : " + points);
        Button playAgainButton = new Button("PLAY AGAIN");
        playAgainButton.setPrefSize(150, 50);
        Button gameMenuButton = new Button("GAME MENU");
        gameMenuButton.setPrefSize(150, 50);
        Button exitButton = new Button("EXIT");
        exitButton.setTextFill(Color.RED);
        exitButton.setPrefSize(150, 50);
        gameLabel.setFont(new Font("Arial", 50));
        gameLabel.setTextFill(Color.RED);
        pointsLabel.setFont(new Font("Arial", 20));
        pointsLabel.setTextFill(Color.BLACK);
        VBox vbox = new VBox(gameLabel, pointsLabel, playAgainButton, gameMenuButton, exitButton);
        vbox.setAlignment(Pos.CENTER);
        Scene newScene = new Scene(vbox, 500, 300, Color.BLACK);
        Main.switchToGameOver(newScene);
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exitTheGame();
            }
        });
        gameMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchToGameMenu();
            }
        });
        playAgainButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchToGame();
            }
        });
    }

    public void exitTheGame() {
        System.exit(0);
    }

    public void switchToGame() {
        Main.switchToGame();
    }

    public void switchToGameMenu() {
        Main.switchToGameMenu();
    }
}
