package models;

public abstract class Casilla {
    int position;
    String name;

    public Casilla() {
        this.position = 0;
        this.name = null;
    }
    
    public Casilla(int posicion, String nombre) {
        this.position = posicion;
        this.name = nombre;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int pos) {
         this.position = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public abstract void hacer(Player actualJugador);
}
