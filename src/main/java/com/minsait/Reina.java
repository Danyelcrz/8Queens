package com.minsait;

import java.util.List;

public class Reina extends Pieza {

    boolean situar(Tablero tablero, int columna) {
        boolean esSolucion = false; // bandera que valida si hay una solucion parcial hasta este momento
        //si ya estamos en la esquina derecha del tablero es porque ya se colocaron las 8 reinas
        if (columna == tablero.getTamanio()) {//CASO BASE DE LA RECURSION
            tablero.imprimirTablero();
            System.out.println("\n\n");
            return true;//Si se logro colocar una reina en cada columna entonces es una solucion.
        }

        //Tenemos una columna fija (se paso por parametro), queda recorrer todas las casillas (renglon) que tiene esa columna
        for (int i = 0; i < tablero.getTamanio(); i++) { // for que recorre el renglon de la columna en turno
            if (validarCasilla(tablero, i, columna)) {
                //si la casilla en turno es vacia, entonces la ponemos como atacada
                tablero.getCasillas().get(i).get(columna).setEstado(Estado.ATACADO);

                //Para que sea una solucion debemos validar que 
                // 1) se haya llegado al final del tablero y
                // 2) en el paso anterior se haya podido colocar una reina (lo que quiere decir que las 7 anteriores se pudieron colocar)
                //si ambas situaciones se cumplen, se setea la bandera en TRUE (ES SOLUCION)
                esSolucion = situar(tablero, columna + 1) || esSolucion;//PASO RECURSIVO

                //Si en el ultimo paso no se encontró una solucion, entonces se regresa un paso (se setea en vacio) y se continua con la posicion siguiente
                tablero.getCasillas().get(i).get(columna).setEstado(Estado.VACIO);
            }
        }
        return esSolucion;
    }

    //solo es necesario valida un extremo de punto donse se esta
    boolean validarCasilla(Tablero tablero, int renglon, int columna) {
        int i, j;

        //validamos que no haya aguna reina en la izquierda
        for (i = 0; i < columna; i++) {
            if (tablero.getCasillas().get(renglon).get(i).getEstado().name().equals(Estado.ATACADO.name())) {
                return false;
            }
        }

        //validamos que no haya aguna reina en la diagonal inferior izquierda
        for (i = renglon, j = columna; i >= 0 && j >= 0; i--, j--) {
            if (tablero.getCasillas().get(i).get(j).getEstado().name().equals(Estado.ATACADO.name())) {
                return false;
            }
        }

        //validamos que no haya aguna reina en la diagonal inferior derecha
        for (i = renglon, j = columna; j >= 0 && i < tablero.getTamanio(); i++, j--) {
            if (tablero.getCasillas().get(i).get(j).getEstado().name().equals(Estado.ATACADO.name())) {
                return false;
            }
        }
        return true;
    }

    void movimiento(Tablero tablero) {
        Casilla casilla = tablero.encontrarPrimeraLibre();
        //Casilla casilla = new Casilla(false, 4, 3);
        casilla.setEstado(Estado.OCUPADO);
        //tablero.getCasillas().get(3).get(4).setEstado(Estado.OCUPADO);

        Casilla casillaEncontrada;

        for (int indiceRenglon = 0; indiceRenglon < tablero.getTamanio(); indiceRenglon++) {
            casillaEncontrada = tablero.getCasillas().get(indiceRenglon).get(casilla.getPosicionColumna());
            coloarAtaque(casillaEncontrada);
            for (int indiceColumna = 0; indiceColumna < tablero.getTamanio(); indiceColumna++) {
                casillaEncontrada = tablero.getCasillas().get(casilla.getPosicionFila()).get(indiceColumna);
                coloarAtaque(casillaEncontrada);
            }
        }

        int posicionFila = casilla.getPosicionFila() + 1;
        int posicionColumna = casilla.getPosicionColumna() + 1;

        for (; posicionFila < tablero.getTamanio() && posicionColumna < tablero.getTamanio(); posicionFila++, posicionColumna++) {
            coloarAtaque(tablero.getCasillas().get(posicionFila).get(posicionColumna));
        }

        posicionFila = casilla.getPosicionFila() - 1;
        posicionColumna = casilla.getPosicionColumna() + 1;

        for (; posicionFila >= 0 && posicionColumna >= 0; posicionFila--, posicionColumna++) {
            coloarAtaque(tablero.getCasillas().get(posicionFila).get(posicionColumna));
        }

        posicionFila = casilla.getPosicionFila() - 1;
        posicionColumna = casilla.getPosicionColumna() - 1;

        for (; posicionFila >= 0 && posicionColumna >= 0; posicionFila--, posicionColumna--) {
            coloarAtaque(tablero.getCasillas().get(posicionFila).get(posicionColumna));
        }

        posicionFila = casilla.getPosicionFila() + 1;
        posicionColumna = casilla.getPosicionColumna() - 1;

        for (; posicionFila >= 0 && posicionColumna >= 0; posicionFila++, posicionColumna--) {
            coloarAtaque(tablero.getCasillas().get(posicionFila).get(posicionColumna));
        }
    }

    private void coloarAtaque(Casilla casillaEncontrada) {

        if (casillaEncontrada.getEstado().name().equals(Estado.VACIO.name())) {
            casillaEncontrada.setEstado(Estado.ATACADO);
        }
    }

    void movimientoFilaInterminable(List<Casilla> casillasFilaInterminable) {
        //TODO: Generar el movimiento de la reina
    }

    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        Reina reina = new Reina();

        //empezamos en la columna mas a la izquierda
        reina.situar(tablero, 0);

//        reina.movimiento(tablero);
//        reina.movimiento(tablero);
//        reina.movimiento(tablero);
//        //tablero.getCasillas().get(0).get(0).setPieza(reina);
//        tablero.imprimirTablero();
    }
}
