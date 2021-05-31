package com.umanizales.apibatallanaval.model;

import com.umanizales.apibatallanaval.model.dto.CoordenadaDTO;
import com.umanizales.apibatallanaval.model.dto.DistribucionBarcoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListaDE implements Serializable {

    private NodoDE cabeza;
    private int cont;

    public void adicionarNodo(Object dato){

        if (cabeza == null){

            cabeza = new NodoDE(dato);

        }
        else {
            //Llamar a mi ayudante y colocarme en el ultimo
            NodoDE temp = cabeza;
            while(temp.getSiguiente()!=null)
            {
                temp= temp.getSiguiente();
            }
            ///Parado en el ultimo
            temp.setSiguiente(new NodoDE(dato));
            temp.getSiguiente().setAnterior(temp);
        }
    }

    public void adicionarNodoAlInicio(Object dato){

        if (cabeza == null) {

        }

    }

    public List<Object> listarDatos(){
        return null;
    }

    //Permite clonar la listaDE para que se diferente tanto para el Jugador 1, como para el Jugador 2
    public ListaDE clonarlist(){
        ListaDE listaCopia = new ListaDE();
        NodoDE temp = cabeza;

        while (temp != null){

            listaCopia.adicionarNodo(temp);
            temp= temp.getSiguiente();

        }
        return listaCopia;
    }

    //Permite validar la existencia de un listado de coordenas
    public boolean validarExistenciaCoordenadas(CoordenadaDTO[] coordenas){
        NodoDE temp = cabeza;
        if (cabeza != null){
            while (temp != null){
                for (CoordenadaDTO coord: coordenas){
                if (((DistribucionBarcoDTO)temp.getDato()).validarExistenciaCoordenada(coord)){
                    return true;
                    }
                }
                temp= temp.getSiguiente();
            }
        }
        return false;
    }
}
