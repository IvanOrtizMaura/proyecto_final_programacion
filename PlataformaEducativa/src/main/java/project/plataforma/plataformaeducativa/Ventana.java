package project.plataforma.plataformaeducativa;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class Ventana {

    public static void gestionarVentanas(ArrayList<Button> ventanas){
        ventanas.forEach(b -> b.setOnAction(e -> {
            Ventana.cerrarVentanaActual(e);
            Ventana.abrirVentanaUsuario(b.getId());
        }));
    }

    public static void abrirVentanaUsuario(String nombre) {

        try{
            FXMLLoader loader = new FXMLLoader(Ventana.class.getResource(nombre + ".fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Eduform - " + LoginController.alumnoLogueado.nombre + " " +
                    LoginController.alumnoLogueado.apellido1 + " " + LoginController.alumnoLogueado.apellido2);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void abrirVentana(String nombre){
        try{
            FXMLLoader loader = new FXMLLoader(Ventana.class.getResource(nombre + ".fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Eduform");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void cerrarVentanaActual(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage actual = (Stage) source.getScene().getWindow();
        actual.close();
    }

}
