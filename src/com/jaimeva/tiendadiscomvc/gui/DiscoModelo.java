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

public class DiscoModelo {
    private ArrayList<Disco> listaDisco;

    public DiscoModelo() {
        listaDisco = new ArrayList<>();
    }

    public ArrayList<Disco> obtenerDisco() {return listaDisco;}

    public void altaDiscoCD(String artista, LocalDate fecha_Lanzamiento, String nombre,  String canciones, double tiempoReproduccion, int precio, String generoMusical,
                            int capacidad, String formatoAudio) {
        Cd cd = new Cd(artista, fecha_Lanzamiento, nombre, canciones, tiempoReproduccion, precio, generoMusical, capacidad, formatoAudio);
        listaDisco.add(cd);
    }
    //String artista, LocalDate fecha_Lanzamiento, String nombre, String canciones, double tiempoReproduccion, int precio, String generoMusical
    public void altaDiscoVinilo(String artista, LocalDate fecha_Lanzamiento, String nombre,  String canciones, double tiempoReproduccion, int precio, String generoMusical,
                                int pulgadas, String color) {
        Vinilo vinilo = new Vinilo(artista, fecha_Lanzamiento, nombre, canciones, tiempoReproduccion, precio, generoMusical, pulgadas, color);
        listaDisco.add(vinilo);
    }

    //comprobamos si existe el disco dado
    public boolean existeDisco(String nombre, String artista) {
        for (Disco disco : listaDisco) {
            if (disco.getNombre().equals(nombre) && disco.getArtista().equals(artista)) {
                return true;
            }
        }
        return false;
    }

    //exportarXML
    //controlar errores
    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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
                nodoDisco = documento.createElement("DiscoCd"); //recordar que no se admiten espacios
            } else {
                nodoDisco = documento.createElement("DiscoVinilo");
            }

            raiz.appendChild(nodoDisco);

            nodoDato = documento.createElement("artista");
            nodoDisco.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getArtista());
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("fecha-lanzamiento");
            nodoDisco.appendChild(nodoDato);

            texto = documento.createTextNode(String.valueOf(disco.getFecha_Lanzamiento()));
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("canciones");
            nodoDisco.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getCanciones());
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("nombre");
            nodoDisco.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getNombre());
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("tiempo-reproduccion");
            nodoDisco.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getTiempoReproduccion() + "");
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("precio");
            nodoDisco.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getPrecio() + "");
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("genero");
            nodoDisco.appendChild(nodoDato);

            texto = documento.createTextNode(disco.getGeneroMusical());
            nodoDato.appendChild(texto);

            if (disco instanceof Cd) {
                nodoDato = documento.createElement("capacidad");
                nodoDisco.appendChild(nodoDato);

                texto = documento.createTextNode(((Cd) disco).getCapacidad() + "");
                nodoDato.appendChild(texto);

                nodoDato = documento.createElement("formato-audio");
                nodoDisco.appendChild(nodoDato);

                texto = documento.createTextNode(((Cd) disco).getFormatoAudio());
                nodoDato.appendChild(texto);

            } else if (disco instanceof Vinilo) {
                nodoDato = documento.createElement("pulgadas");
                nodoDisco.appendChild(nodoDato);

                texto = documento.createTextNode(((Vinilo) disco).getPulgadas() + "");
                nodoDato.appendChild(texto);

                nodoDato = documento.createElement("color");
                nodoDisco.appendChild(nodoDato);

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
            if (nodoDisco.getTagName().equals("DiscoCd")) {
                cd = new Cd();
                cd.setArtista(nodoDisco.getChildNodes().item(0).getTextContent());
                cd.setFecha_Lanzamiento(LocalDate.parse(nodoDisco.getChildNodes().item(1).getTextContent()));
                cd.setCanciones(nodoDisco.getChildNodes().item(2).getTextContent());
                cd.setNombre(nodoDisco.getChildNodes().item(3).getTextContent());
                cd.setTiempoReproduccion(Double.parseDouble(nodoDisco.getChildNodes().item(4).getTextContent()));
                cd.setPrecio(Integer.parseInt(nodoDisco.getChildNodes().item(5).getTextContent()));
                cd.setGeneroMusical(nodoDisco.getChildNodes().item(6).getTextContent());
                cd.setCapacidad(Integer.parseInt(nodoDisco.getChildNodes().item(7).getTextContent()));
                cd.setFormatoAudio(nodoDisco.getChildNodes().item(8).getTextContent());
                listaDisco.add(cd);
            } else {
                if (nodoDisco.getTagName().equals("DiscoVinilo")) {
                    nuevoVinilo = new Vinilo();
                    nuevoVinilo.setArtista(nodoDisco.getChildNodes().item(0).getTextContent());
                    nuevoVinilo.setFecha_Lanzamiento(LocalDate.parse(nodoDisco.getChildNodes().item(1).getTextContent()));
                    nuevoVinilo.setCanciones(nodoDisco.getChildNodes().item(2).getTextContent());
                    nuevoVinilo.setNombre(nodoDisco.getChildNodes().item(3).getTextContent());
                    nuevoVinilo.setTiempoReproduccion(Double.parseDouble(nodoDisco.getChildNodes().item(4).getTextContent()));
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
