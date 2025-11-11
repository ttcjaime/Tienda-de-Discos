package com.jaimeva.tiendadiscomvc.base;

public class ReproductorCd extends Reproductor {

    private String salidaAudio;

    public ReproductorCd(String color, String materialDeCaja, boolean bluetooth, int puertos_Usb, String salidaAudio) {
        super(color, materialDeCaja, bluetooth, puertos_Usb);
        this.salidaAudio = salidaAudio;
    }

    public String getSalidaAudio() {
        return salidaAudio;
    }

    public void setSalidaAudio(String salidaAudio) {
        this.salidaAudio = salidaAudio;
    }
}
