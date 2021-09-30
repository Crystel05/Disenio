package Modelo;

import Modelo.Apariencia.LvlImages;
import Modelo.Apariencia.ManagerApariencia;
import Modelo.BuilderPattern.IBuilder;
import Modelo.PrototypePattern.IPrototype;
import sun.awt.PeerEvent;

import java.awt.*;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Personaje implements IPrototype<Personaje> {

    private String nombre;
    private ManagerApariencia apariencia;
    private int vida;
    private int ataque;
    private int nivel;
    private int campos;
    private int nivelApariencia;
    private float costo;
    private Arma armaActual;
    //Se cambia a un hash para tener que evitar validaciones. Esto hace que no pueda tener dos armas iguales.
    private HashMap<String,Arma> armas;
    boolean alive;
    int posX;
    int posY;


    public Personaje(String nombre, ManagerApariencia apariencia, int vida, int ataque, int nivel, int campos, int nivelApariencia, float costo,HashMap<String,Arma> armas) {
        this.nombre = nombre;
        this.apariencia = apariencia;
        this.vida = vida;
        this.ataque = ataque;
        this.nivel = nivel;
        this.campos = campos;
        this.nivelApariencia = nivelApariencia;
        this.costo = costo;
        this.armas = armas;
        this.alive = true;
        this.posX = 0;
        this.posY = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ManagerApariencia getApariencia() {
        return apariencia;
    }

    public void setApariencia(ManagerApariencia apariencia) {
        this.apariencia = apariencia;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getataque() {
        return ataque;
    }

    public void setataque(int ataque) {
        this.ataque = ataque;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCampos() {
        return campos;
    }

    public void setCampos(int campos) {
        this.campos = campos;
    }

    public int getNivelApariencia() {
        return nivelApariencia;
    }

    public void setNivelApariencia(int nivelApariencia) {
        this.nivelApariencia = nivelApariencia;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public boolean isAlive(){
        return this.alive;
    }

    public void setAlive(boolean alive){
        this.alive = !alive;
    }

    public int[] getPosition(){
        int[] position= new int[2];
        position[0] = this.posX;
        position[1] = this.posY;
        return position;
    }

    public void setPosition(int x,int y){
        this.posX = x;
        this.posY = y;
    }

    public void setPosX(int x){
        this.posX = x;
    }

    public void setPosY(int y){
        this.posY = y;
    }

    //Hash de armas

    public HashMap<String,Arma> getArmas(){return this.armas;}

    public void setArmas(HashMap<String,Arma> armas){this.armas = armas;}

    public void addArma(Arma arma){this.armas.put(arma.getNombre(),arma);}

    //TODO:Tomar en cuenta los nulos
    public Arma getArma(String nombre){
        return armas.get(nombre);
    }

    public Arma getArmaActual(Arma arma){return this.armaActual;}

    public void setArmaActual(Arma arma){this.armaActual = arma;}


    ///Metodos Dummy

    //TODO:Tiene que ser abstracto.
    protected void atacar(ArrayList<Personaje> objetivos,ArrayList<Arma> armas){
        //TODO:CONSIDERAR que el ataque puede ser con varias armas o con ninguna.
    }

    protected void moverse(int x,int y){

    }

    protected void morir(){
        setAlive(false);
        this.vida = 0;
    }


    ///BUILDER
    public static class BuilderPersonaje implements IBuilder<Personaje> {
        private String nombre;
        private ManagerApariencia apariencia;
        private int vida;
        private int ataque;
        private int nivel;
        private int campos;
        private int nivelApariencia;
        private float costo;
        private Arma armaActual;
        private HashMap<String,Arma> armas;

        public BuilderPersonaje setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public BuilderPersonaje setApariencia(ManagerApariencia apariencia) { //revisar
            this.apariencia = apariencia;
            return this;
        }

        public BuilderPersonaje setVida(int vida) {
            this.vida = vida;
            return this;
        }

        public BuilderPersonaje setCantGolpesRecibos(int ataque) {
            this.ataque = ataque;
            return this;
        }

        public BuilderPersonaje setNivel(int nivel) {
            this.nivel = nivel;
            return this;
        }

        public BuilderPersonaje setCampos(int campos) {
            this.campos = campos;
            return this;
        }

        //TODO:Tomar en consideracion cuando no hay imagenes y cuando ya existe la lista.
        //Este es para agregar sobre los LvlImages
        public BuilderPersonaje addApariencia(int nivel,String nombre, ArrayList<String> imagenes) {
            this.apariencia.addApariencia(nivel,nombre,imagenes);
            return this;
        }

        public BuilderPersonaje addApariencia(int nivel, LvlImages imagenes) {
            this.apariencia.addApariencia(nivel,imagenes);
            return this;
        }

        public BuilderPersonaje setCosto(float costo) {
            this.costo = costo;
            return this;
        }

        public BuilderPersonaje addArma(Arma arma){
            this.armas.put(arma.getNombre(),arma);
            return this;
        }

        public BuilderPersonaje setArmas(HashMap<String,Arma> armas){
            this.armas = armas;
            return this;
        }

        @Override
        public Personaje build() {
            return new Personaje(nombre, apariencia, vida, ataque, nivel, campos, nivelApariencia, costo,armas);
        }
    }

    @Override
    public Personaje clone() {
        return new Personaje(nombre, new ManagerApariencia(), vida, ataque, nivel, campos, nivelApariencia, costo,new HashMap<String,Arma>());
    }

    @Override
    public Personaje deepClone() {
        HashMap<String,Arma> nuevasArmas = new HashMap<>();
        for(Map.Entry<String,Arma> entry : this.armas.entrySet()){
            nuevasArmas.put(entry.getKey(),entry.getValue().deepClone());
        }
        return new Personaje(nombre, this.apariencia.deepClone(), vida, ataque, nivel, campos, nivelApariencia, costo,nuevasArmas);
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", apariencia=" + apariencia +
                ", vida=" + vida +
                ", ataque=" + ataque +
                ", nivel=" + nivel +
                ", campos=" + campos +
                ", nivelApariencia=" + nivelApariencia +
                ", costo=" + costo +
                '}';
    }

    public BuilderPersonaje getBuildable(){
        return new BuilderPersonaje();
    }

    //TODO: Metodos Dummy Tienen que poder hacersele override.
    //TODO: Hacer ToString() mas bonito para interfaz.
    //TODO: Que no se me olvide preguntar que hacer cuando uno agrega a una lista vacia o listas con objeto Mas que todo en el caso de ManagerApariencia y armas.
}
