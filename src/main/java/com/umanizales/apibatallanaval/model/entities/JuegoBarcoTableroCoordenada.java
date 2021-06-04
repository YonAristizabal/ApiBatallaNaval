package com.umanizales.apibatallanaval.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "JuegoBarcoTableroCoordenadas", schema = "public", catalog = "batalla_naval")
public class JuegoBarcoTableroCoordenada {
    private int id;
    private JuegoBarcoTablero juegoBarcoTablero;
    private boolean estaTocado;
    private int x;
    private int y;

    public JuegoBarcoTableroCoordenada(boolean estaTocado, int x, int y) {
        this.estaTocado = estaTocado;
        this.x = x;
        this.y = y;
    }

    public JuegoBarcoTableroCoordenada() {

    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "juego_barco_tablero", referencedColumnName = "id", nullable = false)
    public JuegoBarcoTablero getJuegoBarcoTablero() {
        return juegoBarcoTablero;
    }

    public void setJuegoBarcoTablero(JuegoBarcoTablero juegoBarcoTablero) {
        this.juegoBarcoTablero = juegoBarcoTablero;
    }
    @Column(name = "is_esta_tocado", nullable = true)
    public boolean isEstaTocado() {
        return estaTocado;
    }

    public void setEstaTocado(boolean estaTocado) {
        this.estaTocado = estaTocado;
    }

    @Column(name = "x", nullable = true)
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Column(name = "y", nullable = true)
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public List<JuegoBarcoTableroCoordenada> construirCoordenadas(short numerocCasillas, String orientacion){
        List <JuegoBarcoTableroCoordenada> juegoBarcoTableroCoordenadas = new ArrayList<>();
      for(short i=0;i<numerocCasillas;i++){
          int x = this.x;
          int y = this.y;
          if(orientacion.equals(JuegoBarcoTablero.ORIENTACION_HORIZONTAL)){
              x= x +i;
          }else{
              y = y +i;
          }
          JuegoBarcoTableroCoordenada juegoBarcoTableroCoordenada = new JuegoBarcoTableroCoordenada(false,x,y);
          juegoBarcoTableroCoordenadas.add(juegoBarcoTableroCoordenada);
      }
      return juegoBarcoTableroCoordenadas;
    }
}
