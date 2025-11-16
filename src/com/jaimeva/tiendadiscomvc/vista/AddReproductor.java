package com.jaimeva.tiendadiscomvc.vista;

import com.github.lgooddatepicker.components.DatePicker;
import com.jaimeva.tiendadiscomvc.base.Disco;
import com.jaimeva.tiendadiscomvc.base.Reproductor;

import javax.swing.*;

public class AddReproductor {
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
    public JPanel panelPrecio;
    public JPanel tercerPanelCentro;
    public JPanel panelCapacidad;
    public JPanel panelVolver;
    public JPanel panelImportar;
    public JPanel panelNuevo;
    public JPanel panelAdd;
    public JPanel panelBorrar;

    //botones
    public JButton btnVolver;
    public JButton btnImportar;
    public JButton btnNuevo;
    public JButton btnExportar;
    public JButton btnBorrar;

    //labels
    public JLabel lblModificar1;

    //textFields
    public JTextField txtColor;
    public JTextField txtMaterial;
    public JTextField txtMarca;
    public JTextField txtModificador1;
    public JTextField txtModelo;

    //radioButton
    public JRadioButton pickReproductorVinilo;
    public JRadioButton pickReproductorCd;

    //spinner
    public JSpinner spinnerPuertos;
    public JSpinner spinnerPrecio;

    //jlist
    public JList<Reproductor> list1;

    //creados por mi
    private SpinnerNumberModel spinnerPuertoModel;
    private SpinnerNumberModel spinnerPrecioModel;
    public JFrame ventana;
    public DefaultListModel<Reproductor> dlmReproductor;
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
        dlmReproductor = new DefaultListModel<Reproductor>();
        list1.setModel(dlmReproductor);
        spinnerPuertoModel = new SpinnerNumberModel(1,1,500,1);
        spinnerPuertos.setModel(spinnerPuertoModel);
        spinnerPrecioModel = new SpinnerNumberModel(1, 1, 500, 1);
        spinnerPrecio.setModel(spinnerPrecioModel);
    }

    public int getPuertos() {
        Object value = spinnerPuertos.getValue();
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else {
            return 0;
        }
    }

    public int getPrecio() {
        Object value = spinnerPrecio.getValue();
        if (value instanceof Number) {
            return ((Number) value).intValue();
        } else {
            return 0;
        }
    }

    public DefaultListModel eliminarDatos() {
        DefaultListModel model = (DefaultListModel) list1.getModel();

        model.remove(list1.getSelectedIndex());

        return model;

    }

}
