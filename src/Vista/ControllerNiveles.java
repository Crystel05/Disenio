package Vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class ControllerNiveles {
    private ControllerComun comun = ControllerComun.getInstance();

    @FXML
    public void cerrar(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
    }

    @FXML
    public void seleccionarImagen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stageActual);
        String pathFoto = file.getAbsolutePath();

    }

}
