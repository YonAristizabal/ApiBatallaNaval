package com.umanizales.apibatallanaval.model.entities;


import javax.persistence.*;

@Entity
@Table(name = "tablero", schema = "public", catalog = "batalla_naval")
public class Tablero {
    private int id;
    private int cols;
    private int filas;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id =id;
    }
}
