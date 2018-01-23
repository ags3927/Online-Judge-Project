
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("SignUpIn.fxml"));
        primaryStage.setTitle("Online Judge");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }


    public static void main(String[] args){
        NetworkUtil nc = null;
        try {
            String s = "192.168.0.115";
            nc = new NetworkUtil(new Socket(s, 53333));
            new Communication(nc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
        nc.write("BreakUp");
        nc.closeConnection();
    }
}
