package com.jaimeva.tiendadiscomvc.vista;

import com.github.lgooddatepicker.components.DatePicker;
import com.jaimeva.tiendadiscomvc.base.Disco;

import javax.swing.*;

public class AddDisco {
    //paneles
    public JPanel panelPrincipal;
    public JPanel panelEleccion;
    public JPanel panelCabecera;
    public JPanel panelCentro;
    public JPanel primerPanelCentro;
    public JPanel panelDisco;
    public JPanel panelArtista;
    public JPanel panelGenero;
    public JPanel segundoPanelCentro;
    public JPanel tercerPanelCentro;
    public JPanel cuartoPanelCentro;
    public JPanel panelFecha;
    public JPanel panelPrecio;
    public JPanel panelTiempo;
    public JPanel panelFormato;
    public JPanel panelCanciones;
    public JPanel panelCapacidad;
    public JPanel panelImportar;
    public JPanel panelNuevo;
    public JPanel panelAdd;
    public JPanel panelBorrar;
    public JPanel panelVolver;
    public JButton btnVolver;

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

    //hechos por mi
    public JFrame ventana;
    public DefaultListModel<Disco> dlmDisco;
    public Menu menu;


    public AddDisco() {
        ventana = new JFrame("Añadir Discos");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.setSize(1100, 600);
        ventana.setLocationRelativeTo(null);
        initComponents();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void volverMenu() {
        menu.ventana.setVisible(true);
        ventana.setVisible(false);
    }

    public void initComponents() {
        dlmDisco = new DefaultListModel<Disco>();
        list1.setModel(dlmDisco);
        spinnerModel = new SpinnerNumberModel(1,1,500,1);
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

    public DefaultListModel eliminarDatos() {
        DefaultListModel model = (DefaultListModel) list1.getModel();

        model.remove(list1.getSelectedIndex());

        return model;

    }

}
