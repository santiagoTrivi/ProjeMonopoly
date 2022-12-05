package models;

public class Propertycolor extends Property {
    private final Group group;
    private int[] rents;
    private int cant_House;
    private int House_cost;
    
    public Propertycolor() {
        super();
        this.group = null;
        this.rents = new int[6];
        this.cant_House = 0;
        this.House_cost = 0;
    }
    
    public Propertycolor(int pos, String n, int p, int[] r, int cos, int hipo, Group g) {
        super(pos, n, p, hipo);
        this.group = g;
        this.rents = r;
        this.cant_House = 0;
        this.House_cost = cos;
    }

     public Group getGroup() {
         return this.group;
     }

    public void buyHouse(){
        getPropietario().setDinero(-House_cost);
        cant_House++;
        if(cant_House == 5){
            Mostrar.texto("Compraste un hotel en " + name + " por 4 casas + " + House_cost + "$");
        } else {
            System.out.println("Compraste un casa en " + name + " por " + + House_cost + "$");
        }  
    }
    
    public int getCant_House() {
        return this.cant_House;
    }
    
    public int getRenta() {
        switch (this.cant_House) {
            case 1:
                return this.rents[1];
            case 2:
                return this.rents[2];
            case 3:
                return this.rents[3];
            case 4:
                return this.rents[4];
            case 5:
                return this.rents[5];
            default:
                return this.rents[0];
        }
    }
    
    public int getHouse_cost() {
        return this.House_cost;
    }

    @Override
    public void hacer(Player actualJugador) {
        if (propietario == actualJugador) {
           // No hacer nada
        } else if ((!hipotecada) && (propietario != null)) {
            Mostrar.texto(actualJugador.getNombre() + " paga a " + propietario.getNombre() + " " + getRenta() + "$ de renta");
            actualJugador.pagar(propietario, getRenta());
        } else {
            ofertar(actualJugador);
        } 
    }
    
    @Override
    public String toString() {
        String string = "";
        string += "\n\t" + this.name + "\n\n"
                + "Renta: " + this.rents[0] + "$" + "\n"
                + "Con 1 Casa: " + this.rents[1] + "$" + "\n"
                + "Con 2 Casa: " + this.rents[2] + "$" + "\n"
                + "Con 3 Casa: " + this.rents[3] + "$" + "\n"
                + "Con 4 Casa: " + this.rents[4] + "$" + "\n"
                + "Con Hotel: " + this.rents[5] + "$" + "\n\n"
                + "Hipoteca: " + this.valorHipoteca + "$" + "\n\n"
                + "Las Casa cuestan: " + this.House_cost + "$" + "\n"
                + "El Hotel cuesta: 4 casas + " + this.House_cost + "$";
        return string;
    }
}
