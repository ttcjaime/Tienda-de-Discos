package com.jaimeva.tiendadiscomvc.gui;

import com.jaimeva.tiendadiscomvc.base.Disco;
import com.jaimeva.tiendadiscomvc.base.Reproductor;
import com.jaimeva.tiendadiscomvc.base.ReproductorCd;
import com.jaimeva.tiendadiscomvc.base.ReproductorVinilo;
import com.jaimeva.tiendadiscomvc.util.Util;
import com.jaimeva.tiendadiscomvc.vista.AddReproductor;
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

public class ReproductorControlador implements ActionListener, ListSelectionListener, WindowListener {

    private AddReproductor addReproductor;
    private ReproductorModelo modelo;
    private File ultimaRutaSeleccionada;
    private String camposVacios;

    public ReproductorControlador(AddReproductor addReproductor, ReproductorModelo modelo) {
        this.addReproductor = addReproductor;
        this.modelo = modelo;

        addActionListener(this);
        addListaSeleccionListener(this);
        addWindowListener(this);

        cargarDatosConfiguracion();

    }

    private String camposVacios() {
        camposVacios = "Rellena los siguientes campos: \n";
        if (addReproductor.txtColor.getText().isEmpty()) {
            camposVacios += "Color\n";
        } if (addReproductor.txtMaterial.getText().isEmpty()) {
            camposVacios += "Material\n";
        } if (addReproductor.txtMarca.getText().isEmpty()) {
            camposVacios += "Marca\n";
        } if (addReproductor.txtModelo.getText().isEmpty()) {
            camposVacios += "Modelo\n";
        }
        if (addReproductor.pickReproductorCd.isSelected()) {
            if (addReproductor.txtModificador1.getText().isEmpty()) {
                camposVacios += "Salida de Audio";
            }
        }
        if (addReproductor.pickReproductorVinilo.isSelected()) {
            if (addReproductor.txtModificador1.getText().isEmpty()) {
                camposVacios += "Selector Velocidad";
            }
        }
        return camposVacios;
    }

    private void limpiarCampos() {
        addReproductor.txtMarca.setText("");
        addReproductor.txtColor.setText("");
        addReproductor.txtModelo.setText("");
        addReproductor.txtMaterial.setText("");
        addReproductor.txtModificador1.setText("");
        addReproductor.spinnerPuertos.setValue(0);
        addReproductor.spinnerPrecio.setValue(0);
    }

    private boolean algunCampoVacio() {
        if (addReproductor.txtMaterial.getText().isEmpty() || addReproductor.txtModelo.getText().isEmpty() ||
                addReproductor.txtModificador1.getText().isEmpty() || addReproductor.txtColor.getText().isEmpty() ||
                addReproductor.txtMarca.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private void addActionListener(ActionListener listener) {
        addReproductor.btnImportar.addActionListener(listener);
        addReproductor.btnExportar.addActionListener(listener);
        addReproductor.btnNuevo.addActionListener(listener);
        addReproductor.pickReproductorCd.addActionListener(listener);
        addReproductor.pickReproductorVinilo.addActionListener(listener);
        addReproductor.btnBorrar.addActionListener(listener);
        addReproductor.btnVolver.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        addReproductor.ventana.addWindowListener(listener);
    }

    private void addListaSeleccionListener(ListSelectionListener listener) {
        addReproductor.list1.addListSelectionListener(listener);
    }

    public void refrescar() {
        addReproductor.dlmReproductor.clear();
        //modelo.obtenerVehiculo contiene la lista de vehiculos
        for (Reproductor reproductor: modelo.getListaReproductores()) {
            addReproductor.dlmReproductor.addElement(reproductor);
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

    private void guardarConfiguracion() {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaSeleccionada", ultimaRutaSeleccionada.getAbsolutePath());
        try {
            configuracion.store(new PrintWriter("discos.conf"), "Datos configuracion Disco");
        } catch (IOException e) {
            System.out.println();
        }
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
                if (modelo.existeReproductor(addReproductor.txtMarca.getText(), addReproductor.txtModelo.getText())) {
                    Util.errorMensaje("Ya existe un reproductor de la marca " + addReproductor.txtMarca.getText() + "\n , modelo " + addReproductor.txtModelo.getText());
                    break;
                }
                try {
                    if (addReproductor.pickReproductorCd.isSelected()) {
                        modelo.altaReproductorCd(addReproductor.txtMarca.getText(), addReproductor.txtModelo.getText(),
                                addReproductor.txtColor.getText(), addReproductor.txtMaterial.getText(), addReproductor.getPuertos()
                                , addReproductor.txtModificador1.getText(), addReproductor.getPrecio());

                    } else {
                        modelo.altaReproductorVinilo(addReproductor.txtMarca.getText(), addReproductor.txtModelo.getText(),
                                addReproductor.txtColor.getText(), addReproductor.txtMaterial.getText(), addReproductor.getPuertos()
                                , addReproductor.txtModificador1.getText(), addReproductor.getPrecio());
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
                        System.out.println("Importados: " + modelo.getListaReproductores().size());
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
                addReproductor.volverMenu();
                break;
            case "Reproductor CD":
                addReproductor.lblModificar1.setText("Salida Audio");
                break;
            case "Reproductor Vinilo":
                addReproductor.lblModificar1.setText("Selector Velocidad");
                break;
            case "Borrar":
                try {
                    Reproductor r = addReproductor.list1.getSelectedValue();
                    modelo.borrarReproductor(r);
                    addReproductor.eliminarDatos();
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
            Reproductor reproductor = addReproductor.list1.getSelectedValue();
            addReproductor.txtMarca.setText(reproductor.getMarca());
            addReproductor.txtModelo.setText(reproductor.getModelo());
            addReproductor.txtMaterial.setText(reproductor.getMaterialDeCaja());
            addReproductor.txtColor.setText(reproductor.getColor());
            addReproductor.spinnerPuertos.setValue(reproductor.getPuertos_Usb());
            addReproductor.spinnerPrecio.setValue(reproductor.getPrecio());
            if (reproductor instanceof ReproductorCd) {
                addReproductor.pickReproductorCd.doClick();
                addReproductor.txtMaterial.setText(((ReproductorCd) reproductor).getSalidaAudio());
            } else {
                addReproductor.pickReproductorVinilo.doClick();
                addReproductor.txtModificador1.setText(((ReproductorVinilo)reproductor).getSelectorVelocidad());
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
