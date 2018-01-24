import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private JFXDatePicker date;

    @FXML
    private JFXTimePicker time;

    private File statements[] = new File[5];
    private File inputs[] = new File[5];
    private File solutions[] = new File[5];

    @FXML
    void ContestItself(ActionEvent event) throws IOException {
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
    void addDone(ActionEvent event) throws IOException {
        int st = 0;
        int in = 0;
        int sln = 0;

        for (int i=0; i<5 ; i++){
            if (statements[i] != null)
                st = st+1;
            if (inputs[i] != null)
                in = in+1;
            if (solutions[i] != null)
                sln = sln+1;
        }

        Parent root = FXMLLoader.load(getClass().getResource("Contest.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void in1(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        inputs[0] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void in2(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        inputs[1] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void in3(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        inputs[2] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void in4(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        inputs[3] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void in5(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        inputs[4] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void s1(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        statements[0] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void s2(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        statements[1] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void s3(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        statements[2] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void s4(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        statements[3] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void s5(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        statements[4] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void sln1(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        solutions[0] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void sln2(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        solutions[1] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void sln3(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        solutions[2] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void sln4(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        solutions[3] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void sln5(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        solutions[4] = fileChooser.showOpenDialog(null);
    }

    @FXML
    void initialize() {
        assert home != null : "fx:id=\"home\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert contest != null : "fx:id=\"contest\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert problemset != null : "fx:id=\"problemset\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert discussion != null : "fx:id=\"discussion\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert signOut != null : "fx:id=\"signOut\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'AddContest.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'AddContest.fxml'.";

    }
}
