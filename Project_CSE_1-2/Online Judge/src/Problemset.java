

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Problemset {

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
    private ListView<String> listView;

    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    void handleMouseClick(MouseEvent event) throws IOException {
        Problem.counter = listView.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("Problem.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Contest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Contest.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Discussion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Discussion.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ProblemsetItself(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Problemset.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Profile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void SignOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUpIn.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        assert home != null : "fx:id=\"home\" was not injected: check your FXML file 'Problemset.fxml'.";
        assert contest != null : "fx:id=\"contest\" was not injected: check your FXML file 'Problemset.fxml'.";
        assert problemset != null : "fx:id=\"problemset\" was not injected: check your FXML file 'Problemset.fxml'.";
        assert discussion != null : "fx:id=\"discussion\" was not injected: check your FXML file 'Problemset.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Problemset.fxml'.";
        assert signOut != null : "fx:id=\"signOut\" was not injected: check your FXML file 'Problemset.fxml'.";

        ArrayList<String> problems = new ArrayList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("ProblemName.txt"));
            while (true){
                String data = bufferedReader.readLine();
                if(data == null || data.length()<3)
                    break;

                problems.add(data);
            }
            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        for (int i=0;i<problems.size();i++)
            list.add(problems.get(i));
        listView.setItems(list);

    }
}

