package com.jaimeva.tiendadiscomvc.vista;

import com.github.lgooddatepicker.components.DatePicker;
import com.jaimeva.tiendadiscomvc.base.Disco;

import javax.swing.*;

public class AddDisco {
    public JPanel panelPrincipal;
    public JRadioButton pickDiscoCd;
    public JPanel PanelEleccion;
    public JRadioButton pickDiscoVinilo;
    public JPanel PanelCabecera;
    public JPanel JPanelCentro;
    public JTextField txtNombre;
    public JPanel PrimerPanelCentro;
    public JPanel PanelDisco;
    public JPanel PanelArtista;
    public JPanel PanelGenero;
    public JButton btnExportar;
    public JButton btnNuevo;
    public JSpinner spinnerPrecio;
    public JPanel SegundoPanelCentro;
    public JPanel TercerPanelCentro;
    public JPanel CuartoPanelCentro;
    public JPanel PanelFecha;
    public JPanel PanelPrecio;
    public JPanel PanelTiempo;
    public JPanel PanelFormato;
    public JPanel PanelCanciones;
    public JPanel PanelCapacidad;
    public JPanel PanelImportar;
    public JPanel PanelNuevo;
    public JPanel PanelAdd;
    public JPanel PanelBorrar;
    public JButton btnImportar;
    public JButton btnBorrar;
    public JTextField txtModificador1;
    public JTextField txtCanciones;
    public JTextField txtModificador2;
    public JTextField txtTiempo;
    public JTextField txtArtista;
    public JTextField txtGenero;
    public JList list1;
    public DatePicker fechaPicker;
    public JLabel lblModificar1;
    public JLabel lblModificar2;

    public JFrame ventana;
    public DefaultListModel dlmDisco;
    public JPanel[] paneles;
    public JTextField[] modificadores;

    public AddDisco() {
        ventana = new JFrame("Añadir Discos");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1100, 600);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        modificadores = new JTextField [] {txtModificador1, txtModificador2};
        //paneles = new JPanel{};
    }

    public void initComponents() {
        dlmDisco = new DefaultListModel<Disco>();
        list1.setModel(dlmDisco);
    }

    public int getPrecio() {
        return (int) spinnerPrecio.getValue();
    }

}
