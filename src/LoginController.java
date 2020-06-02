import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordField;


    @FXML
    public void signIn() {
        if (checkBox()) return;
        String signInState = User.correctUserPass(userNameTextField.getText(), passwordField.getText());
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
        userNameTextField.clear();
        passwordField.clear();
    }

    @FXML
    public void signUp() {
        if (checkBox()) return;
        String signUpState = User.newUserPass(userNameTextField.getText());
        if (signUpState.equalsIgnoreCase("invalid userName")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("user exist with this userName!\nplease choose another");
            alert.show();
        } else if (signUpState.equalsIgnoreCase("correct")) {
            new User(userNameTextField.getText(), passwordField.getText());
            User.putOnlineUser(userNameTextField.getText());
            Main.switchToGameMenu();
        }
        userNameTextField.clear();
        passwordField.clear();
    }

    private boolean checkBox() {
        if (userNameTextField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("please enter a userName");
            alert.show();
            return true;
        }
        if (passwordField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("user enter a password");
            alert.show();
            return true;
        }
        return false;
    }

    @FXML
    public void exitButton() {
        System.exit(0);
    }
}
