package project.plataforma.plataformaeducativa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class APanelController {

    @FXML
    public void ventanaAdd(){
        Ventana.abrirVentana("Pantalla_Add");
    }

    @FXML
    public void ventanaDel(){
        Ventana.abrirVentana("Pantalla_Del");
    }

    @FXML
    public void cerrarSesion(ActionEvent e){
        Ventana.cerrarVentanaActual(e);
        Ventana.abrirVentana("Pantalla_ALogin");
    }
}
