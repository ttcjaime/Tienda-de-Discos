package com.jaimeva.tiendadiscomvc.base;

import java.time.LocalDate;
import java.util.ArrayList;

public class Disco {
    public String artista;
    public LocalDate fecha_Lanzamiento;
    public String canciones;
    public String nombre;
    public double tiempoReproduccion;
    public double precio;
    public String generoMusical;

    //falta ArrayList Canciones
    public Disco(String artista, String nombre, String canciones, double tiempoReproduccion, double precio, String generoMusical) {
        this.artista = artista;
        this.canciones = canciones;
        this.nombre = nombre;
        this.tiempoReproduccion = tiempoReproduccion;
        this.precio = precio;
        this.generoMusical = generoMusical;
    }

    public Disco() {

    }

    public String getArtista() {
        return artista;
    }

    public LocalDate getFecha_Lanzamiento() {
        return fecha_Lanzamiento;
    }

    public String getCanciones() {
        return canciones;
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

    public void setCanciones(String canciones) {
        canciones = canciones;
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
