package Modelo.Apariencia;
import java.util.*;

import Modelo.PrototypePattern.IPrototype;

public class ManagerApariencia implements IPrototype<ManagerApariencia> {

    HashMap<Integer,LvlImages>  aparienciasDisponibles;

    public ManagerApariencia(){
        this.aparienciasDisponibles = new HashMap<>();
    }

    public ManagerApariencia(HashMap<Integer,LvlImages> aparienciasDisponibles){
        this.aparienciasDisponibles = aparienciasDisponibles;
    }

    @Override
    public ManagerApariencia clone() {
        HashMap<Integer,LvlImages> copiaApariencias = new HashMap<>();
        for(Map.Entry<Integer,LvlImages> apariencia : aparienciasDisponibles.entrySet()) {
            Integer key = apariencia.getKey();
            LvlImages value = new LvlImages();
            copiaApariencias.put(key, value);
        }
        return new ManagerApariencia(copiaApariencias);
    }

    @Override
    public ManagerApariencia deepClone() {
        HashMap<Integer,LvlImages> copiaApariencias = new HashMap<>();
        for(Map.Entry<Integer,LvlImages> apariencia : aparienciasDisponibles.entrySet()) {
            Integer key = apariencia.getKey();
            LvlImages value = apariencia.getValue().deepClone();
            copiaApariencias.put(key, value);
        }
        return new ManagerApariencia(copiaApariencias);
    }

    //Retorna el LvlImages hashmap que tiene varias listas de Strings por String.
    public LvlImages getApariencias(int nivel){
        return this.aparienciasDisponibles.get(nivel);
    }



    public ArrayList<String> getAparienciaEspecifica(int nivel,String accion){
        return this.aparienciasDisponibles.get(nivel).getAparienciasPorNivel(accion);
    }

    public void addApariencia(int nivel,LvlImages imagenes){
        this.aparienciasDisponibles.put(nivel, imagenes);
    }

    //Agrega un nuevo LvlImages
    public void addApariencia(int nivel,String nombre,ArrayList<String> urls){
        LvlImages images = this.aparienciasDisponibles.get(nivel);
        if(!images.equals(null))
        {images.addApariencia(nombre, urls);}
        else
        {this.aparienciasDisponibles.put(nivel,new LvlImages(nombre,urls));}
    }

    //Set Apariencia
    //Todo:Tener cuidado si se crean clones donde los LvlImages esten vacios Get Defaults().

}
