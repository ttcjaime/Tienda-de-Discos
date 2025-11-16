package com.jaimeva.tiendadiscomvc.base;

public class Reproductor {

    private String marca;
    private String modelo;
    private String color;
    private String materialDeCaja;
    private int puertos_Usb;
    private int precio;

    public Reproductor() {

    }

    public Reproductor(String marca, String modelo, String color, String materialDeCaja, int puertos_Usb, int precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.materialDeCaja = materialDeCaja;
        this.puertos_Usb = puertos_Usb;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterialDeCaja() {
        return materialDeCaja;
    }

    public void setMaterialDeCaja(String materialDeCaja) {
        this.materialDeCaja = materialDeCaja;
    }

    public int getPuertos_Usb() {
        return puertos_Usb;
    }

    public void setPuertos_Usb(int puertos_Usb) {
        this.puertos_Usb = puertos_Usb;
    }

    @Override
    public String toString() {
        return "Marca: " + marca + " | Modelo: " + modelo + " | Precio: " + precio + " | Color: " + color
        + " | Material: " + materialDeCaja + " | Puertos USB: " + puertos_Usb;
    }
}
