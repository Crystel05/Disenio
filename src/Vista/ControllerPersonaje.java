package Vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerPersonaje {

    private final ControllerComun comun = ControllerComun.getInstance();

    @FXML
    private TextField vidaTF;

    @FXML
    private TextField nombreTF;

    @FXML
    private TextField dantGolpesTF;

    @FXML
    private TextField camposTF;

    @FXML
    private TextField costoTF;

    @FXML
    private ImageView imagenNivelIV;

    @FXML
    private TextField nivelAparTF;

    @FXML
    public void agregarNiveles(ActionEvent event) throws IOException {
        comun.abrirVentana("FXMLS/escogerNiveles.fxml");
    }

    @FXML
    public void cerrar(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
        comun.abrirVentana("FXMLS/principal.fxml");
    }
}
