
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
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args){
        try {
            NetworkUtil nc = new NetworkUtil(new Socket("127.0.0.1", 53333));
            new Communication(nc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        launch(args);
    }
}
