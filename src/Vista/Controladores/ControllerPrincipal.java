package Vista.Controladores;

import Controlador.DragWindow;
import FileManager.ProcesadorSerializable;
import Vista.ControllerComun;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPrincipal implements Initializable, DragWindow {

    private final ControllerComun comun = ControllerComun.getInstance();

    @FXML
    private Pane principal;

    @FXML
    private RadioButton armaRB;

    @FXML
    private RadioButton personajeRB;

    @FXML
    private TextField rutaDirectorio;

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
        if (!rutaDirectorio.getText().isEmpty() && !comun.getRutaDirectorio().isEmpty()) {
            comun.setRutaDirectorio(rutaDirectorio.getText());
        }
        if (!comun.getRutaDirectorio().isEmpty()){
            if (armaRB.isSelected() || personajeRB.isSelected()) {
                Node source = (Node) event.getSource();
                Stage stageActual = (Stage) source.getScene().getWindow();
                stageActual.close();
                if (armaRB.isSelected())
                    comun.abrirVentana("FXMLS/crearArma.fxml");
                else {
                    comun.setModificado(false);
                    comun.abrirVentana("FXMLS/ventanaPersonaje.fxml");
                }
            }
        }

//        if (!comun.getRutaDirectorio().isEmpty()) {
//            if (!rutaDirectorio.getText().isEmpty()) {
//                comun.setRutaDirectorio(rutaDirectorio.getText());

//            }
//        }
    }

    @FXML
    public void verPersonajes(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
        comun.setArmas(false);
        comun.abrirVentana("FXMLS/PersonajesCreados.fxml");
    }

    @FXML
    public void verArmas(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
        comun.setArmas(true);
        comun.abrirVentana("FXMLS/PersonajesCreados.fxml");
    }

    @FXML
    public void cargarRuta(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escoger ruta");
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stageActual);
        comun.setRutaDirectorio(file.getAbsolutePath());
    }

    @FXML
    public void cerrar(MouseEvent event){
        comun.cerrar(event, false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.onDraggedScene(principal);
    }

    @Override
    public void onDraggedScene(Pane panelFather) {
        DragWindow.super.onDraggedScene(panelFather);
    }
}
