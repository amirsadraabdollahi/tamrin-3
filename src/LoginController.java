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
        String state = User.correctUserPass(userNameTextField.getText(), passwordTextField.getText());
        userNameTextField.clear();
        passwordTextField.clear();
        if(state.equalsIgnoreCase("invalid userName")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("invalid userName");
            alert.show();
        } else if(state.equalsIgnoreCase("invalid password")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("invalid password");
            alert.show();
        } else if(state.equalsIgnoreCase("correct")){
            User.putOnlineUser(userNameTextField.getText());
            Main.switchToGameMenu();
        }
    }

    @FXML
    public void signUp() {
        Main.switchToSignUpMenu();
    }

    @FXML
    public void exitButton() {
        System.exit(0);
    }
}
