package Modelo;

import Modelo.PrototypePattern.IPrototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeaponManager implements IPrototype<WeaponManager> {

    private HashMap<String, Arma> armas;
    private Arma armaActual;
    //Hash de armas

    WeaponManager(){
        this.armas = new HashMap<>();
    }

    WeaponManager(WeaponManager armas){
        this.armas = armas.deepClone().getArmas();
    }

    WeaponManager(HashMap<String,Arma> armas){
        this.armas = armas;
    }


    public HashMap<String,Arma> getArmas(){return this.armas;}

    public void setArmas(HashMap<String,Arma> armas){this.armas = armas;}

    public void addArma(Arma arma){this.armas.put(arma.getNombre(),arma);}

    public void addArmas(HashMap<String,Arma> nuevasArmas){
        for(Map.Entry<String,Arma> entry:nuevasArmas.entrySet()){
            armas.put(entry.getKey(),entry.getValue().deepClone());
        }
    }

    public void addArmas(WeaponManager manager){
        addArmas(manager.getArmas());
    }

    public Arma getArma(String nombre){
        return armas.get(nombre);
    }

    public Arma getArmaActual(Arma arma){return this.armaActual;}

    public void setArmaActual(Arma arma){this.armaActual = arma;}

    //TODO:Probar en interfaz para ver las diferentes armas del personaje
    public void nextArma(){
        if(!armas.isEmpty()) {
            ArrayList<Arma> listaArmas = new ArrayList<>(armas.values());
            armaActual = listaArmas.get(nextIndex(listaArmas.indexOf(armaActual)));
        }
    }

    public void previousArma(){
        if(!armas.isEmpty()) {
            ArrayList<Arma> listaArmas = new ArrayList<>(armas.values());
            armaActual = listaArmas.get(prevIndex(listaArmas.indexOf(armaActual)));
        }
    }

    private int nextIndex(int indexOf)
    {
        return indexOf == armas.values().size()-1?indexOf++:0;
    }

    private int prevIndex(int indexOf)
    {
        return indexOf == 0?indexOf--:armas.values().size()-1;
    }

    //La diferencia esta en que este no hace un deepClone a las armas.
    @Override
    public WeaponManager clone() {
        HashMap<String ,Arma> copiaArmas = new HashMap<>();
        for(Map.Entry<String,Arma> entry:armas.entrySet()){
            armas.put(entry.getKey(),entry.getValue().clone());
        }
        return new WeaponManager(copiaArmas);
    }

    @Override
    public WeaponManager deepClone() {
        HashMap<String ,Arma> copiaArmas = new HashMap<>();
        for(Map.Entry<String,Arma> entry:armas.entrySet()){
            armas.put(entry.getKey(),entry.getValue().deepClone());
        }

        return new WeaponManager(copiaArmas);
    }
    
    public Strin
}
