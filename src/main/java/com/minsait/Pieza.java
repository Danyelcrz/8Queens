package com.minsait;

import java.util.List;

abstract class Pieza {
    private boolean color;

    abstract void movimiento(Tablero tablero);

    abstract void movimientoFilaInterminable(List<Casilla> casillasFilaInterminable);
}
