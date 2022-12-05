package models;

import java.util.ArrayList;
import java.io.*;

public class Player {
    String nombre;
    int dinero, posicion, turnosCarcel, cantFerrocarriles;
    private ArrayList<Property> propiedades;
    public int cartasSalirCarcel;
    public boolean enCarcel;

    public Player() {
        this.nombre = null;
        this.dinero = 1500;
        this.posicion = 0;
        this.propiedades = new ArrayList<>();
        this.cartasSalirCarcel = 0;
        this.enCarcel = false;
        this.turnosCarcel = 0;
    }
    
    public Player(String nombre) {
        this.nombre = nombre;
        this.dinero = 1500;
        this.posicion = 0;
        this.propiedades = new ArrayList<>();
        this.cartasSalirCarcel = 0;
        this.enCarcel = false;
        this.turnosCarcel = 0;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        if (this.dinero < -dinero) {
            bancarrota(-dinero - this.dinero);
        }
        this.dinero += dinero;
    }
    
    private void bancarrota(int faltaDinero){
        Mostrar.texto("Jugador: " + this.getNombre());
        Mostrar.texto("Te faltan " + faltaDinero + "$");
        ArrayList<Property> pSinCasa = getPropiedadesSinCasas();
        if (pSinCasa.isEmpty()) {
            Mostrar.texto("No posees ninguna propiedad que puedas hipotecar");
            Mostrar.texto("Qudaste en la bancarrota");
            String archivo = "PersonasEnBaCarrota.txt";
            try(FileWriter fw = new FileWriter(archivo, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                        out.println("Perdedor: " + this.getNombre() + "Turnos en la Carcel: " + this.turnosCarcel);
                    
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
        } else {
            Mostrar.propiedad(pSinCasa);
            int opc = Leer.opcion(pSinCasa.size(), "Escoje una propiedad: ");
            Property p = pSinCasa.get(opc - 1);
            this.hipotecar(p);
        }
    }
    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int pos) {
        this.posicion += pos;
    }

    public boolean isBancarota() {
        return dinero == 0;
    }
    
    public void pagar(Player recividor, int cant) {
        recividor.setDinero(cant);
        setDinero(-cant);
    }

    public void comprar(Property propiedad) {
        setDinero(-propiedad.getPrecio());
        propiedades.add(propiedad);
        OrdenarPropiedadesPorGrupo();
    }
    
    public void mover(int pasos) {
        this.posicion += pasos;
        boolean pasaPorSalida = (this.posicion >= 40);
        if (pasaPorSalida) {
            Mostrar.texto(nombre + " pasaste por \"SALIDA\" y cobrate 200$ de sueldo");
            this.dinero += 200;
            this.posicion -= 40;
        }
        Mostrar.texto("Ahora estas en " + Tablero.getActualCasilla(this).getName());
        Tablero.getActualCasilla(this).hacer(this);
    }
    
    public void moverA(int aPos){
        mover((40 - this.posicion + aPos) % 40);
    }
     public void vender(Property propiedad){
        setDinero(propiedad.getPrecio() / 2);
        propiedad.setPropietario(null);
    }

    public void hipotecar(Property propiedad){
        propiedad.hipotecada = true;
        setDinero(propiedad.getValorHipoteca());
    }

    public void pagaHipoteca(Property propiedad){
        propiedad.hipotecada = false;
        setDinero((int) (-propiedad.getPrecio() * 0.10));
    }
    
    /**
     * Metodo que ordena todas las porpiedades de un jugador
     * 
     */
    public void OrdenarPropiedadesPorGrupo() {
        ArrayList<Utility> utilidades = new ArrayList<>();
        ArrayList<Ferrocarril> ferrocarriles = new ArrayList<>();
        ArrayList<Property> ordenadas = new ArrayList<>();

        for (Property propiedad : this.propiedades) {
            if (propiedad instanceof Utility){
                utilidades.add((Utility) propiedad);
            } else if (propiedad instanceof Ferrocarril) {
                ferrocarriles.add((Ferrocarril) propiedad);
            } else {
                ordenadas.add(propiedad);
            }
        }        
        ordenadas.addAll(ferrocarriles);
        ordenadas.addAll(utilidades);
        this.propiedades = ordenadas;
    }
    
    public int getCantFerrocarriles(){
        int cantFerro = 0;
        for (Property p : this.propiedades) {
            if (p instanceof Ferrocarril) {
                cantFerro++;
            }
        }

        return cantFerro;
    }

    public int getCantUtilidades() {
        int cantUtilidades = 0;
        for (Property p : this.propiedades){
            if (p instanceof Utility){
                cantUtilidades++;
            }
        }

        return cantUtilidades;
    }

    public int getCantCalles() {
        int cantCalles = 0;
        for (Property p : this.propiedades){
            if (p instanceof Propertycolor){
                cantCalles++;
            }
        }
        return cantCalles;
    }
    
    public String getPropiedades() {
        String string = "\nPropiedades del Jugador\n\n";
        if (propiedades.isEmpty()) {
            string += "No pasees propiedades";
        } else {
            for(Property p : this.propiedades){
                string += p.toString() + "\n";
            } 
        }
        return string;
    }
    
    /**
     * Metodo que retorna todas las propiedades que el jugador posee de Propertycolor
     * 
     * @return Propiedades de Propertycolor del jugador
     */
    public ArrayList<Propertycolor> getListaGrupoColores(){
        ArrayList<Propertycolor> lista = new ArrayList<>();
        for (Property propiedad: propiedades){
            if ((propiedad instanceof Propertycolor) && (isPropietarioGrupo(((Propertycolor) propiedad).getGroup()))){
                lista.add((Propertycolor) propiedad);
            }
        }
        return lista;
    }

    /**
     * Metodo que retorna las propiedades donde se puede constriuir casas
     * 
     * @return Propiedades del jugador donde pueden constuir casas
     */
    public ArrayList<Propertycolor> getPropiedadesHabitables() {
        ArrayList<Propertycolor> habitables = new ArrayList<>();
        for (Propertycolor i: getListaGrupoColores()) {
            boolean minCasa = true;
            for (Propertycolor j: getListaGrupoColores()) {
                if(i.getGroup() == j.getGroup() && i.getCant_House() > j.getCant_House()){
                    minCasa = false;
                }
            }
            if ((minCasa) && (i.getCant_House() != 5)){
                habitables.add(i);
            }
        }
        return habitables;
    }

    /**
     * Metodo que retorna las propiedades sin casas, estas puedes ser hipotecadas
     * 
     * @return Propiedades sin casa del jugador
     */
    public ArrayList<Property> getPropiedadesSinCasas(){
        ArrayList<Property> sinCasas = new ArrayList<>();
        for (Property propiedad : this.propiedades) {
            if ((propiedad instanceof Propertycolor) && (((Propertycolor) propiedad).getCant_House() != 0));
            else {
                sinCasas.add(propiedad);
            }
        }
        return sinCasas;
    }

    /**
     * Metodo que retorna las propiedades hipotecadas del jugador
     * 
     * @return Propiedades hipotecados del jugador
     */
    public ArrayList<Property> getPropiedadesHipotecadas(){
        ArrayList<Property> hipotecadas = new ArrayList<>();
        for (Property propiedad : this.propiedades) {
            if (propiedad.hipotecada){
                hipotecadas.add(propiedad);
            }
        }
        return hipotecadas;
    }

    /**
     * Metodo que verifica si un jugador es dueño de una propiedad
     * 
     * @return true si el jugador es el propietario, false en caso contrario
     */
    private boolean isPropietario(Property propiedad) {
        return this.propiedades.contains(propiedad);
    }

    /**
     * Metodo que verifica si un jugador es dueño de una propiedad
     * 
     * @return true si el jugador es el propietario, false en caso contrario
     */
    public boolean isPropietarioGrupo(Group grupo){
        int contador = 0;
        for (Property propiedad : this.propiedades){
            if ((propiedad instanceof Propertycolor) && (((Propertycolor) propiedad).getGroup() == grupo)){
                contador++;
            }
        }
        return (contador == grupo.maxGrupo);
    }
    
    @Override
    public String toString() {
        String string = "\nEstado del Jugador\n\n";
        string += "Ficha: " + this.nombre + "\n"
                + "Balance: " + this.dinero + "\n"
                + "Posicion actual: " + Tablero.tablero[this.posicion].getName() + "\n"
                + "Condicion: " + ((enCarcel) ? "En la carcel" : "Jugando")  + "\n"
                + "Propiedades: " + propiedades.size()  + "\n"
                + "Calles: " + getCantCalles()  + "\n"
                + "Ferrocariles: " + getCantFerrocarriles()  + "\n"
                + "Utilidades: " + getCantUtilidades()  + "\n";
        return string;
    }
}
