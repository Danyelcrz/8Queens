package com.minsait;

public class Casilla {

    private boolean color;
    private Integer posicionColumna;
    private Integer posicionFila;
    private Estado estado;
    private Pieza pieza;

    public Casilla(boolean color, Integer posicionColumna, Integer posicionFila) {
        this.color = color;
        this.posicionColumna = posicionColumna;
        this.posicionFila = posicionFila;
        this.estado = Estado.VACIO;
    }

    public boolean isColor() {
        return color;
    }

    public Integer getPosicionColumna() {
        return posicionColumna;
    }

    public Integer getPosicionFila() {
        return posicionFila;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    @Override
    public String toString() {
        return "Casilla{" +
                "color=" + color +
                ", posicionColumna=" + posicionColumna +
                ", posicionFila=" + posicionFila +
                ", estado=" + estado +
                ", pieza=" + pieza +
                "\n}";
    }
}
