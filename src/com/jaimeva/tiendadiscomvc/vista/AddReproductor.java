package com.jaimeva.tiendadiscomvc.vista;

import com.github.lgooddatepicker.components.DatePicker;
import com.jaimeva.tiendadiscomvc.base.Disco;

import javax.swing.*;

public class AddReproductor {
    public JPanel panelPrincipal;
    public JPanel PanelEleccion;
    public JRadioButton pickReproductorVinilo;
    public JRadioButton pickDiscoCd;
    public JPanel PanelCabecera;
    public JPanel JPanelCentro;
    public JPanel PrimerPanelCentro;
    public JPanel PanelDisco;
    public JTextField txtColor;
    public JPanel PanelArtista;
    public JTextField txtMaterial;
    public JPanel PanelGenero;
    public JTextField txtBluetooth;
    public JPanel SegundoPanelCentro;
    public JPanel PanelPrecio;
    public JSpinner spinnerPuertos;
    public JPanel TercerPanelCentro;
    public JPanel PanelCapacidad;
    public JLabel lblModificar1;
    public JTextField txtModificador1;
    public JPanel panelVolver;
    public JButton btnVolver;
    public JPanel PanelImportar;
    public JButton btnImportar;
    public JPanel PanelNuevo;
    public JButton btnNuevo;
    public JPanel PanelAdd;
    public JButton btnExportar;
    public JPanel PanelBorrar;
    public JButton btnBorrar;
    public JList list1;

    private SpinnerNumberModel spinnerModel;

    public JFrame ventana;
    public DefaultListModel dlmReproductor;
    public Menu menu;

    public AddReproductor() {
        ventana = new JFrame("Añadir Reproductores");
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
        dlmReproductor = new DefaultListModel<Disco>();
        list1.setModel(dlmReproductor);
        spinnerModel = new SpinnerNumberModel(1,0,500,1);
        spinnerPuertos.setModel(spinnerModel);
    }

    public int getPrecio() {
        Object value = spinnerPuertos.getValue();
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
