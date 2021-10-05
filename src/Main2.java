import Modelo.Apariencia.ManagerApariencia;
import Modelo.FactoryPattern.PrototypeFactory;
import Modelo.Personaje;

import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) {
        //--------------------------------------
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
        System.out.println(p1.toString());
        Personaje p2 = p1.deepClone();
        Personaje p3 = p1.clone();
        System.out.println(p2.toString());
        System.out.println(p3.toString());
    }
}
