import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class RankingController implements Initializable {

    @FXML
    private ListView<String> myListView;

    ObservableList<String> userNames;

    public RankingController() {
        userNames = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int counter = 1;
        for (User user : User.sortedUsers()) {
            userNames.add(counter + "." + user.getUserName());
            counter += 1;
        }
    }

    public void back(){
        Main.switchToGameMenu();
    }
}
