package com.jaimeva.tiendadiscomvc.util;

import javax.swing.*;

public class Util {

    public static void errorMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "",
                JOptionPane.ERROR_MESSAGE);
    }

}
