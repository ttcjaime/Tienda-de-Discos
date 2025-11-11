package com.jaimeva.tiendadiscomvc.base;

import java.time.LocalDate;
import java.util.ArrayList;

public class Disco {
    public String artista;
    public LocalDate fecha_Lanzamiento;
    public ArrayList<String> Canciones;
    public String nombre;
    public double tiempoReproduccion;
    public double precio;
    public String generoMusical;

    public Disco(String artista, LocalDate fecha_Lanzamiento, ArrayList<String> canciones, String nombre, double tiempoReproduccion, double precio, String generoMusical) {
        this.artista = artista;
        this.fecha_Lanzamiento = fecha_Lanzamiento;
        Canciones = canciones;
        this.nombre = nombre;
        this.tiempoReproduccion = tiempoReproduccion;
        this.precio = precio;
        this.generoMusical = generoMusical;
    }

    public String getArtista() {
        return artista;
    }

    public LocalDate getFecha_Lanzamiento() {
        return fecha_Lanzamiento;
    }

    public ArrayList<String> getCanciones() {
        return Canciones;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTiempoReproduccion() {
        return tiempoReproduccion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setFecha_Lanzamiento(LocalDate fecha_Lanzamiento) {
        this.fecha_Lanzamiento = fecha_Lanzamiento;
    }

    public void setCanciones(ArrayList<String> canciones) {
        Canciones = canciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempoReproduccion(double tiempoReproduccion) {
        this.tiempoReproduccion = tiempoReproduccion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

}
