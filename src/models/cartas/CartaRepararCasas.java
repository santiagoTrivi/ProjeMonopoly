package models.cartas;

import models.Player;
import models.Propertycolor;

public class CartaRepararCasas extends CartaTomarPagar {
    private int pCasa;
    private int pHotel;

    public CartaRepararCasas(int pCasa, int pHotel, String descrip){
        super(0, descrip);
        this.pCasa = pCasa;
        this.pHotel = pHotel;
    }

    private int calcularTarifa(Player jugador){
        int tarifa = 0;
        for(Propertycolor cp : jugador.getListaGrupoColores()){
            if(cp.getCant_House() == 5){
                tarifa += pHotel;
            } else if (cp.getCant_House() > 0) {
                tarifa += pCasa * cp.getCant_House();
            }
        }
        return tarifa;
    }

    @Override
    public void hacer(Player jugador){
        jugador.setDinero(-calcularTarifa(jugador));
    }
}
