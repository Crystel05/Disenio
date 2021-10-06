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

    public CreadorDePersonajes(){
        currentImages = new ArrayList<>();
    }

    ////////////////////////////////////////////////////Metodos de clonacion////////////////////////////////////////////////////


//    public ArrayList<Personaje> getClonesPersonajes(int clones,String nombre){
//        ArrayList<Personaje> listaDeClones = new ArrayList<>();
//        for(IPrototype prototype: PrototypeFactory.get(clones,nombre)){
//            listaDeClones.add((Personaje) prototype);
//        }
//        return listaDeClones;
//    }
//
//    public ArrayList<Arma> getClonesArmas(int clones,String nombre){
//        ArrayList<Arma> listaDeClones = new ArrayList<>();
//        for(IPrototype prototype: PrototypeFactory.get(clones,nombre)){
//            listaDeClones.add((Arma) prototype);
//        }
//        return listaDeClones;
//    }

    //TODO:Validacion tonta para usar el resto de metodos
    //Usar en interfaz para controlar que entra al Prototype. Que pasa con un build de nombre vacio?
    //Obligar al usuario a meter algunos datos como nombre al menos?
    public boolean isBuilding(){
        return currentBuilding!= null;
    }

    ////////////////////////////////////////////////////Metodos para reusar personajes////////////////////////////////////////////////////

    //El metodo se crea porque el contralador solo maneje una referencia a builder y asi no haya que agregar nada mas y todo funcione como una sola cosa.
    //Estos metodos se pueden usar tanto para modificar como para la creacion de un nuevo personaje a base de otro
    //Aprovechando el builder para modificar lo que ya esxiste pero con una nueva referencia.
    //Cuando se guarda en el hash, el id se encarga de sobreescribir o de guardar un nuevo personaje
    public void createFromPersonajeExistente(Personaje personaje){
        //A partir de este ya se puede usar el builder como si fuerea desde cero.
        currentBuilding = personaje.getBuildable();
    }

    public void createFromArmaExistente(Arma arma){
        currentBuilding = arma.getBuildable();
    }


    ////////////////////////////////////////////////////Metodos de BuilderPersonaje////////////////////////////////////////////////////

    //TODO:Implementar en GUI
    public void addBuilderPersonaje() {
        this.currentBuilding = new Personaje.BuilderPersonaje();
    }

    public void setNameBuilderPersonaje(String nombre){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setNombre(nombre);
    }

    public void setNivelCurrentPersonaje(int nivel){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setNivel(nivel);
    }

    public void setCamposCurrentPersonaje(int campos){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setCampos(campos);
    }
    //TODO:Implementar en GUI
    public void setCamposCurrentPersonaje(float costo){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setCosto(costo);
    }

    //TODO: Cambiar en clase
    public void setAtaqueCurrentPersonaje(int golpes){
        Personaje.BuilderPersonaje nowBuilding = (Personaje.BuilderPersonaje) currentBuilding;
        this.currentBuilding = nowBuilding.setCantGolpesRecibos(golpes);
    }

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

    public void setNameBuilderArma(String nombre){
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.setNombre(nombre);
    }

    public void setAlcanceBuilderArma(int alcance){
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.setAlcance(alcance);
    }

    public void setDanoBuilderArma(int dano){
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.setDano(dano);
    }

    public void setRangoBuilderArma(int rangoExplosion){
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.setRangoExplosion(rangoExplosion);
    }

    public void addAparienciaArma(int nivel,String accion)
    {
        Arma.BuilderArma nowBuilding = (Arma.BuilderArma) currentBuilding;
        this.currentBuilding = nowBuilding.addApariencia(nivel,accion,currentImages);
    }

    //TODO:Implementar en GUI
    public Arma buildCurrentArma(){
        Arma arma = (Arma)this.currentBuilding;
        PrototypeFactory.addItem(arma.getNombre(),arma);
        return arma;
    }

    ////////////////////////////////////////////////////Metodos de Manejo de imagenes//////////////////////////////////////////////////
    //Cuando se estan agregando las imagenes en el builder. Para no agregar de una en una en el builder sino que meterlas por accion
    //Se tiene este array de imagenes que tiene las imagenes que se han cargado hasta el momento. Cuando se cree el grupo de imagenes por nivel y accion entonces se agrega al builder
    public void addToCurrentImages(String url){
        currentImages.add(url);
    }
    public void clearImages(){
        currentImages = new ArrayList<>();
    }
    public void removeLast(){
        if(!currentImages.isEmpty())
        currentImages.remove(currentImages.size()-1);
    }

    //Validacion
    public boolean areImages(){
        return currentImages.size() != 0;
    }
}
