package com.jaimeva.tiendadiscomvc.vista;

import javax.swing.*;

public class AddDisco {
    private JPanel panelPrincipal;
    private JRadioButton btnDiscoCd;
    private JPanel PanelEleccion;
    private JRadioButton btnDiscoVinilo;
    private JPanel PanelCabecera;
    private JPanel JPanelCentro;
    private JTextField textField1;
    private JPanel PrimerPanelCentro;
    private JPanel PanelDisco;
    private JPanel PanelArtista;
    private JPanel PanelGenero;

    public JFrame ventana;

    public AddDisco() {
        ventana = new JFrame("Añadir Discos");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600, 500);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }

}
