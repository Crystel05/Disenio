package Vista.Controladores;

import Controlador.DragWindow;
import Modelo.Arma;
import Vista.ControllerComun;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerPersonaje implements Initializable, DragWindow,ILoadImages {

    //TODO:Cuando se cargue la pantalla hay que llamar al metodo que creaa el builder.
    private final ControllerComun comun = ControllerComun.getInstance();

    @FXML
    private Pane contenedor;

    @FXML
    private Text modificarLabel;

    @FXML
    private Text crearLabel;

    @FXML
    private Button modificarButt;

    @FXML
    private Button agregarBut;

    @FXML
    private ImageView salir2;

    @FXML
    private ImageView salir;

    @FXML
    private TextField vidaTF;

    @FXML
    private TextField nombreTF;

    @FXML
    private TextField ataqueTF;

    @FXML
    private TextField camposTF;

    @FXML
    private TextField costoTF;

    @FXML
    private ImageView imagenNivelIV;

    @FXML
    private TextField nivelAparTF;

    @FXML
    private ComboBox<String> armasPersonaje;

    @FXML
    public void agregarNiveles(ActionEvent event) throws IOException {
        comun.abrirVentana("FXMLS/escogerNiveles.fxml");
        //TODO:Como pasar el tipo de pantalla al controllerNiveles?
    }

    @FXML
    public void cerrar(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
        comun.abrirVentana("FXMLS/principal.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.onDraggedScene(contenedor);
        comun.getControlador().addBuilderPersonaje();
        ObservableList armas = FXCollections.observableArrayList();
        //TODO: get keys de armas
        armasPersonaje.setItems(armas);
        if (comun.isModificado()){
            nombreTF.setDisable(true);
            salir2.setVisible(true);
            salir.setVisible(false);
            crearLabel.setVisible(false);
            modificarLabel.setVisible(true);
            agregarBut.setVisible(false);
            modificarButt.setVisible(true);
        }else{
            nombreTF.setDisable(false);
            salir2.setVisible(false);
            salir.setVisible(true);
            crearLabel.setVisible(true);
            modificarLabel.setVisible(false);
            agregarBut.setVisible(true);
            modificarButt.setVisible(false);
        }
    }

    @FXML
    public void agregarNuevo(ActionEvent event){
        if (!nombreTF.getText().isEmpty()){
            setNombre();
            if (!ataqueTF.getText().isEmpty())
                setAtaque();
            if (!vidaTF.getText().isEmpty())
                setVida();
            if (!camposTF.getText().isEmpty())
                setCampos();
            if (!nivelAparTF.getText().isEmpty())
                setNivel();
            if (!costoTF.getText().isEmpty())
                setCosto();
            comun.getControlador().buildCurrentPersonaje();
        }else{
            System.out.println("Tiene que tener nombre");
        }
    }

    @Override
    public void onDraggedScene(Pane panelFather) {
        DragWindow.super.onDraggedScene(panelFather);
    }

    public void cerrar2(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
    }

    public void setNombre(){ comun.getControlador().setNameBuilderPersonaje(nombreTF.getText());}

    public void setNivel(){ comun.getControlador().setNivelCurrentPersonaje(Integer.parseInt(nivelAparTF.getText()));}

    public void setCampos(){ comun.getControlador().setCamposCurrentPersonaje(Integer.parseInt(camposTF.getText())); }

    public void setAtaque(){ comun.getControlador().setAtaqueCurrentPersonaje(Integer.parseInt(ataqueTF.getText()));}

    public void setVida() { comun.getControlador().setVidaCurrentPersonaje(Integer.parseInt(vidaTF.getText()));}

    public void setCosto(){
        comun.getControlador().setCamposCostoPersonaje(Float.parseFloat(costoTF.getText()));
    }

    //TODO: Cuando viene con una nueva o con una reutilizada
    public void addArma(Arma arma){
        comun.getControlador().agregarArmaCurrentPersonaje(arma);
    }

    @Override
    public void loadImages(String accion, ArrayList<String> images) {
        comun.getControlador().addAparienciaBuilderPersonaje(Integer.parseInt(nivelAparTF.getText()),accion,images);
    }

}
