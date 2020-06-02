import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalInfoController implements Initializable {

    @FXML
    private Text userName;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;

    public void changePassword() {
        if (User.onlineUser.correctPassword(oldPassword.getText())) {
            User.onlineUser.changePassword(newPassword.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("successfully changed");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("wrong password!");
            alert.show();
        }
    }

    public void back() {
        Main.switchToGameMenu();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userName.setText(User.onlineUser.getUserName());
    }


}
