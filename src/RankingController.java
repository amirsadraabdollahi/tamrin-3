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
        userNames.add("userName                    point");
        for (User user : User.sortedUsers()) {
            String s;
            s = counter + "." + user.getUserName();
            int size = user.getUserName().length() + 2;
            for(int i=0;i<28-size;i++)s=s.concat(" ");
            s = s.concat("" + user.getAveragePoint());
            userNames.add(s);
            counter += 1;
        }
        myListView.setItems(userNames);
    }

    public void back(){
        Main.switchToGameMenu();
    }
}
