import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;


    @FXML
    public void signIn() {
        String signInState = User.correctUserPass(userNameTextField.getText(), passwordTextField.getText());
        userNameTextField.clear();
        passwordTextField.clear();
        if (signInState.equalsIgnoreCase("invalid userName")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("user with this userName doesn't exist");
            alert.show();
        } else if (signInState.equalsIgnoreCase("invalid password")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("wrong password");
            alert.show();
        } else if (signInState.equalsIgnoreCase("correct")) {
            User.putOnlineUser(userNameTextField.getText());
            Main.switchToGameMenu();
        }
    }

    @FXML
    public void signUp() {
        String signUpState = User.newUserPass(userNameTextField.getText());
        userNameTextField.clear();
        passwordTextField.clear();
        if (signUpState.equalsIgnoreCase("invalid userName")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("user with exist with this userName!\nplease choose another");
            alert.show();
        } else if (signUpState.equalsIgnoreCase("correct")) {
            new User(userNameTextField.getText(), passwordTextField.getText());
            User.putOnlineUser(userNameTextField.getText());
            Main.switchToGameMenu();
        }
    }

    @FXML
    public void exitButton() {
        System.exit(0);
    }
}
