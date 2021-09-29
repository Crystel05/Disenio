package Vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerPrincipal {

    private final ControllerComun comun = ControllerComun.getInstance();

    @FXML
    private RadioButton armaRB;

    @FXML
    private RadioButton personajeRB;

    @FXML
    public void armaSelected(ActionEvent event){
        personajeRB.setSelected(false);
        armaRB.setSelected(true);
    }

    @FXML
    public void personajeSelected(ActionEvent event){
        personajeRB.setSelected(true);
        armaRB.setSelected(false);
    }

    @FXML
    public void crear(ActionEvent event) throws IOException {
        if (armaRB.isSelected() || personajeRB.isSelected()) {
            Node source = (Node) event.getSource();
            Stage stageActual = (Stage) source.getScene().getWindow();
            stageActual.close();
            if (armaRB.isSelected())
                comun.abrirVentana("");

            else
                comun.abrirVentana("FXMLS/ventanaPersonaje.fxml");

        }
    }

    @FXML
    public void cerrar(MouseEvent event){
        comun.cerrar(event, false);
    }

}
