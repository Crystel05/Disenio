package Vista;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerComun {

    private static ControllerComun controllerComun;
    public ControllerComun(){}

    public static ControllerComun getInstance(){
        if(controllerComun == null){
            controllerComun = new ControllerComun();
        }
        return controllerComun;
    }

    public void cerrar(MouseEvent event, Boolean atras){
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();
        if (!atras)
            System.exit(0);
    }

    public void abrirVentana(String fxmlName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));;
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
