package com.jaimeva.tiendadiscomvc.gui;

import com.jaimeva.tiendadiscomvc.base.Disco;
import com.jaimeva.tiendadiscomvc.vista.AddDisco;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class TiendaDiscoControlador implements ActionListener, ListSelectionListener, WindowListener {

    private AddDisco addDisco;
    private TiendaDiscoModelo modelo;
    private File ultimaRutaExportacion;
    private String camposVacios;

    public TiendaDiscoControlador(AddDisco addDisco, TiendaDiscoModelo modelo) {
        this.addDisco = addDisco;
        this.modelo = modelo;
    }

    private String hayCamposVacions() {
        if (addDisco.txtArtista.getText().isEmpty()) {
            camposVacios += addDisco.txtArtista.getText() + "\n";
        } else if (addDisco.txtCanciones.getText().isEmpty()) {
            camposVacios += addDisco.txtCanciones + "\n";
        } else if (addDisco.txtGenero.getText().isEmpty()) {
            camposVacios += addDisco.txtGenero + "\n";
        } else if (addDisco.txtTiempo.getText().isEmpty()) {
            camposVacios += addDisco.txtTiempo + "\n";
        } else if (addDisco.txtNombre.getText().isEmpty()) {
            camposVacios += addDisco.txtNombre + "\n";
        } else if (addDisco.fechaPicker.getText().isEmpty()) {
            camposVacios += addDisco.fechaPicker.getText() + "\n";
        }
        else if (addDisco.txtModificador1.getText().isEmpty()) {
            camposVacios += "";
        } else if (addDisco.txtModificador2.getText().isEmpty()) {
            camposVacios += "";
        }
        return camposVacios;
    }

    private void limpiarCampos() {
        addDisco.txtNombre.setText("");
        addDisco.txtTiempo.setText("");
        addDisco.txtGenero.setText("");
        addDisco.txtCanciones.setText("");
        addDisco.txtArtista.setText("");
        addDisco.txtModificador1.setText("");
        addDisco.txtModificador2.setText("");
        addDisco.fechaPicker.setText("");
        addDisco.spinnerPrecio.setValue(0);
    }

    private void addActionListener(ActionListener listener) {
        addDisco.btnImportar.addActionListener(listener);
        addDisco.btnExportar.addActionListener(listener);
        addDisco.btnNuevo.addActionListener(listener);
        addDisco.pickDiscoCd.addActionListener(listener);
        addDisco.pickDiscoVinilo.addActionListener(listener);
        addDisco.btnBorrar.addActionListener(listener);
    }

    private void addListaSeleccionListener(ListSelectionListener listener) {
        addDisco.list1.addListSelectionListener(listener);
    }

    public void refrescar() {
        addDisco.dlmDisco.clear();
        //modelo.obtenerVehiculo contiene la lista de vehiculos
        for (Disco disco: modelo.obtenerDisco()) {
            addDisco.dlmDisco.addElement(disco);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
 //no usar
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

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

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
