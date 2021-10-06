package Vista.Controladores;

import Controlador.DragWindow;
import Modelo.EnumPrototypes;
import Modelo.FactoryPattern.PrototypeFactory;
import Modelo.Personaje;
import Vista.ControllerComun;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonajesCreados implements Initializable, DragWindow {

    private final ControllerComun comun = ControllerComun.getInstance();

    @FXML
    private Text armasL;

    @FXML
    private Text creadosL;

    @FXML
    private Pane contenedor;

    @FXML
    private Pane armas;

    @FXML
    private Pane wait;

    @FXML
    private Pane detalles;

    @FXML
    private ComboBox<String> nombresPersonajes;


    @FXML
    public void verDetalles(ActionEvent event){
        wait.setVisible(false);
        if (!comun.isArmas()) {
            detalles.setVisible(true);
            armas.setVisible(false);
        }else{
            detalles.setVisible(false);
            armas.setVisible(true);
        }
    }

    @FXML
    public void salir(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
        comun.abrirVentana("FXMLS/principal.fxml");
    }

    @FXML
    public void modificar(ActionEvent event) throws IOException {
        if (!comun.isArmas()) {
            comun.setModificado(true);
            comun.abrirVentana("FXMLS/ventanaPersonaje.fxml");
            detalles.setVisible(true);
            wait.setVisible(false);
            armas.setVisible(false);
        }
        else{
            comun.setArmas(true);
            comun.abrirVentana("FXMLS/crearArma.fxml");
            detalles.setVisible(false);
            wait.setVisible(false);
            armas.setVisible(true);
        }
//        try {
//            //llenar datos de detalles
//            String nombre = nombresPersonajes.getSelectionModel().getSelectedItem();
//            comun.abrirVentana("FXMLS/ventanaPersonaje.fxml");
//            detalles.setVisible(true);
//            wait.setVisible(false);
//        }catch (NullPointerException e){
//            System.out.println("Sin selección");
//            detalles.setVisible(true);
//            wait.setVisible(false);
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        detalles.setVisible(false);
        wait.setVisible(true);
        armas.setVisible(false);
        this.onDraggedScene(contenedor);
        if (comun.isArmas()) {
            armasL.setVisible(true);
            creadosL.setVisible(false);
        }else{
            armasL.setVisible(false);
            creadosL.setVisible(true);
        }

        try {
            ObservableList<String> personajes = FXCollections.observableArrayList();
            ArrayList<String> nombres = PrototypeFactory.getAllKeys(EnumPrototypes.PERSONAJES);
            personajes.addAll(nombres);
            nombresPersonajes.setItems(personajes);
        }
        catch (NullPointerException e){
            System.out.println("No hay personajes");
        }

    }
}
