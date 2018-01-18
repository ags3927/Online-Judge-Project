

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.InputMismatchException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Problem {

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
    private Label problemDetails;

    @FXML
    private ChoiceBox<String> chooseLang;

    @FXML
    private Button chooseFile;

    @FXML
    private Button submit;

    @FXML
    private Label filename;
    public String lang;
    public File file;

    @FXML
    void ChooseFile(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);

        if(file!=null){
            filename.setText(file.getName());
        }
        else {
            System.out.println("Nothing Found");
        }
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
    void Submit(ActionEvent event) throws IOException {
        lang = chooseLang.getValue();
        if(file != null && lang != null) {
            Socket socket = new Socket("127.0.0.1", 53333);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject("Submit");
            oos.writeObject(lang);
            oos.writeObject(file);
            socket.close();
        }
    }

    public static String counter;

    @FXML
    void initialize() {
        assert home != null : "fx:id=\"home\" was not injected: check your FXML file 'Problem.fxml'.";
        assert contest != null : "fx:id=\"contest\" was not injected: check your FXML file 'Problem.fxml'.";
        assert problemset != null : "fx:id=\"problemset\" was not injected: check your FXML file 'Problem.fxml'.";
        assert discussion != null : "fx:id=\"discussion\" was not injected: check your FXML file 'Problem.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Problem.fxml'.";
        assert signOut != null : "fx:id=\"signOut\" was not injected: check your FXML file 'Problem.fxml'.";
        assert problemDetails != null : "fx:id=\"problemDetails\" was not injected: check your FXML file 'Problem.fxml'.";
        assert chooseLang != null : "fx:id=\"chooseLang\" was not injected: check your FXML file 'Problem.fxml'.";
        assert chooseFile != null : "fx:id=\"chooseFile\" was not injected: check your FXML file 'Problem.fxml'.";
        assert submit != null : "fx:id=\"submit\" was not injected: check your FXML file 'Problem.fxml'.";

        ObservableList<String>list = FXCollections.observableArrayList("C++","JAVA");
        chooseLang.setItems(list);

        String statement="";
        try {
            BufferedReader bufferedReader =  new BufferedReader(new FileReader("Problems.txt"));
            boolean b = false;
            while (true){
                String data = bufferedReader.readLine();
                if(data.equals("End of The File") && b)
                    break;
                if(b)
                    statement+=data+"\n";
                if(data.equals(counter) && !b){
                    b=true;
                }
            }
            bufferedReader.close();
        } catch (Exception e){
            e.printStackTrace();
        }


        problemDetails.setText(statement);
    }
}

