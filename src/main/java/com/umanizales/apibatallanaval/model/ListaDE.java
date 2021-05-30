package com.umanizales.apibatallanaval.model;

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
}
