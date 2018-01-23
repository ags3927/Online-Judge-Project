import java.io.BufferedReader;
import java.io.FileReader;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Blog {

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
    private Button signOut;

    @FXML
    private TextArea details;

    @FXML
    private Button AddBlog;

    @FXML
    private TextField title;

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
    void addBlog(ActionEvent event) {
        System.out.println(title.getText());
        System.out.println(details.getText());
    }

    @FXML
    void initialize() {
        assert home != null : "fx:id=\"home\" was not injected: check your FXML file 'Blog.fxml'.";
        assert contest != null : "fx:id=\"contest\" was not injected: check your FXML file 'Blog.fxml'.";
        assert problemset != null : "fx:id=\"problemset\" was not injected: check your FXML file 'Blog.fxml'.";
        assert discussion != null : "fx:id=\"discussion\" was not injected: check your FXML file 'Blog.fxml'.";
        assert profile != null : "fx:id=\"profile\" was not injected: check your FXML file 'Blog.fxml'.";
        assert HandleB != null : "fx:id=\"HandleB\" was not injected: check your FXML file 'Blog.fxml'.";
        assert submission != null : "fx:id=\"submission\" was not injected: check your FXML file 'Blog.fxml'.";
        assert blog != null : "fx:id=\"blog\" was not injected: check your FXML file 'Blog.fxml'.";
        assert signOut != null : "fx:id=\"signOut\" was not injected: check your FXML file 'Blog.fxml'.";
        assert details != null : "fx:id=\"details\" was not injected: check your FXML file 'Blog.fxml'.";
        assert AddBlog != null : "fx:id=\"AddBlog\" was not injected: check your FXML file 'Blog.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'Blog.fxml'.";

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
