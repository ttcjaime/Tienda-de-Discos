package com.jaimeva.tiendadiscomvc.vista;

import com.github.lgooddatepicker.components.DatePicker;
import com.jaimeva.tiendadiscomvc.base.Disco;

import javax.swing.*;

public class AddDisco {
    //paneles
    public JPanel panelPrincipal;
    public JPanel PanelEleccion;
    public JPanel PanelCabecera;
    public JPanel JPanelCentro;
    public JPanel PrimerPanelCentro;
    public JPanel PanelDisco;
    public JPanel PanelArtista;
    public JPanel PanelGenero;
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

    //JRadioButton
    public JRadioButton pickDiscoCd;
    public JRadioButton pickDiscoVinilo;

    //JTextField
    public JTextField txtNombre;
    public JTextField txtModificador1;
    public JTextField txtCanciones;
    public JTextField txtModificador2;
    public JTextField txtTiempo;
    public JTextField txtArtista;
    public JTextField txtGenero;

    //JLabel
    public JLabel lblModificar1;
    public JLabel lblModificar2;

    //botones
    public JButton btnExportar;
    public JButton btnNuevo;
    public JButton btnImportar;
    public JButton btnBorrar;

    //spinner
    private SpinnerNumberModel spinnerModel;
    public JSpinner spinnerPrecio;

    //demas
    public JList<Disco> list1;
    public DatePicker fechaPicker;
    public JFrame ventana;
    public DefaultListModel dlmDisco;

    public AddDisco() {
        ventana = new JFrame("Añadir Discos");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1100, 600);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        initComponents();
    }

    public void initComponents() {
        dlmDisco = new DefaultListModel<Disco>();
        list1.setModel(dlmDisco);
        spinnerModel = new SpinnerNumberModel(1,0,500,1);
        spinnerPrecio.setModel(spinnerModel);
    }

    public int getPrecio() {
        Object value = spinnerPrecio.getValue();
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else {
            return 0; // o lanzar una excepción
        }
    }



}
