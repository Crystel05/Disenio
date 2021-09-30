import FileManager.ProcesadorSerializable;
import Modelo.Apariencia.ManagerApariencia;
import Modelo.FactoryPattern.PrototypeFactory;
import Modelo.Personaje;
import Modelo.PrototypePattern.IPrototype;
import com.sun.xml.internal.ws.api.ha.HaInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class MainTester {

    public static void main(String [] args){

        ArrayList<String> imagenes1 = new ArrayList<>();
        imagenes1.add("Corriendo.jpg");
        imagenes1.add("guau.jpg");
        //--------------------------------------
        ArrayList<String> imagenes = new ArrayList<>();
        imagenes.add("Muerte_por_colesterol.jpg");
        imagenes.add("Comiendo.jpg");
        //--------------------------------------
        ManagerApariencia managerApariencia = new ManagerApariencia();
        managerApariencia.addApariencia(2,"default",imagenes);
        managerApariencia.addApariencia(2,"movimiento",imagenes1);
        //--------------------------------------
        Personaje p1 = new Personaje.BuilderPersonaje().setNombre("Perro").setApariencia(managerApariencia).setCampos(10).setCantGolpesRecibos(6).setNivel(2000).build();
        //System.out.println(p1.toString());

        PrototypeFactory.addItem("p1",p1);

        //Escritor
        ProcesadorSerializable.fileWriter(PrototypeFactory.getHash());

        //Lector
        HashMap<String, IPrototype> loaded = ProcesadorSerializable.fileReader("prototypesDataBase.ser");
        System.out.println(loaded.get("p1"));
    }
}
