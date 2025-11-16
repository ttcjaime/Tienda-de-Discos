package com.jaimeva.tiendadiscomvc.vista;

import com.jaimeva.tiendadiscomvc.base.Disco;
import com.jaimeva.tiendadiscomvc.base.Reproductor;
import com.jaimeva.tiendadiscomvc.util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class VerLista implements WindowListener {
    //paneles
    private JPanel panelPrincipal;
    private JPanel panelEleccion;
    private JPanel panelCentro;
    private JPanel panelCabecera;

    //botones
    public JButton btnVolver;

    //listas
    public JList<Reproductor> listaReproductores;
    public JList<Disco> listaDiscos;

    //creador por mi
    public JFrame ventana;
    public Menu menu;


    public VerLista() {
        ventana = new JFrame("Ver Lista");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.setSize(600, 500);
        ventana.setLocationRelativeTo(null);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverMenu();
            }
        });
        addWindowListener(this);
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void volverMenu() {
        menu.ventana.setVisible(true);
        ventana.setVisible(false);
    }

    private void addWindowListener(WindowListener listener) {
        ventana.addWindowListener(listener);
    }

    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
