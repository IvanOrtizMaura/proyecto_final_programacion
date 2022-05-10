package project.plataforma.plataformaeducativa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App extends Application {

    public static Connection dbCon;
    public static String BDD = "edu";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Pantalla_Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Eduform");
        stage.setScene(scene);
        BDDConnection();
        stage.show();
    }

    public void BDDConnection(){
        try{
           dbCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}