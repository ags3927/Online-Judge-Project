import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.nio.ch.Net;

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

    @FXML
    private Label st1;

    @FXML
    private Label st2;

    @FXML
    private Label st3;

    @FXML
    private Label st4;

    @FXML
    private Label st5;

    @FXML
    private Label it1;

    @FXML
    private Label it2;

    @FXML
    private Label it3;

    @FXML
    private Label it4;

    @FXML
    private Label it5;

    @FXML
    private Label slt1;

    @FXML
    private Label slt2;

    @FXML
    private Label slt3;

    @FXML
    private Label slt4;

    @FXML
    private Label slt5;

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
        boolean bl = false;

        for (int i=0; i<5 ; i++){
            int x = 0;
            if (statements[i] != null) {
                st = st+1;
                x = x+1;
            }
            if (inputs[i] != null) {
                in = in+1;
                x = x+1;
            }
            if (solutions[i] != null) {
                sln = sln+1;
                x = x+1;
            }

            if (x == 2 || x==1)
                bl = true;

        }

        if (st<3 || in<3 || sln<3){
            new Alert(Alert.AlertType.INFORMATION,"Please Add more Problems!!").show();
            return;
        }
        if (bl){
            new Alert(Alert.AlertType.INFORMATION,"Please Add all Required Files!!").show();
            return;
        }

        ContestProblem cP = new ContestProblem();
        for (int i=0; i<5; i++){
            if (statements[i] != null){
                cP.statements[i] = FileRead(statements[i]);
                cP.inputs[i] = FileRead(inputs[i]);
                cP.solutions[i] = FileRead(solutions[i]);
            }
        }

        NetworkUtil nc = Communication.get();
        nc.write(cP);

        Parent root = FXMLLoader.load(getClass().getResource("Contest.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void in1(ActionEvent event) {
        inputs[0] = null;
        FileChooser fileChooser = new FileChooser();
        inputs[0] = fileChooser.showOpenDialog(null);
        if (inputs[0] != null)
            it1.setText(inputs[0].getName());
    }

    @FXML
    void in2(ActionEvent event) {
        inputs[1] = null;
        FileChooser fileChooser = new FileChooser();
        inputs[1] = fileChooser.showOpenDialog(null);
        if (inputs[1] != null)
            it2.setText(inputs[1].getName());
    }

    @FXML
    void in3(ActionEvent event) {
        inputs[2] = null;
        FileChooser fileChooser = new FileChooser();
        inputs[2] = fileChooser.showOpenDialog(null);
        if (inputs[2] != null)
            it3.setText(inputs[2].getName());
    }

    @FXML
    void in4(ActionEvent event) {
        inputs[3] = null;
        FileChooser fileChooser = new FileChooser();
        inputs[3] = fileChooser.showOpenDialog(null);
        if (inputs[3] != null)
            it4.setText(inputs[3].getName());
    }

    @FXML
    void in5(ActionEvent event) {
        inputs[4] = null;
        FileChooser fileChooser = new FileChooser();
        inputs[4] = fileChooser.showOpenDialog(null);
        if (inputs[4] != null)
            it5.setText(inputs[4].getName());
    }

    @FXML
    void s1(ActionEvent event) {
        statements[0] = null;
        FileChooser fileChooser = new FileChooser();
        statements[0] = fileChooser.showOpenDialog(null);
        if (statements[0] != null)
            st1.setText(statements[0].getName());
    }

    @FXML
    void s2(ActionEvent event) {
        statements[1] = null;
        FileChooser fileChooser = new FileChooser();
        statements[1] = fileChooser.showOpenDialog(null);
        if (statements[1] != null)
            st2.setText(statements[1].getName());
    }

    @FXML
    void s3(ActionEvent event) {
        statements[2] = null;
        FileChooser fileChooser = new FileChooser();
        statements[2] = fileChooser.showOpenDialog(null);
        if (statements[2] != null)
            st3.setText(statements[2].getName());
    }

    @FXML
    void s4(ActionEvent event) {
        statements[3] = null;
        FileChooser fileChooser = new FileChooser();
        statements[3] = fileChooser.showOpenDialog(null);
        if (statements[3] != null)
            st4.setText(statements[3].getName());
    }

    @FXML
    void s5(ActionEvent event) {
        statements[4] = null;
        FileChooser fileChooser = new FileChooser();
        statements[4] = fileChooser.showOpenDialog(null);
        if (statements[4] != null)
            st5.setText(statements[4].getName());
    }

    @FXML
    void sln1(ActionEvent event) {
        solutions[0] = null;
        FileChooser fileChooser = new FileChooser();
        solutions[0] = fileChooser.showOpenDialog(null);
        if (solutions[0] != null)
            slt1.setText(solutions[0].getName());
    }

    @FXML
    void sln2(ActionEvent event) {
        solutions[1] = null;
        FileChooser fileChooser = new FileChooser();
        solutions[1] = fileChooser.showOpenDialog(null);
        if (solutions[1] != null)
            slt2.setText(solutions[1].getName());
    }

    @FXML
    void sln3(ActionEvent event) {
        solutions[2] = null;
        FileChooser fileChooser = new FileChooser();
        solutions[2] = fileChooser.showOpenDialog(null);
        if (solutions[2] != null)
            slt3.setText(solutions[2].getName());
    }

    @FXML
    void sln4(ActionEvent event) {
        solutions[3] = null;
        FileChooser fileChooser = new FileChooser();
        solutions[3] = fileChooser.showOpenDialog(null);
        if (solutions[3] != null)
            slt4.setText(solutions[3].getName());
    }

    @FXML
    void sln5(ActionEvent event) {
        solutions[4] = null;
        FileChooser fileChooser = new FileChooser();
        solutions[4] = fileChooser.showOpenDialog(null);
        if (solutions[4] != null)
            slt5.setText(solutions[4].getName());
    }

    private String FileRead(File file){
        String s = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true){
                String data = br.readLine();
                if (data == null)
                    break;
                s = s+data;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
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
