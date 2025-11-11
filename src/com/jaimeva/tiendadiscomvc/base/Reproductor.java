package com.jaimeva.tiendadiscomvc.base;

public class Reproductor {

    private String color;
    private String materialDeCaja;
    private boolean Bluetooth;
    private int puertos_Usb;

    public Reproductor(String color, String materialDeCaja, boolean bluetooth, int puertos_Usb) {
        this.color = color;
        this.materialDeCaja = materialDeCaja;
        Bluetooth = bluetooth;
        this.puertos_Usb = puertos_Usb;
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

    public boolean isBluetooth() {
        return Bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        Bluetooth = bluetooth;
    }

    public int getPuertos_Usb() {
        return puertos_Usb;
    }

    public void setPuertos_Usb(int puertos_Usb) {
        this.puertos_Usb = puertos_Usb;
    }
}
