package Controlador;

import Modelo.Apariencia.LvlImages;
import Modelo.Arma;
import Modelo.BuilderPattern.IBuilder;
import Modelo.FactoryPattern.PrototypeFactory;
import Modelo.Personaje;
import Modelo.PrototypePattern.IPrototype;

import java.util.ArrayList;

public class CreadorDePersonajes {

    ArrayList<String> currentImages;
    IBuilder currentBuilding;

    //TODO:Agregar metodos de creacion sin builder/Modificaciones/Clonaciones

    ///Modificacion a partir de un personaje

    //TODO:Cambiar nombre
    public void createFromExistente(Personaje personaje){
        //A partir de este ya se puede usar el builder como si fuerea desde cero.
        //Hacer tambien esto para las armas.
        currentBuilding = personaje.getBuildable();
    }


    ////////////////////////////////////////////////////Metodos de BuilderPersonaje////////////////////////////////////////////////////

    //TODO:Implementar en GUI
    public void addBuilderPersonaje() {
        this.currentBuilding = new Personaje.BuilderPersonaje();
    }
    //TODO:Implementar en GUI
    public void setNameBuilderPersonaje(String nombre){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setNombre(nombre);
    }
    //TODO:Implementar en GUI
    public void setNivelCurrentPersonaje(int nivel){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setNivel(nivel);
    }
    //TODO:Implementar en GUI
    public void setCamposCurrentPersonaje(int campos){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setCampos(campos);
    }
    //TODO:Implementar en GUI
    public void setCamposCurrentPersonaje(float costo){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setCosto(costo);
    }
    //TODO:Implementar en GUI
    //TODO: Cambiar en clase
    public void setAtaqueCurrentPersonaje(int golpes){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setCantGolpesRecibos(golpes);
    }
    //TODO:Implementar en GUI
    public void setVidaCurrentPersonaje(int vida){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setVida(vida);
    }
    //TODO:Implementar en GUI
    public void agregarArmaCurrentPersonaje(Arma arma){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.addArma(arma);
    }
    //TODO:Implementar en GUI
    public void setVidaCurrentPersonaje(int nivel,String accion,ArrayList<String> imagenes){
        LvlImages imagenPorAccion = new LvlImages(accion,imagenes);
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.addApariencia(nivel,imagenPorAccion);
    }
    //TODO:Implementar en GUI
    public Personaje buildCurrentPersonaje(){
        Personaje nuevoPersonaje = (Personaje)currentBuilding.build();
        PrototypeFactory.addItem(nuevoPersonaje.getNombre(),nuevoPersonaje);
        return nuevoPersonaje;
    }

    ////////////////////////////////////////////////////Metodos de BuilderArma//////////////////////////////////////////////////


//TODO:Implementar en GUI
    public void addBuilderArma() {
        this.currentBuilding = new Arma.BuilderArma();
    }
    //TODO:Implementar en GUI
    public void setNameBuilderArma(String nombre){
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.setNombre(nombre);
    }
    //TODO:Implementar en GUI
    public void setAlcanceBuilderArma(int alcance){
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.setAlcance(alcance);
    }
    //TODO:Implementar en GUI
    public void setDanoBuilderArma(int dano){
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.setDano(dano);
    }
    //TODO:Implementar en GUI
    public void setRangoBuilderArma(int rangoExplosion){
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.setRangoExplosion(rangoExplosion);
    }
    //TODO:Implementar en GUI
    public void setNivelBuilderArma(int nivel){
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.setNivel(nivel);
    }
    //TODO:Implementar en GUI
    public void setNivelBuilderArma(int nivel,String accion,ArrayList<String> imagenes){
        LvlImages imagenPorAccion = new LvlImages(accion,imagenes);
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.addApariencia(nivel,imagenPorAccion);
    }

    //TODO:Implementar en GUI
    public Arma buildCurrentArma(){
        Arma arma = (Arma)this.currentBuilding;
        PrototypeFactory.addItem(arma.getNombre(),arma);
        return arma;
    }

}
