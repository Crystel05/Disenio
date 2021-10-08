package Vista.Controladores;

import Controlador.DragWindow;
import FileManager.ProcesadorSerializable;
import Modelo.Arma;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    private TextArea datosArmas;

    @FXML
    private TextField cantClonesTF;

    @FXML
    private TextField cantidadClonesATF;

    @FXML
    private ImageView imagenPersonaje;

    @FXML
    private ImageView imagenArma;

    @FXML
    public void verDetalles(ActionEvent event) throws FileNotFoundException {
        wait.setVisible(false);
        cargar();
    }

    public void cargar() throws FileNotFoundException {
        if (!comun.isArmas()) {
            ArrayList<IPrototype> personajes;
            if (cantClonesTF.getText().isEmpty())
                personajes = PrototypeFactory.getItem(nombresPersonajes.getSelectionModel().getSelectedItem(), 1, EnumPrototypes.PERSONAJES);
            else{
                int cantClones = Integer.parseInt(cantClonesTF.getText());
                personajes = PrototypeFactory.getItem(nombresPersonajes.getSelectionModel().getSelectedItem(), cantClones, EnumPrototypes.PERSONAJES);
            }
            StringBuilder mostrar = new StringBuilder("PERSONAJE\n");
            Personaje personajeP =(Personaje) personajes.get(0);
            int i = 0;
            for (IPrototype per : personajes){
                if (personajes.get(0) != per)
                    mostrar.append("\n*****************************************************\nCLON "+ i + "\n");
                i++;
                Personaje personaje = (Personaje) per;
                mostrar.append("\tNombre\t\t\t");
                mostrar = mostrar.append(personaje.getNombre());
                mostrar.append("\n\tAtaque\t\t\t");
                mostrar.append(personaje.getataque());
                mostrar.append("\n\tCampos\t\t\t");
                mostrar.append(personaje.getCampos());
                mostrar.append("\n\tNivel aparición\t");
                mostrar.append(personaje.getataque());
                mostrar.append("\n\tVida\t\t\t");
                mostrar.append(personaje.getVida());
                mostrar.append("\n\tCosto\t\t\t");
                mostrar.append(personaje.getataque());
            }

            System.out.println("TEST"+personajeP.getApariencia());
            String path = personajeP.getApariencia().getImagenes().get(1).getDefault().get(0);
            InputStream stream = new FileInputStream(path);
            Image image = new Image(stream);
            imagenPersonaje.setImage(image);

            clones.setText(String.valueOf(mostrar));
            detalles.setVisible(true);
            armas.setVisible(false);
        }else{
            ArrayList<IPrototype> armasA;
            if (cantidadClonesATF.getText().isEmpty())
                armasA = PrototypeFactory.getItem(nombresPersonajes.getSelectionModel().getSelectedItem(), 1, EnumPrototypes.ARMAS);
            else{
                int cantClones = Integer.parseInt(cantidadClonesATF.getText());
                armasA = PrototypeFactory.getItem(nombresPersonajes.getSelectionModel().getSelectedItem(), cantClones, EnumPrototypes.ARMAS);
            }
            System.out.println(armasA.size());
            StringBuilder mostrar = new StringBuilder("ARMA\n");
            Arma armaP = (Arma) armasA.get(0);
            int i = 0;
            for (IPrototype arm : armasA){
                if (armasA.get(0) != arm)
                    mostrar.append("\n*****************************************************\nCLON "+ i + "\n");
                i++;
                Arma arm1 = (Arma) arm;
                mostrar.append("\tNombre\t\t\t\t");
                mostrar = mostrar.append(arm1.getNombre());
                mostrar.append("\n\tDaño\t\t\t\t");
                mostrar.append(arm1.getDano());
                mostrar.append("\n\tAlcance\t\t\t\t");
                mostrar.append(arm1.getAlcance());
                mostrar.append("\n\tRango de explosión\t");
                mostrar.append(arm1.getRangoExplosion());
            }
            datosArmas.setText(String.valueOf(mostrar));
            detalles.setVisible(false);
            armas.setVisible(true);
        }
    }

    @FXML
    public void cargarClones(MouseEvent event) throws FileNotFoundException {
        cargar();
    }

    @FXML
    public void salir(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
        comun.abrirVentana("FXMLS/principal.fxml");
    }

    @FXML
    public void modificar(ActionEvent event) throws IOException {
        if (!nombresPersonajes.getSelectionModel().getSelectedItem().isEmpty())
            comun.setNombreElemento(nombresPersonajes.getSelectionModel().getSelectedItem());

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
