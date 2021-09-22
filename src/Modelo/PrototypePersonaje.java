package Modelo;

import Modelo.Apariencia.ManagerApariencia;
import Modelo.PrototypePattern.IPrototype;

public class PrototypePersonaje implements IPrototype<PrototypePersonaje> {

    private String nombre;
    private ManagerApariencia apariencia;
    private int vida;
    private int cantGolpesRecibos;
    private int nivel;
    private int campos;
    private int nivelApariencia;
    private float costo;

    public PrototypePersonaje(String nombre, ManagerApariencia apariencia, int vida, int cantGolpesRecibos, int nivel, int campos, int nivelApariencia, float costo) {
        this.nombre = nombre;
        this.apariencia = apariencia;
        this.vida = vida;
        this.cantGolpesRecibos = cantGolpesRecibos;
        this.nivel = nivel;
        this.campos = campos;
        this.nivelApariencia = nivelApariencia;
        this.costo = costo;
    }

    static class BuilderPersonaje{

    }

    @Override
    public PrototypePersonaje clone() {
        return null;
    }

    @Override
    public PrototypePersonaje deepClone() {
        return null;
    }
}
