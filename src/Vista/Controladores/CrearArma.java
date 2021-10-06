package Vista.Controladores;

import Controlador.DragWindow;
import Vista.ControllerComun;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrearArma implements Initializable, DragWindow {
    private final ControllerComun comun = ControllerComun.getInstance();

    @FXML
    private Text crearL;

    @FXML
    private ImageView cerrar2;

    @FXML
    private Button modificarB;

    @FXML
    private TextField nivelArmaTF;

    @FXML
    private TextField alcanceArma;

    @FXML
    private ImageView cerrar;

    @FXML
    private AnchorPane arma;

    @FXML
    private Button agregarB;

    @FXML
    private TextField nombreArma;

    @FXML
    private Text modificarL;

    @FXML
    private TextField dannoArma;

    @FXML
    private TextField rangoExplosionArma;



    @FXML
    public void salir(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
        comun.abrirVentana("FXMLS/principal.fxml");
    }

    @FXML
    public void salir2(MouseEvent event) throws IOException {
        comun.cerrar(event, true);
    }

    @FXML
    public void niveles(ActionEvent event) throws IOException {
        if (!nivelArmaTF.getText().isEmpty())
            comun.abrirVentana("FXMLS/escogerNiveles.fxml");
        else
            System.out.println("Escribir el nivel");
    }

    @FXML
    public void crearArma(){
        if (!nombreArma.getText().isEmpty()){
            //set nombre
            if (!alcanceArma.getText().isEmpty())
                //set alcance
                System.out.println();
            if (!dannoArma.getText().isEmpty())
                //set daño
                System.out.println();
            if (!rangoExplosionArma.getText().isEmpty())
                //set rango explosion
                System.out.println();

            comun.getControlador().buildCurrentArma();
        }else {
            System.out.println("Tiene que tener nombre");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.onDraggedScene(arma);
        if (comun.isArmas()){
            crearL.setVisible(false);
            modificarL.setVisible(true);
            agregarB.setVisible(false);
            modificarB.setVisible(true);
            cerrar.setVisible(false);
            cerrar2.setVisible(true);
        }else{
            crearL.setVisible(true);
            modificarL.setVisible(false);
            agregarB.setVisible(true);
            modificarB.setVisible(false);
            cerrar.setVisible(true);
            cerrar2.setVisible(false);
        }
    }

    @Override
    public void onDraggedScene(Pane panelFather) {
        DragWindow.super.onDraggedScene(panelFather);
    }

    public void setName(){
        comun.getControlador().setNameBuilderArma(nombreArma.getText());
    }
    public void setAlcance(){
        comun.getControlador().setAlcanceBuilderArma(Integer.parseInt(alcanceArma.getText()));
    }
    public void setDano(){
        comun.getControlador().setDanoBuilderArma(Integer.parseInt(dannoArma.getText()));
    }
    public void setRango(){
        comun.getControlador().setRangoBuilderArma(Integer.parseInt(rangoExplosionArma.getText()));
    }
    public void addAparienciaArma(String nivel,String nombre){
        comun.getControlador().addAparienciaArma(Integer.parseInt(nivel),nombre);
    }
}
