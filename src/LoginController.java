import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField userNameTextField;



    @FXML
    public void playGameButton(){
        new User(userNameTextField.getText());
        Main.switchToGame();

    }
    @FXML
    public void exitButton(){
        System.exit(0);
    }
}
