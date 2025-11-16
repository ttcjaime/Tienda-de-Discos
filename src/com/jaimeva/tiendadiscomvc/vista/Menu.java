package com.jaimeva.tiendadiscomvc.vista;

import com.jaimeva.tiendadiscomvc.gui.DiscoControlador;
import com.jaimeva.tiendadiscomvc.gui.DiscoModelo;
import com.jaimeva.tiendadiscomvc.gui.ReproductorControlador;
import com.jaimeva.tiendadiscomvc.gui.ReproductorModelo;
import com.jaimeva.tiendadiscomvc.util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class Menu implements WindowListener {

    private AddDisco addDisco;
    private AddReproductor addReproductor;

    public JPanel panelPrincipal;
    public JPanel PanelCabecera;
    public JButton btnAddReproductor;
    public JButton btnVerLista;
    public JButton btnAddDisco;

    private File ultimaRutaExportacion;

    public JFrame ventana;

    JButton[] botones;

    public Menu() {
        ventana = new JFrame("Tienda de discos");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ventana.setSize(600, 500);
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        botones = new JButton[] {btnAddDisco, btnAddReproductor};
        addWindowListener(this);
        addDisco = new AddDisco();
        DiscoModelo modelo = new DiscoModelo();
        DiscoControlador controlador = new DiscoControlador(addDisco, modelo); //los creo aqui para que la ventana addDisco solo se abra una vez, y no haya más ocultas abiertas
        addReproductor = new AddReproductor();
        ReproductorModelo modeloReproductor = new ReproductorModelo();
        ReproductorControlador rc = new ReproductorControlador(addReproductor, modeloReproductor);
        boton();
    }

    public void boton() {
        for (JButton boton : botones) {
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch(boton.getText()) {
                        case "Añadir Disco":
                            addDisco.setMenu(Menu.this);
                            Menu.this.ventana.setVisible(false);
                            addDisco.ventana.setVisible(true);
                            break;
                        case "Ver Lista":
                            break;
                        case "Añadir Reproductor":
                            addReproductor.setMenu(Menu.this);
                            Menu.this.ventana.setVisible(false);
                            addReproductor.ventana.setVisible(true);
                            break;
                    }
                }
            });
        }
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
