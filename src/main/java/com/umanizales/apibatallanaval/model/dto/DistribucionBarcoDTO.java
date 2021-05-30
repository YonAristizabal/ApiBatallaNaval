package com.umanizales.apibatallanaval.model.dto;

import com.umanizales.apibatallanaval.model.entities.Barco;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import java.io.Serializable;

@Getter
@Setter
public class DistribucionBarcoDTO implements Serializable {

    private Barco barco;
    private byte orientacion;
    private String estado;//Tocado, Hundido, Estado
    private CoordenadaDTO[] casillas;

    public DistribucionBarcoDTO(Barco barco) {
        this.barco = barco;
        this.estado = "INTACTO";

    }

    public void definirUbicacion(int x, int y, byte orientacion) {
        this.casillas = new CoordenadaDTO[barco.getNumeroCasillas()];

        for (int i = 0; i < casillas.length; i++) {
            //Verificar que la coordenada este libre
            casillas[i] = new CoordenadaDTO(x, y, false);
            if (orientacion == 1) {//Horizontal
                x++;
            } else {
                y++;
            }
        }
    }

    public void definirUbicacion(CoordenadaDTO[] coordenadas){
        this.casillas= coordenadas;
    }

    public boolean validarExistenciaCoordenada(CoordenadaDTO coordenada) {
        if (casillas != null) {
            for (CoordenadaDTO coord : casillas) {
                if (coord.equals(coordenada)) {
                    return true;
                }
            }
        }
        return false;
    }


    public CoordenadaDTO[] sugerirUbicacion(int x, int y, byte orientacion) {
        CoordenadaDTO[] casillasSugeridas = new CoordenadaDTO[barco.getNumeroCasillas()];

        for (int i = 0; i < casillas.length; i++) {
            //Verificar que la coordenada este libre
            casillas[i] = new CoordenadaDTO(x, y, false);
            if (orientacion == 1) {//Horizontal
                x++;
            } else {
                y++;
            }
        }
        return casillasSugeridas;
    }
}