package com.jaimeva.tiendadiscomvc.vista;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;

public class AddDisco {
    private JPanel panelPrincipal;
    private JRadioButton btnDiscoCd;
    private JPanel PanelEleccion;
    private JRadioButton btnDiscoVinilo;
    private JPanel PanelCabecera;
    private JPanel JPanelCentro;
    private JTextField txtNombre;
    private JPanel PrimerPanelCentro;
    private JPanel PanelDisco;
    private JPanel PanelArtista;
    private JPanel PanelGenero;
    private JButton btnAdd;
    private JButton btnNuevo;
    private JSpinner spinnerPrecio;
    private JPanel SegundoPanelCentro;
    private JPanel TercerPanelCentro;
    private JPanel CuartoPanelCentro;
    private JPanel PanelFecha;
    private JPanel PanelPrecio;
    private JPanel PanelTiempo;
    private JPanel PanelFormato;
    private JPanel PanelCanciones;
    private JPanel PanelCapacidad;
    private JPanel PanelImportar;
    private JPanel PanelNuevo;
    private JPanel PanelAdd;
    private JPanel PanelBorrar;
    private JButton btnImportar;
    private JButton btnBorrar;
    private JTextField txtAudio;
    private JTextField txtCanciones;
    private JTextField txtCapacidad;
    private JTextField txtTiempo;
    private JTextField txtArtista;
    private JTextField txtGenero;

    public JFrame ventana;

    public AddDisco() {
        ventana = new JFrame("Añadir Discos");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1100, 600);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }

}
