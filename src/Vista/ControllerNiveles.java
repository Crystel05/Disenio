package Vista;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ControllerNiveles {
    private ControllerComun comun = ControllerComun.getInstance();

    @FXML
    public void cerrar(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
        comun.abrirVentana("FXMLS/ventanaPersonaje.fxml");
    }

}
