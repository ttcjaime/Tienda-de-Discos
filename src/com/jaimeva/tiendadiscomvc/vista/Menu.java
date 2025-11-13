package com.jaimeva.tiendadiscomvc.vista;

import com.github.lgooddatepicker.components.DatePicker;
import com.jaimeva.tiendadiscomvc.gui.TiendaDiscoControlador;
import com.jaimeva.tiendadiscomvc.gui.TiendaDiscoModelo;

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

    JButton[] botones;

    public Menu() {
        ventana = new JFrame("Tienda de discos");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

}
