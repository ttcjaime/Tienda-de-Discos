package com.jaimeva.tiendadiscomvc.base;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReproductorVinilo extends Reproductor {

    private String selectorVelocidad;

    public ReproductorVinilo(String color, String materialDeCaja, boolean bluetooth, int puertos_Usb, String selectorVelocidad) {
        super(color, materialDeCaja, bluetooth, puertos_Usb);
        this.selectorVelocidad = selectorVelocidad;
    }

    public String getSelectorVelocidad() {
        return selectorVelocidad;
    }

    public void setSelectorVelocidad(String selectorVelocidad) {
        this.selectorVelocidad = selectorVelocidad;
    }
}
