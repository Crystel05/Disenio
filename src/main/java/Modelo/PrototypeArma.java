package Modelo;

import Modelo.Apariencia.LvlImages;
import Modelo.Apariencia.ManagerApariencia;
import Modelo.PrototypePattern.IPrototype;

import java.util.*;

public class PrototypeArma implements IPrototype<PrototypeArma> {

    //Clase defaultnames puede ser configuracion para asignar los elementos por default
    String nombre;
    int alcance;
    int dano;
    int nivel;
    int rangoExplosion;
    ManagerApariencia apariencia;

    public PrototypeArma(String nombre,int alcance,int dano,int nivel,int rangoExplosion,ManagerApariencia apariencia){
        this.alcance = alcance;
        this.nombre = nombre;
        this.dano = dano;
        this.nivel = nivel;
        this.rangoExplosion = rangoExplosion;
        this.apariencia = apariencia;
    }

    static class BuilderArma{
        String nombre;
        int alcance;
        int dano;
        int nivel;
        int rangoExplosion;
        ManagerApariencia apariencia;

        public void setNombre(String nombre){
            this.nombre = nombre;
        }
        public void setAlcance(int alcance) {
            this.alcance = alcance;
        }
        public void setDano(int dano) {
            this.dano = dano;
        }
        public void setNivel(int nivel) {
            this.nivel = nivel;
        }
        public void setRangoExplosion(int rangoExplosion) {
            this.rangoExplosion = rangoExplosion;
        }
        public void setApariencia(ManagerApariencia apariencia) {
            this.apariencia = apariencia;
        }
        public void addApariencia(int nivel,LvlImages images) {
            this.apariencia.addApariencia(nivel,images);
        }
        //Aca se ponen las imagenes como strings porque van a ser los URL
        public void addApariencia(int nivel,String nombre, ArrayList<String> imagenes) {
            this.apariencia.addApariencia(nivel,nombre,imagenes);
        }
    }

    public String getNombre() {
        return nombre;
    }
    public int getAlcance() {
        return alcance;
    }
    public int getDano() {
        return dano;
    }
    public int getNivel() {
        return nivel;
    }
    public int getRangoExplosion() {
        return rangoExplosion;
    }
    public ManagerApariencia getApariencia() {
        return apariencia;
    }

    private void setApariencia(ManagerApariencia apariencia){
        this.apariencia = apariencia;
    }

    //Todo: pensar en como se va a manejar el default de ManagerApariencia.
    @Override
    public PrototypeArma clone(){
        return new PrototypeArma(this.nombre,this.alcance,this.dano,this.nivel,this.rangoExplosion,new ManagerApariencia());
    }

    //Creo que la apariencia deberia poder ser modificable. Lo mas facil es modificar una lista String. No imagenes individuales.
    @Override
    public PrototypeArma deepClone(){
        //Va a copiar las apariencias disponibles
        ManagerApariencia copiaApariencia = this.apariencia.deepClone();
        //Usa el clone normal
        PrototypeArma copiaArma = clone();
        //Setea de manera privada las nuevas apariencias.
        copiaArma.setApariencia(copiaApariencia);
        return copiaArma;
    }

}