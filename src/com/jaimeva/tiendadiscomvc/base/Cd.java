package com.jaimeva.tiendadiscomvc.base;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cd extends Disco{

    private int capacidad;
    private String formatoAudio;
    public Cd() {

    }
    public Cd(String artista, LocalDate fehcha_lanzamiento, String nombre, String canciones, double tiempoReproduccion, int precio, String generoMusical, int capacidad, String formatoAudio) {
        super(artista, fehcha_lanzamiento,  nombre, canciones, tiempoReproduccion, precio, generoMusical);
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
        return "Cd | " + super.toString() + " | Capacidad: " + capacidad + " | Formato de Audio: " + formatoAudio;
    }
}
