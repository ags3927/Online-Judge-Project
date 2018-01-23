import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlySetWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Submission {

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
    private Button HandleB;

    @FXML
    private Button submission;

    @FXML
    private Button blog;

    @FXML
    private TableView<HistoryCls> table;

    @FXML
    private TableColumn<HistoryCls, String> problem;

    @FXML
    private TableColumn<HistoryCls, String> lang;

    @FXML
    private TableColumn<HistoryCls, String> verdict;

    @FXML
    private Button signOut;

    @FXML
    void Blog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Blog.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void HandleB(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void Submission(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Submission.fxml"));
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
    void Problemset(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Problemset.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ProfileItself(ActionEvent event) throws IOException {
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
        assert home != null : "fx:id=\"home\" was not injected: check your FXML file 'Submission.fxml'.";
        assert contest != null : "fx:id=\"contest\" was not injected: check your FXML file 'Submission.fxml'.";
        assert problemset != null : "fx:id=\"problemset\" was not injected: check your FXML file 'Submission.fxml'.";
        assert discussion != null : "fx:id=\"discussion\" was not injected: check your FXML file 'Submission.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Submission.fxml'.";
        assert HandleB != null : "fx:id=\"HandleB\" was not injected: check your FXML file 'Submission.fxml'.";
        assert submission != null : "fx:id=\"submission\" was not injected: check your FXML file 'Submission.fxml'.";
        assert blog != null : "fx:id=\"blog\" was not injected: check your FXML file 'Submission.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Submission.fxml'.";
        assert problem != null : "fx:id=\"problem\" was not injected: check your FXML file 'Submission.fxml'.";
        assert lang != null : "fx:id=\"lang\" was not injected: check your FXML file 'Submission.fxml'.";
        assert verdict != null : "fx:id=\"time\" was not injected: check your FXML file 'Submission.fxml'.";
        assert signOut != null : "fx:id=\"signOut\" was not injected: check your FXML file 'Submission.fxml'.";

        try {
            ObservableList<HistoryCls>historyCls = FXCollections.observableArrayList();
            BufferedReader br = new BufferedReader(new FileReader("history.txt"));
            while (true){
                String data = br.readLine();
                if(data == null)
                    break;

                String s[] = data.split("\t\t");
                historyCls.add(new HistoryCls(s[0],s[1],s[2]));
            }
            br.close();

            lang.setCellValueFactory(new PropertyValueFactory<>("Lang"));
            problem.setCellValueFactory(new PropertyValueFactory<>("Problem"));
            verdict.setCellValueFactory(new PropertyValueFactory<>("Verdict"));
            table.setItems(historyCls);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String s[] = br.readLine().split("\t\t");
            br.close();
            HandleB.setText(s[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

