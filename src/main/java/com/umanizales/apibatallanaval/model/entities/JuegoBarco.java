package com.umanizales.apibatallanaval.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "juego_barco", schema = "public", catalog = "batalla_naval")
public class JuegoBarco {
    private int id;
    @Transient
    private int cantidadBarcos;
    private int tipoBarco;
    private int juegoId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCantidadBarcos() {
        return cantidadBarcos;
    }

    public void setCantidadBarcos(int cantidadBarcos) {
        this.cantidadBarcos = cantidadBarcos;
    }

    @Column(name = "juego_id", nullable = true)
    public int getJuegoId() {
        return this.juegoId;
    }
    public void setJuegoId(int juegoId){
        this.juegoId = juegoId;
    }

    @Column(name = "barco_id", nullable = true)
    public int getTipoBarco() {
        return this.tipoBarco;
    }
    public void setTipoBarco(int tipoBarco ){
        this.tipoBarco = tipoBarco;
    }

}
