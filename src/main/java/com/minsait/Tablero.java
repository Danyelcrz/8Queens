package com.minsait;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private int tamanio = 8;

    private List<List<Casilla>> casillas = new ArrayList<List<Casilla>>();
    private List<Casilla> casillasFilaInterminable = new ArrayList<Casilla>();

    public Tablero() {
        inicializarTablero();
        //inicializarTableroFilaInterminable();
    }

    public int getTamanio() {
        return tamanio;
    }

    public List<List<Casilla>> getCasillas() {
        return casillas;
    }

    public List<Casilla> getCasillasFilaInterminable() {
        return casillasFilaInterminable;
    }

    private void inicializarTablero() {
        for (int indiceRenglon = 0; indiceRenglon < tamanio; indiceRenglon++) {
            casillas.add(new ArrayList<Casilla>());
            for (int indiceColumna = 0; indiceColumna < tamanio; indiceColumna++) {
                casillas.get(indiceRenglon).add(construirCasilla(
                        definirColor(indiceRenglon, indiceColumna),
                        indiceColumna,
                        indiceRenglon)
                );
            }
        }
    }

    private void inicializarTableroFilaInterminable() {
        int variableControladora = -1;
        for (int indice = 0; indice < tamanio * tamanio; ++indice) {
            if (indice % tamanio == 0) {
                variableControladora++;
            }
            casillasFilaInterminable.add(
                    construirCasilla(
                            definirColor(indice),
                            indice % tamanio,
                            variableControladora));
        }
    }

    private boolean definirColor(int indiceRenglon, int indiceColumna) {
        return (indiceRenglon + indiceColumna) % 2 == 0;
    }

    private boolean definirColor(int indice) {
        return indice % 2 == 0;
    }

    private Casilla construirCasilla(boolean color, Integer posicionColumna, Integer posicionFila) {
        return new Casilla(color, posicionColumna, posicionFila);
    }

    public Casilla encontrarPrimeraLibre() {
        for (List<Casilla> casillasARecorrer: casillas) {
            for (Casilla casilla:casillasARecorrer) {
                if (casilla.getEstado() == Estado.VACIO) {
                    return casilla;
                }
            }
        }
        return null;
    }

    public void imprimirTablero() {
        for (List<Casilla> renglon: casillas) {
            for (Casilla columna: renglon) {
                System.out.printf("%10s", columna.getEstado());
            }
            System.out.printf("\n");
        }
    }

    @Override
    public String toString() {
        return "Tablero{" +
                "tamanio=" + tamanio +
                ", \ncasillas=" + casillas +
                ", \ncasillasFilaInterminable=" + casillasFilaInterminable +
                '}';
    }

    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        tablero.imprimirTablero();
    }
}
