package com.jaimeva.tiendadiscomvc.base;

public class ReproductorCd extends Reproductor {

    private String salidaAudio;

    public ReproductorCd() {

    }

    public ReproductorCd(String marca, String modelo, String color, String materialDeCaja,
                         int puertos_Usb, String salidaAudio, int precio) {
        super(marca, modelo, color, materialDeCaja, puertos_Usb, precio);
        this.salidaAudio = salidaAudio;
    }

    public String getSalidaAudio() {
        return salidaAudio;
    }

    public void setSalidaAudio(String salidaAudio) {
        this.salidaAudio = salidaAudio;
    }

    @Override
    public String toString() {
        return "Reproductor de Cd | " + super.toString() + " | Salida de Audio: " + salidaAudio;
    }

}
