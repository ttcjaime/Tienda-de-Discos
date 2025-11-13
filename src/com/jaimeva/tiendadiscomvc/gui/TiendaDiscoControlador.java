package com.jaimeva.tiendadiscomvc.gui;

import com.jaimeva.tiendadiscomvc.base.Cd;
import com.jaimeva.tiendadiscomvc.base.Disco;
import com.jaimeva.tiendadiscomvc.base.Vinilo;
import com.jaimeva.tiendadiscomvc.util.Util;
import com.jaimeva.tiendadiscomvc.vista.AddDisco;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class TiendaDiscoControlador implements ActionListener, ListSelectionListener, WindowListener {

    private AddDisco addDisco;
    private TiendaDiscoModelo modelo;
    private File ultimaRutaExportacion;
    private String camposVacios;

    public TiendaDiscoControlador(AddDisco addDisco, TiendaDiscoModelo modelo) {
        this.addDisco = addDisco;
        this.modelo = modelo;

        addActionListener(this);
        addListaSeleccionListener(this);
    }

    private String camposVacios() {
        camposVacios = "Rellena los siguientes campos: \n";
        if (addDisco.txtArtista.getText().isEmpty()) {
            camposVacios += "Artista\n";
        } if (addDisco.txtCanciones.getText().isEmpty()) {
            camposVacios += "Canciones\n";
        } if (addDisco.txtGenero.getText().isEmpty()) {
            camposVacios += "Genero Musical\n";
        } if (addDisco.txtTiempo.getText().isEmpty()) {
            camposVacios += "Tiempo de reproducción\n";
        } if (addDisco.txtNombre.getText().isEmpty()) {
            camposVacios += "Nombre del disco\n";
        } if (addDisco.fechaPicker.getText().isEmpty()) {
            camposVacios += "Fecha de lanzamiento\n";
        }
         if (addDisco.txtModificador1.getText().isEmpty()) {
            if (addDisco.pickDiscoCd.isSelected()) {
                camposVacios += "Formato de Audio" + "\n";
            } else if (addDisco.pickDiscoVinilo.isSelected()) {
                camposVacios += "Color" + "\n";
            }
        }  if (addDisco.txtModificador2.getText().isEmpty()) {
            if (addDisco.pickDiscoCd.isSelected()) {
                camposVacios += "Capacidad" + "\n";
            } else if (addDisco.pickDiscoVinilo.isSelected()) {
                camposVacios += "Pulgadas" + "\n";
            }
        }
        return camposVacios;
    }

    private boolean algunCampoVacio() {
        if (addDisco.txtNombre.getText().isEmpty() || addDisco.txtArtista.getText().isEmpty() || addDisco.txtModificador2.getText().isEmpty()
        || addDisco.txtModificador1.getText().isEmpty() || addDisco.txtCanciones.getText().isEmpty() || addDisco.txtGenero.getText().isEmpty()
        || addDisco.txtTiempo.getText().isEmpty() || addDisco.fechaPicker.getText().isEmpty()) {
            return true;
        }
        return false;
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

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties(); //coge ruta por defecto y directorio por defecto
        configuracion.load(new FileReader("vehiculos.comf"));
        ultimaRutaExportacion = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportacion) {
        this.ultimaRutaExportacion = ultimaRutaExportacion;
    }

    private void guardaConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExporacion", ultimaRutaExportacion.getAbsolutePath());
        configuracion.store(new PrintWriter("vehiculos.conf"),"Datos configuracion vehiculo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionComand = e.getActionCommand(); //se realizan las acciones de todos los botones
        switch(actionComand) {
            case "Nuevo":
                if (algunCampoVacio()) {
                    Util.errorMensaje(camposVacios());
                    break;
                }
                try {
                    if (addDisco.pickDiscoCd.isSelected()) {
                        modelo.altaDiscoCD(addDisco.txtArtista.getText(), addDisco.fechaPicker.getDate(),  addDisco.txtNombre.getText(), addDisco.txtCanciones.getText()
                                , Double.parseDouble(addDisco.txtTiempo.getText()), addDisco.getPrecio(), addDisco.txtGenero.getText(),
                                Integer.parseInt(addDisco.txtModificador1.getText()), addDisco.txtModificador2.getText());

                    } else {
                        modelo.altaDiscoVinilo(addDisco.txtArtista.getText(), addDisco.fechaPicker.getDate(), addDisco.txtNombre.getText(), addDisco.txtCanciones.getText()
                                , Double.parseDouble(addDisco.txtTiempo.getText()), addDisco.getPrecio(), addDisco.txtGenero.getText(),
                                Integer.parseInt(addDisco.txtModificador1.getText()), addDisco.txtModificador2.getText());
                    }
                    limpiarCampos();
                    refrescar();
                } catch (NumberFormatException nfe) {
                    Util.errorMensaje("Introduce un número correcto");
                }
                break;
            case "Disco CD":
                addDisco.lblModificar1.setText("Capacidad");
                addDisco.lblModificar2.setText("Formato de Audio");
                break;
            case "Disco Vinilo":
                addDisco.lblModificar1.setText("Pulgadas");
                addDisco.lblModificar2.setText("Color");
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            //String artista, LocalDate fecha_Lanzamiento, String nombre,
            // String canciones, double tiempoReproduccion, double precio, String generoMusical
            Disco disco  = addDisco.list1.getSelectedValue();
            addDisco.txtArtista.setText(disco.getArtista());
            addDisco.txtNombre.setText(disco.getNombre());
            addDisco.txtTiempo.setText(disco.getTiempoReproduccion() + "");
            addDisco.txtGenero.setText(disco.getNombre());
            addDisco.txtCanciones.setText(disco.getCanciones());
            addDisco.spinnerPrecio.setValue(disco.getPrecio());
            addDisco.txtGenero.setText(disco.getGeneroMusical());
            if (disco instanceof Cd) {
                addDisco.pickDiscoCd.doClick();
                addDisco.txtModificador1.setText(String.valueOf(((Cd) disco).getCapacidad()));
                addDisco.txtModificador2.setText(((Cd) disco).getFormatoAudio());
            } else {
                addDisco.pickDiscoVinilo.doClick();
                addDisco.txtModificador1.setText(String.valueOf(((Vinilo) disco).getPulgadas()));
                addDisco.txtModificador2.setText(((Vinilo) disco).getColor());
            }
        }
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

}
