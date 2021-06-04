package com.umanizales.apibatallanaval.model.entities;


import javax.persistence.*;

@Entity
@Table(name = "JuegoBarcoTablero", schema = "public", catalog = "batalla_naval")
public class JuegoBarcoTablero {
    public static  final String ORIENTACION_HORIZONTAL = "horizontal";
    public static  final String ORIENTACION_VERTICAL = "vertical";
    private int id;
    private int juegoBarcoId;
    private int tableroId;
    private String orientacion;
    private int x;
    private int y;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "juego_barco_id", nullable = true)
    public int getJuegoBarcoId() {
        return juegoBarcoId;
    }

    public void setJuegoBarcoId(int juegoBarcoId) {
        this.juegoBarcoId = juegoBarcoId;
    }

    @Column(name = "tablero_id", nullable = true)
    public int getTableroId() {
        return tableroId;
    }

    public void setTableroId(int tableroId) {
        this.tableroId = tableroId;
    }

    @Column(name = "orientacion", nullable = true)
    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }
    @Transient
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Transient
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
