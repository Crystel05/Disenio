package Vista.Controladores;

import Controlador.DragWindow;
import FileManager.ProcesadorSerializable;
import Modelo.EnumPrototypes;
import Modelo.FactoryPattern.PrototypeFactory;
import Modelo.Personaje;
import Modelo.PrototypePattern.IPrototype;
import Vista.ControllerComun;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private Pane wait;

    @FXML
    private Pane armas;

    @FXML
    private Pane contenedor;

    @FXML
    private ComboBox<String> nombresPersonajes;

    @FXML
    private Text creadosL;

    @FXML
    private Pane detalles;

    @FXML
    private TextArea clones;

    @FXML
    private TextField cantClonesTF;


    @FXML
    public void verDetalles(ActionEvent event){
        wait.setVisible(false);
        cargar();
    }

    public void cargar(){
        if (!comun.isArmas()) {
            ArrayList<IPrototype> personajes;
            if (cantClonesTF.getText().isEmpty())
                personajes = PrototypeFactory.getItem(nombresPersonajes.getSelectionModel().getSelectedItem(), 1, EnumPrototypes.PERSONAJES);
            else{
                int cantClones = Integer.parseInt(cantClonesTF.getText());
                personajes = PrototypeFactory.getItem(nombresPersonajes.getSelectionModel().getSelectedItem(), cantClones, EnumPrototypes.PERSONAJES);
            }
            StringBuilder mostrar = new StringBuilder("PERSONAJE\n");
            for (IPrototype per : personajes){
                if (personajes.get(0) != per)
                    mostrar.append("\n*****************************************************\nCLON\n");
                Personaje personaje = (Personaje) per;
                mostrar.append("\tNombre\t\t\t");
                mostrar = mostrar.append(personaje.getNombre());
                mostrar.append("\n\tAtaque\t\t\t");
                mostrar.append(personaje.getataque());
                mostrar.append("\n\tCampos\t\t\t");
                mostrar.append(personaje.getCampos());
                mostrar.append("\n\tNivel aparición\t\t\t");
                mostrar.append(personaje.getataque());
                mostrar.append("\n\tVida\t\t\t");
                mostrar.append(personaje.getVida());
                mostrar.append("\n\tCosto\t\t\t");
                mostrar.append(personaje.getataque());
            }
            clones.setText(String.valueOf(mostrar));
            detalles.setVisible(true);
            armas.setVisible(false);
        }else{
            detalles.setVisible(false);
            armas.setVisible(true);
        }
    }

    @FXML
    public void cargarClones(MouseEvent event){
        cargar();
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
        if (!comun.getRutaDirectorio().isEmpty()) {
            System.out.println(comun.getRutaDirectorio());
            PrototypeFactory.setPrototipos(ProcesadorSerializable.fileReader(comun.getRutaDirectorio()));
        }
        detalles.setVisible(false);
        wait.setVisible(true);
        armas.setVisible(false);
        this.onDraggedScene(contenedor);
        try {
            ObservableList<String> personajes = FXCollections.observableArrayList();
            ArrayList<String> nombres;
            if (comun.isArmas()) {
                nombres = PrototypeFactory.getAllKeys(EnumPrototypes.ARMAS);
                armasL.setVisible(true);
                creadosL.setVisible(false);
            }else{
                nombres = PrototypeFactory.getAllKeys(EnumPrototypes.PERSONAJES);
                armasL.setVisible(false);
                creadosL.setVisible(true);
            }
            personajes.addAll(nombres);
            nombresPersonajes.setItems(personajes);
        }
        catch (NullPointerException e){
            System.out.println("No hay personajes");
        }

    }
}
