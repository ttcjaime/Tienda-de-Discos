package com.jaimeva.tiendadiscomvc.gui;

import com.jaimeva.tiendadiscomvc.base.Cd;
import com.jaimeva.tiendadiscomvc.base.Disco;
import com.jaimeva.tiendadiscomvc.base.Reproductor;
import com.jaimeva.tiendadiscomvc.base.Vinilo;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TiendaDiscoModelo {
    private ArrayList<Disco> listaDisco;
    public ArrayList<Reproductor> listaReproductor;

    public TiendaDiscoModelo() {
        listaDisco = new ArrayList<>();
        listaReproductor = new ArrayList<>();
    }

    public ArrayList<Disco> obtenerDisco() {return listaDisco;}
    public ArrayList<Reproductor> obtenerReproductor() {return listaReproductor;}

    public void altaDiscoCD(String artista, LocalDate fecha_Lanzamiento, String canciones,  String nombre, double tiempoReproduccion, int precio, String generoMusical,
                            int capacidad, String formatoAudio) {
        Cd cd = new Cd(artista, fecha_Lanzamiento, nombre, canciones, tiempoReproduccion, precio, generoMusical, capacidad, formatoAudio);
        listaDisco.add(cd);
    }

    public void altaDiscoVinilo(String artista, LocalDate fecha_Lanzamiento, String canciones,  String nombre, double tiempoReproduccion, int precio, String generoMusical,
                                int pulgadas, String color) {
        Vinilo vinilo = new Vinilo(artista, fecha_Lanzamiento, nombre, canciones, tiempoReproduccion, precio, generoMusical, pulgadas, color);
        listaDisco.add(vinilo);
    }

    //comprobamos si existe el disco dado
    public boolean existeDisco(String nombre) {
        for (Disco disco : listaDisco) {
            if (disco.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    //exportarXML
    //controlar errores
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("Discos");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoDisco = null;
        Element nodoDato = null;
        Text texto = null;

        for (Disco disco : listaDisco) {
            if (disco instanceof Cd) {
                nodoDisco = documento.createElement("Disco Cd");
            } else {
                nodoDisco = documento.createElement("Disco Vinilo");
            }

            raiz.appendChild(nodoDisco);

            nodoDato = documento.createElement("artista");
            nodoDato.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getArtista());
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("fecha-lanzamiento");
            nodoDato.appendChild(nodoDato);

            texto = documento.createTextNode(String.valueOf(disco.getFecha_Lanzamiento()));
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("canciones");
            nodoDato.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getCanciones());
            nodoDato.appendChild(texto);


            nodoDato = documento.createElement("nombre");
            nodoDato.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getNombre());
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("tiempo-reproduccion");
            nodoDato.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getTiempoReproduccion() + "");
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("precio");
            nodoDato.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getPrecio() + "");
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("genero");
            nodoDato.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getGeneroMusical());
            nodoDato.appendChild(texto);

            if (disco instanceof Cd) {
                nodoDato = documento.createElement("capacidad");
                nodoDato.appendChild(nodoDato);

                texto = documento.createTextNode(((Cd) disco).getCapacidad() + "");
                nodoDato.appendChild(texto);

                nodoDato = documento.createElement("formato-audio");
                nodoDato.appendChild(nodoDato);

                texto = documento.createTextNode(((Cd) disco).getFormatoAudio());
                nodoDato.appendChild(texto);

            } else if (disco instanceof Vinilo) {
                nodoDato = documento.createElement("pulgadas");
                nodoDato.appendChild(nodoDato);

                texto = documento.createTextNode(((Vinilo) disco).getPulgadas() + "");
                nodoDato.appendChild(texto);

                nodoDato = documento.createElement("color");
                nodoDato.appendChild(nodoDato);

                texto = documento.createTextNode(((Vinilo) disco).getColor());
                nodoDato.appendChild(texto);
            }

            Source source = new DOMSource(documento);
            Result result = new StreamResult(fichero);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        }

    }

    //importarXML
    //controlar errores
    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaDisco = new ArrayList<Disco>();
        Cd cd = null;
        Vinilo nuevoVinilo = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoDisco = (Element) listaElementos.item(i);
            //artista, fecha, canciones, nombre, tiempo, precio, genero, capacidad, formato
            if (nodoDisco.getTagName().equals("Disco")) {
                cd = new Cd();
                cd.setArtista(nodoDisco.getChildNodes().item(0).getTextContent());
                cd.setFecha_Lanzamiento(LocalDate.parse(nodoDisco.getChildNodes().item(1).getTextContent()));
                cd.setCanciones(nodoDisco.getChildNodes().item(2).getTextContent());
                cd.setNombre(nodoDisco.getChildNodes().item(3).getTextContent());
                cd.setTiempoReproduccion(Integer.parseInt(nodoDisco.getChildNodes().item(4).getTextContent()));
                cd.setPrecio(Integer.parseInt(nodoDisco.getChildNodes().item(5).getTextContent()));
                cd.setGeneroMusical(nodoDisco.getChildNodes().item(6).getTextContent());
                cd.setCapacidad(Integer.parseInt(nodoDisco.getChildNodes().item(7).getTextContent()));
                cd.setFormatoAudio(nodoDisco.getChildNodes().item(8).getTextContent());
                listaDisco.add(cd);
            } else {
                if (nodoDisco.getTagName().equals("Vinilo")) {
                    nuevoVinilo = new Vinilo();
                    nuevoVinilo.setArtista(nodoDisco.getChildNodes().item(0).getTextContent());
                    nuevoVinilo.setFecha_Lanzamiento(LocalDate.parse(nodoDisco.getChildNodes().item(1).getTextContent()));
                    nuevoVinilo.setCanciones(nodoDisco.getChildNodes().item(2).getTextContent());
                    nuevoVinilo.setNombre(nodoDisco.getChildNodes().item(3).getTextContent());
                    nuevoVinilo.setTiempoReproduccion(Integer.parseInt(nodoDisco.getChildNodes().item(4).getTextContent()));
                    nuevoVinilo.setPrecio(Integer.parseInt(nodoDisco.getChildNodes().item(5).getTextContent()));
                    nuevoVinilo.setGeneroMusical(nodoDisco.getChildNodes().item(6).getTextContent());
                    nuevoVinilo.setPulgadas(Integer.parseInt(nodoDisco.getChildNodes().item(7).getTextContent()));
                    nuevoVinilo.setColor(nodoDisco.getChildNodes().item(8).getTextContent());
                    listaDisco.add(nuevoVinilo);
                }
            }

        }

    }
}
