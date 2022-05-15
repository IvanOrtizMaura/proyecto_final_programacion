/**
 * @author Rodrigo García Calvo & Ivan Ortiz Maura
 * @since 15/05/2022
 */

package project.plataforma.plataformaeducativa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class APanelController {

    @FXML
    //Permite acceder a la ventana "Agregar alumno".
    public void ventanaAdd(){
        Ventana.abrirVentana("Pantalla_Add");
    }

    @FXML
    //Permite acceder a la ventana "Suprimir alumno".
    public void ventanaDel(){
        Ventana.abrirVentana("Pantalla_Del");
    }

    @FXML
    //Permite volver a la ventana de inicio de sesión.
    public void cerrarSesion(ActionEvent e){
        Ventana.cerrarVentanaActual(e);
        Ventana.abrirVentana("Pantalla_ALogin");
    }
}
