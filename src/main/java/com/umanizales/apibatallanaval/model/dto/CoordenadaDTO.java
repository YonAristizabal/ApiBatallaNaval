package com.umanizales.apibatallanaval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
//Clase para la coordenada donde va ubicado el barco
public class CoordenadaDTO implements Serializable {
    private int x;
    private int y;
    private Boolean estado;

    //Metodo para comparar las pocisiones entrantes frente a las que ya estan
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CoordenadaDTO){
            CoordenadaDTO objDTO= (CoordenadaDTO) obj;
            if (this.x == objDTO.getX() && this.y == objDTO.getY()){
                return true;
            }
        }
        return false;
    }
}
