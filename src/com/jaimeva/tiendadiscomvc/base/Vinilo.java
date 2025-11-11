package com.jaimeva.tiendadiscomvc.base;

import java.time.LocalDate;
import java.util.ArrayList;

public class Vinilo extends Disco {
    private int pulgadas;
    private String color;

    public Vinilo(String artista, LocalDate fecha_Lanzamiento, ArrayList<String> canciones, String nombre, double tiempoReproduccion, double precio,
                  String generoMusical, int pulgadas, String color) {
        super(artista, fecha_Lanzamiento, canciones, nombre, tiempoReproduccion, precio, generoMusical);
        this.pulgadas = pulgadas;
        this.color = color;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
