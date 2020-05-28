import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField userNameTextField;



    @FXML
    public void playGameButton(){
        if(User.hasUserWithThisUserName(userNameTextField.getText())){
            userNameTextField.clear();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("user exist with this userName!! \nplease enter another userName!!");
            alert.show();
            return;
        }
        new User(userNameTextField.getText());
        Main.switchToGame();

    }
    @FXML
    public void exitButton(){
        System.exit(0);
    }
}
