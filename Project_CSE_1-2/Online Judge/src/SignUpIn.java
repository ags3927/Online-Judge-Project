
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpIn {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginName;

    @FXML
    private PasswordField password;

    @FXML
    private Button signIn;

    @FXML
    private TextField inName;

    @FXML
    private TextField inHandle;

    @FXML
    private TextField inEmail;

    @FXML
    private PasswordField inPassword;

    @FXML
    private Button signUp;

    @FXML
    void SignIn(ActionEvent event) throws IOException, ClassNotFoundException {
        String loginNameText = loginName.getText();
        String pass = password.getText();
        boolean b = false;
        NetworkUtil temp = Communication.get();
        LogInData logInData = new LogInData(loginNameText,pass);
        temp.write(logInData);
        String info;
        do{
            info = (String) temp.read();
        }while(info==null);

        if (!info.equals("Found")){
            b = true;
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data.txt"));
                bufferedWriter.write(info);
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(b){
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else
            System.out.println("Wrong Input");

        loginName.setText(null);
        password.setText(null);
        inHandle.setText(null);
        inEmail.setText(null);
        inName.setText(null);
        inPassword.setText(null);
    }

    @FXML
    void SignUp(ActionEvent event) throws IOException, ClassNotFoundException {
        NetworkUtil temp = Communication.get();
        String email,name,handle,password;
        email = inEmail.getText();
        name = inName.getText();
        handle = inHandle.getText();
        password = inPassword.getText();
        Object b = null;

        if(password.length()<8){
            System.out.println("Please Enter a Secure Password.");
            return;
        }

        SignUpData signUpData = new SignUpData(name,handle,email,password);
        temp.write(signUpData);
        b = temp.read();

        if(b != null){
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data.txt"));
                bufferedWriter.write(name+"\t\t"+handle+"\t\t"+email+"\t\t"+password+"\n");
                bufferedWriter.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else
            System.out.println("Your Email or Handle is used before!! Please Provide a new Email or Handle or Log In");

        loginName.setText(null);
        this.password.setText(null);
        inHandle.setText(null);
        inEmail.setText(null);
        inName.setText(null);
        inPassword.setText(null);

        if (b != null){
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void initialize() {
        assert loginName != null : "fx:id=\"loginName\" was not injected: check your FXML file 'sample.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'sample.fxml'.";
        assert signIn != null : "fx:id=\"signIn\" was not injected: check your FXML file 'sample.fxml'.";
        assert inName != null : "fx:id=\"inName\" was not injected: check your FXML file 'sample.fxml'.";
        assert inHandle != null : "fx:id=\"inHandle\" was not injected: check your FXML file 'sample.fxml'.";
        assert inEmail != null : "fx:id=\"inEmail\" was not injected: check your FXML file 'sample.fxml'.";
        assert inPassword != null : "fx:id=\"inPassword\" was not injected: check your FXML file 'sample.fxml'.";
        assert signUp != null : "fx:id=\"signUp\" was not injected: check your FXML file 'sample.fxml'.";

    }
}


