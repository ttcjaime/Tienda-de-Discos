package com.jaimeva.tiendadiscomvc.base;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cd extends Disco{

    private int capacidad;
    private String formatoAudio;
    public Cd() {

    }
    public Cd(String artista,  String nombre, String canciones, double tiempoReproduccion, double precio, String generoMusical, int capacidad, String formatoAudio) {
        super(artista,  nombre, canciones, tiempoReproduccion, precio, generoMusical);
        this.capacidad = capacidad;
        this.formatoAudio = formatoAudio;
    }



    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getFormatoAudio() {
        return formatoAudio;
    }

    public void setFormatoAudio(String formatoAudio) {
        this.formatoAudio = formatoAudio;
    }

    @Override
    public String toString() {
        return super.toString() + "Cd{" +
                "capacidad=" + capacidad +
                ", formatoAudio='" + formatoAudio + '\'' +
                '}';
    }
}
