import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AddContest {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button home;

    @FXML
    private Button contest;

    @FXML
    private Button problemset;

    @FXML
    private Button discussion;

    @FXML
    private Button profile;

    @FXML
    private Button signOut;

    @FXML
    void ContestItself(ActionEvent event) {

    }

    @FXML
    void Discussion(ActionEvent event) {

    }

    @FXML
    void Home(ActionEvent event) {

    }

    @FXML
    void Problemset(ActionEvent event) {

    }

    @FXML
    void Profile(ActionEvent event) {

    }

    @FXML
    void SignOut(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert home != null : "fx:id=\"home\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert contest != null : "fx:id=\"contest\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert problemset != null : "fx:id=\"problemset\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert discussion != null : "fx:id=\"discussion\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert signOut != null : "fx:id=\"signOut\" was not injected: check your FXML file 'AddContest.fxml'.";

    }
}
