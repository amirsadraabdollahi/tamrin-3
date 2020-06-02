import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GameMenuController implements Initializable {

    @FXML
    private Text user;

    public void playGame() {
        Main.switchToGame();
    }

    public void ranking() {
        Main.switchToRanking();
    }

    public void personalInfo() {
        Main.switchToPersonalInfoMenu();
    }

    public void signOut() {
        Main.switchToLoginMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user.setText(User.onlineUser.getUserName());
    }
}
