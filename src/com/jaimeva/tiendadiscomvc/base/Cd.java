package com.jaimeva.tiendadiscomvc.base;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cd extends Disco{

    private int capacidad;
    private String formatoAudio;

    public Cd(String artista, LocalDate fecha_Lanzamiento, ArrayList<String> canciones, String nombre,
              double tiempoReproduccion, double precio, String generoMusical, int capacidad, String formatoAudio) {
        super(artista, fecha_Lanzamiento, canciones, nombre, tiempoReproduccion, precio, generoMusical);
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

}
