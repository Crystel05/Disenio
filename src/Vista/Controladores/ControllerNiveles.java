package Vista.Controladores;

import Controlador.DragWindow;
import Vista.ControllerComun;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllerNiveles implements Initializable, DragWindow {
    private ControllerComun comun = ControllerComun.getInstance();

    @FXML
    private Pane niveles;

    @FXML
    private ImageView foto;

    @FXML
    public void cerrar(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
    }

    @FXML
    public void seleccionarImagen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escoger imagen");
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stageActual);
        try {
            String pathFoto = file.getAbsolutePath();
            InputStream stream = new FileInputStream(pathFoto);
            Image image = new Image(stream);
            foto.setImage(image);
        }catch (NullPointerException e){
            System.out.println("Sin ruta");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.onDraggedScene(niveles);
    }

    @Override
    public void onDraggedScene(Pane panelFather) {
        DragWindow.super.onDraggedScene(panelFather);
    }
}
