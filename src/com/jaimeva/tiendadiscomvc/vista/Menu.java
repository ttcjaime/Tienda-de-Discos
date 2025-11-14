package com.jaimeva.tiendadiscomvc.vista;

import com.github.lgooddatepicker.components.DatePicker;
import com.jaimeva.tiendadiscomvc.gui.TiendaDiscoControlador;
import com.jaimeva.tiendadiscomvc.gui.TiendaDiscoModelo;
import com.jaimeva.tiendadiscomvc.util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Menu implements WindowListener {
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
        botones = new JButton[] {btnAddDisco, btnAddReproductor, btnAddReproductor};
        boton();
    }

    public void boton() {
        for (JButton boton : botones) {
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch(boton.getText()) {
                        case "Añadir Disco":
                            AddDisco addDisco = new AddDisco();
                            TiendaDiscoModelo modelo = new TiendaDiscoModelo();
                            TiendaDiscoControlador controlador = new TiendaDiscoControlador(addDisco, modelo);
                            break;
                        case "Ver Lista":
                            break;
                        case "Añadir Reproductor":
                            break;
                    }
                }
            });
        }
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
