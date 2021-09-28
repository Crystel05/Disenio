package Modelo.FactoryPattern;

import Modelo.PrototypePattern.IPrototype;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class PrototypeFactory {

    public static HashMap<String, IPrototype> prototipos;

    public static IPrototype GetItem(String nombrePrototipo){
        return ((IPrototype)prototipos.get(nombrePrototipo).clone());
    }

    public static void addItem(String nombre, IPrototype item){
        prototipos.put(nombre,item);
    }

    public static ArrayList<IPrototype>getAll(){
        ArrayList<IPrototype> listaItems = new ArrayList<IPrototype>();

        Set<String> llaves = prototipos.keySet();

        for(String llave : llaves) {
            listaItems.add(((IPrototype)prototipos.get(llave).clone()));
        }
        return listaItems;
    }

    public static ArrayList<String> getAllKeys(){
        ArrayList<String> keys = new ArrayList<>();

        Set<String> llaves = prototipos.keySet();

        for(String llave : llaves) {
            keys.add(llave);
        }
        return keys;
    }

}
