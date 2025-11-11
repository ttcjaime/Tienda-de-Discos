package com.jaimeva.tiendadiscomvc.vista;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public JPanel panelPrincipal;
    public JPanel PanelCabecera;
    public JButton btnAddReproductor;
    public JButton btnVerLista;
    public JButton btnAddDisco;

    public JFrame ventana;

    public Menu() {
        ventana = new JFrame("Tienda de discos");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600, 500);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
    }

}
