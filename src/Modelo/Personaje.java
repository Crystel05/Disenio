package Modelo;

import Modelo.Apariencia.ManagerApariencia;
import Modelo.BuilderPattern.IBuilder;
import Modelo.PrototypePattern.IPrototype;
import sun.awt.PeerEvent;

import java.util.ArrayList;

public class Personaje implements IPrototype<Personaje> {

    private String nombre;
    private ManagerApariencia apariencia;
    private int vida;
    private int cantGolpesRecibos;
    private int nivel;
    private int campos;
    private int nivelApariencia;
    private float costo;

    public Personaje(String nombre, ManagerApariencia apariencia, int vida, int cantGolpesRecibos, int nivel, int campos, int nivelApariencia, float costo) {
        this.nombre = nombre;
        this.apariencia = apariencia;
        this.vida = vida;
        this.cantGolpesRecibos = cantGolpesRecibos;
        this.nivel = nivel;
        this.campos = campos;
        this.nivelApariencia = nivelApariencia;
        this.costo = costo;
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

    public int getCantGolpesRecibos() {
        return cantGolpesRecibos;
    }

    public void setCantGolpesRecibos(int cantGolpesRecibos) {
        this.cantGolpesRecibos = cantGolpesRecibos;
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

    public static class BuilderPersonaje implements IBuilder<Personaje> {
        private String nombre;
        private ManagerApariencia apariencia;
        private int vida;
        private int cantGolpesRecibos;
        private int nivel;
        private int campos;
        private int nivelApariencia;
        private float costo;

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

        public BuilderPersonaje setCantGolpesRecibos(int cantGolpesRecibos) {
            this.cantGolpesRecibos = cantGolpesRecibos;
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

        public void addApariencia(int nivel,String nombre, ArrayList<String> imagenes) {
            this.apariencia.addApariencia(nivel,nombre,imagenes);
        }

        public BuilderPersonaje setCosto(float costo) {
            this.costo = costo;
            return this;
        }

        @Override
        public Personaje build() {
            return new Personaje(nombre, apariencia, vida, cantGolpesRecibos, nivel, campos, nivelApariencia, costo);
        }
    }

    @Override
    public Personaje clone() {
        return new Personaje(nombre, new ManagerApariencia(), vida, cantGolpesRecibos, nivel, campos, nivelApariencia, costo);
    }

    @Override
    public Personaje deepClone() {
        //preguntar como hacer con el ManagerApariencia
        return new Personaje(nombre, new ManagerApariencia(), vida, cantGolpesRecibos, nivel, campos, nivelApariencia, costo);
    }
}
