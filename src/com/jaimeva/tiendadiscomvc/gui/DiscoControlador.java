package com.jaimeva.tiendadiscomvc.gui;

import com.jaimeva.tiendadiscomvc.base.Cd;
import com.jaimeva.tiendadiscomvc.base.Disco;
import com.jaimeva.tiendadiscomvc.base.Vinilo;
import com.jaimeva.tiendadiscomvc.util.Util;
import com.jaimeva.tiendadiscomvc.vista.AddDisco;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class DiscoControlador implements ActionListener, ListSelectionListener, WindowListener {

    private AddDisco addDisco;
    private DiscoModelo modelo;
    private File ultimaRutaSeleccionada;
    private String camposVacios;

    public DiscoControlador(AddDisco addDisco, DiscoModelo modelo) {
        this.addDisco = addDisco;
        this.modelo = modelo;

        addActionListener(this);
        addListaSeleccionListener(this);
        addWindowListener(this);

        cargarDatosConfiguracion();


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
        addDisco.btnVolver.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        addDisco.ventana.addWindowListener(listener);
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

    private void cargarDatosConfiguracion() {

        Properties configuracion = new Properties();

        try {
            configuracion.load(new FileReader("disco.conf"));
            ultimaRutaSeleccionada = new File(configuracion.getProperty("ultimaRutaSeleccionada"));
        } catch (IOException e) {
            ultimaRutaSeleccionada = new File(System.getProperty("user.home"));
        }

    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaSeleccionada = ultimaRutaExportada;
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
                if (modelo.existeDisco(addDisco.txtNombre.getText(), addDisco.txtArtista.getText())) {
                    Util.errorMensaje("Ya existe un disco con el nombre " + addDisco.txtNombre.getText() + "\n del artista " + addDisco.txtArtista.getText());
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
            case "Importar XML":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaSeleccionada, "Archivos XML", "xml");
                int opt = selectorFichero.showOpenDialog(null);
                if (opt == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    } catch (NumberFormatException nfe) {
                        Util.errorMensaje("No se puede cargar un XML con un formato de numeros incorrecto");
                    }
                    refrescar();
                }
                break;
            case "Exportar XML":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaSeleccionada, "Archivos XML", "xml");
                int opt2 = selectorFichero2.showSaveDialog(null);
                if (opt2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "Volver":
                addDisco.volverMenu();
                break;
            case "Disco CD":
                addDisco.lblModificar1.setText("Capacidad");
                addDisco.lblModificar2.setText("Formato de Audio");
                break;
            case "Disco Vinilo":
                addDisco.lblModificar1.setText("Pulgadas");
                addDisco.lblModificar2.setText("Color");
                break;
            case "Borrar":
                try {
                    addDisco.eliminarDatos();
                    limpiarCampos();
                } catch (ArrayIndexOutOfBoundsException aiobe) {
                    Util.errorMensaje("Selecciona un disco para borrar");
                }
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            //String artista, LocalDate fecha_Lanzamiento, String nombre, String canciones, double tiempoReproduccion, int precio, String generoMusical
            Disco disco  = addDisco.list1.getSelectedValue();
            addDisco.txtArtista.setText(disco.getArtista());
            addDisco.fechaPicker.setDate(disco.getFecha_Lanzamiento());
            addDisco.txtNombre.setText(disco.getNombre());
            addDisco.txtCanciones.setText(disco.getCanciones());
            addDisco.txtTiempo.setText(disco.getTiempoReproduccion() + "");
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

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Util.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            guardarConfiguracion();
            System.exit(0);
        }
    }

    private void guardarConfiguracion() {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaSeleccionada", ultimaRutaSeleccionada.getAbsolutePath());
        try {
            configuracion.store(new PrintWriter("discos.conf"), "Datos configuracion Disco");
        } catch (IOException e) {
            System.out.println();
        }
    }

    //no usar
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
