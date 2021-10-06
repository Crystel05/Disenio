package Modelo.FactoryPattern;

import Modelo.PrototypePattern.IPrototype;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class PrototypeFactory {

    public static HashMap<String, HashMap<String, IPrototype>> prototipos = new HashMap<>();

    public static ArrayList<IPrototype> getItem(String nombrePrototipo, int cant, String tipoHash){
        ArrayList<IPrototype> peticiones = new ArrayList<>();
        for (int i = 0 ; i < cant ; i++){
            peticiones.add(((IPrototype)prototipos.get(tipoHash).get(nombrePrototipo).clone()));
        }
        return peticiones;
    }

    public static void addItem(String nombre, IPrototype item, String tipoHash){
        prototipos.get(tipoHash).put(nombre,item);
    }

    public static ArrayList<IPrototype>getAll(String tipoHash){
        ArrayList<IPrototype> listaItems = new ArrayList<>();

        Set<String> llaves = prototipos.keySet();

        for(String llave : llaves) {
            listaItems.add(((IPrototype)prototipos.get(tipoHash).get(llave).clone()));
        }
        return listaItems;
    }

    public static ArrayList<String> getAllKeys(String tipoHash){
        ArrayList<String> keys = new ArrayList<>();

        Set<String> llaves = prototipos.get(tipoHash).keySet();

        for(String llave : llaves) {
            keys.add(llave);
        }
        return keys;
    }

    public static HashMap<String, HashMap<String, IPrototype>> getHash(){
        return prototipos;
    }

}
