

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Profile {

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
    private ImageView image;

    @FXML
    private Label Name;

    @FXML
    private Button HandleB;

    @FXML
    private Button submission;

    @FXML
    private Button blog;

    @FXML
    private Label Email;

    @FXML
    private Label tried;

    @FXML
    private Label solved;

    @FXML
    private Button uploadImage;

    private static Image imagey;


    @FXML
    void Blog(ActionEvent event) {

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
    void UploadImage(ActionEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if(file!=null){
            System.out.println(file.getPath());
            Image imagex = new Image(file.toURI().toURL().toString());
            imagey = imagex;
            image.setImage(imagey);
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
        assert contest != null : "fx:id=\"contest\" was not injected: check your FXML file 'Profile.fxml'.";
        assert problemset != null : "fx:id=\"problemset\" was not injected: check your FXML file 'Profile.fxml'.";
        assert discussion != null : "fx:id=\"discussion\" was not injected: check your FXML file 'Profile.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Profile.fxml'.";
        assert signOut != null : "fx:id=\"signOut\" was not injected: check your FXML file 'Profile.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'Profile.fxml'.";
        assert Name != null : "fx:id=\"Name\" was not injected: check your FXML file 'Profile.fxml'.";
        assert HandleB != null : "fx:id=\"HandleB\" was not injected: check your FXML file 'Profile.fxml'.";
        assert submission != null : "fx:id=\"submission\" was not injected: check your FXML file 'Profile.fxml'.";
        assert blog != null : "fx:id=\"blog\" was not injected: check your FXML file 'Profile.fxml'.";
        assert Email != null : "fx:id=\"Email\" was not injected: check your FXML file 'Profile.fxml'.";
        assert uploadImage != null : "fx:id=\"uploadImage\" was not injected: check your FXML file 'Profile.fxml'.";
        assert tried != null : "fx:id=\"tried\" was not injected: check your FXML file 'Profile.fxml'.";
        assert solved != null : "fx:id=\"solved\" was not injected: check your FXML file 'Profile.fxml'.";

        if (imagey != null)
            image.setImage(imagey);

        int tr=0;
        int ac=0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("history.txt"));
            while (true){
                String data = bufferedReader.readLine();
                if (data == null)
                    break;
                String s[] = data.split("\t\t");
                if (s[2].equals("Accepted"))
                    ac++;
                tr++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tried.setText("Total Tried: "+tr);
        solved.setText("Total Solved: "+ac);

        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String s[] = br.readLine().split("\t\t");
            br.close();
            HandleB.setText(s[1]);
            Email.setText(s[2]);
            Name.setText(s[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


