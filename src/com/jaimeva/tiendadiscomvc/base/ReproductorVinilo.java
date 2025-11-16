package com.jaimeva.tiendadiscomvc.base;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReproductorVinilo extends Reproductor {

    private String selectorVelocidad;

    public ReproductorVinilo() {

    }

    public ReproductorVinilo(String modelo, String marca, String color, String materialDeCaja,
                             int puertos_Usb, String selectorVelocidad, int precio) {
        super(marca, modelo, color, materialDeCaja, puertos_Usb, precio);
        this.selectorVelocidad = selectorVelocidad;
    }

    public String getSelectorVelocidad() {
        return selectorVelocidad;
    }

    public void setSelectorVelocidad(String selectorVelocidad) {
        this.selectorVelocidad = selectorVelocidad;
    }

    @Override
    public String toString() {
        return super.toString() + " | Selector de Velocidad: " + selectorVelocidad;
    }
}
