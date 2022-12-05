package models;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class File {
    
    public static ArrayList<Propertycolor> getPropiedades() {
        ArrayList<Propertycolor> propiedades = new ArrayList<>();
        Propertycolor tempCP = new Propertycolor();
        try {
            Scanner leer = new Scanner(new FileReader("src\\models\\propiedades.txt"));
            int pos = 0;
            String n = null;
            int p = 0;
            int[] r = new int[6];
            int cos = 0;
            int hipo = 0;
            Group g = null;
            int contador = 0;
            while (leer.hasNextLine()) {
                contador++;
                String line = leer.nextLine();
                String[] separado = line.split(":");
                pos = Integer.parseInt(separado[0]);
                n = separado[1];
                p = Integer.parseInt(separado[2]);
                for (int i = 0; i < 6; i++) {
                    r[i] = Integer.parseInt(separado[3 + i]); 
                }
                cos = Integer.parseInt(separado[9]);
                hipo = Integer.parseInt(separado[10]);
                switch (contador) {
                    case 1:
                    case 2:
                        g = Group.MARRON;
                        break;
                    case 3:
                    case 4:
                    case 5:
                        g = Group.CIELO;
                        break;
                    case 6:
                    case 7:
                    case 8:
                        g = Group.ROSA;
                        break;
                    case 9:
                    case 10:
                    case 11:
                        g = Group.NARANJA;
                        break;
                    case 12:
                    case 13:
                    case 14:
                        g = Group.ROJO;
                        break;
                    case 15:
                    case 16:
                    case 17:
                        g = Group.AMARRILLO;
                        break;
                    case 18:
                    case 19:
                    case 20:
                        g = Group.VERDE;
                        break;
                    case 21:
                    case 22:
                        g = Group.AZUL;
                        break;
                }
                tempCP = new Propertycolor(pos, n, p, r, cos, hipo, g);
                propiedades.add(tempCP);
            }
        } catch (FileNotFoundException fnfe) {
            Mostrar.error("El archivo \"propiedades.txt\" no encontrado.");
        } catch (IOException ioe) {
            Mostrar.error("El archivo \"propiedades.txt\" no se puede leer.");
        }
        return propiedades;
    }
    
}
    
    
    
    
    
    
    
    
    
    
    
    
    

