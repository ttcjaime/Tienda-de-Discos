package com.jaimeva.tiendadiscomvc.gui;

import com.jaimeva.tiendadiscomvc.base.*;
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
import java.util.ArrayList;

public class ReproductorModelo {

    private ArrayList<Reproductor> listaReproductores;

    public ReproductorModelo() {
        listaReproductores = new ArrayList<>();
    }

    public ArrayList<Reproductor> getListaReproductores() {
        return listaReproductores;
    }

    public void altaReproductorVinilo(String marca, String modelo, String color, String materialDeCaja,
                                      int puertos_Usb, String selectorVelocidad, int precio) {
        ReproductorVinilo nuevo = new ReproductorVinilo(marca, modelo, color, materialDeCaja, puertos_Usb,
                selectorVelocidad, precio);
        listaReproductores.add(nuevo);
    }

    public void altaReproductorCd(String marca, String modelo, String color, String materialDeCaja,
                                  int puertos_Usb, String salidaAudio, int precio) {
        ReproductorCd nuevo = new ReproductorCd(marca, modelo, color, materialDeCaja, puertos_Usb, salidaAudio, precio);
        listaReproductores.add(nuevo);
    }

    public boolean existeReproductor(String marca, String modelo) {
        for (Reproductor r : listaReproductores) {
            if (r.getMarca().equals(marca) && r.getModelo().equals(modelo)) {
                return true;
            }
        }
        return false;
    }

    public void borrarReproductor(Reproductor r) {
        listaReproductores.remove(r);
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("Reproductores");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoReproductor = null;
        Element nodoDato = null;
        Text texto = null;

        for (Reproductor reproductor : listaReproductores) {
            if (reproductor instanceof ReproductorCd) {
                nodoReproductor = documento.createElement("ReproductorCd"); //recordar que no se admiten espacios
            } else {
                nodoReproductor = documento.createElement("ReproductorVinilo");
            }

            raiz.appendChild(nodoReproductor);

            nodoDato = documento.createElement("marca");
            nodoReproductor.appendChild(nodoDato);

            texto = documento.createTextNode(reproductor.getMarca());
            nodoDato.appendChild(texto);

           nodoDato = documento.createElement("modelo");
           nodoReproductor.appendChild(nodoDato);

           texto = documento.createTextNode(reproductor.getModelo());
           nodoDato.appendChild(texto);

           nodoDato = documento.createElement("color");
           nodoReproductor.appendChild(nodoDato);

           texto = documento.createTextNode(reproductor.getColor());
           nodoDato.appendChild(texto);

           nodoDato = documento.createElement("materialCaja");
            nodoReproductor.appendChild(nodoDato);

            texto = documento.createTextNode(reproductor.getMaterialDeCaja());
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("puertosUsb");
            nodoReproductor.appendChild(nodoDato);

            texto = documento.createTextNode(reproductor.getPuertos_Usb() + "");
            nodoDato.appendChild(texto);

            nodoDato = documento.createElement("precio");
            nodoReproductor.appendChild(nodoDato);

            texto = documento.createTextNode(reproductor.getPrecio() + "");
            nodoDato.appendChild(texto);

            if (reproductor instanceof ReproductorCd) {
                nodoDato = documento.createElement("salidaAudio");
                nodoReproductor.appendChild(nodoDato);

                texto = documento.createTextNode(((ReproductorCd) reproductor).getSalidaAudio());
                nodoDato.appendChild(texto);

            } else if (reproductor instanceof ReproductorVinilo) {
                nodoDato = documento.createElement("selectorVelocidad");
                nodoReproductor.appendChild(nodoDato);

                texto = documento.createTextNode(((ReproductorVinilo) reproductor).getSelectorVelocidad());
                nodoDato.appendChild(texto);
            }

            Source source = new DOMSource(documento);
            Result result = new StreamResult(fichero);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        }

    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaReproductores = new ArrayList<Reproductor>();
        ReproductorCd reproductorCd = null;
        ReproductorVinilo reproductorVinilo = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoReproductor = (Element) listaElementos.item(i);
            if (nodoReproductor.getTagName().equals("ReproductorCd")) {
                reproductorCd = new ReproductorCd ();
                reproductorCd.setMarca(nodoReproductor.getChildNodes().item(0).getTextContent());
                reproductorCd.setModelo(nodoReproductor.getChildNodes().item(1).getTextContent());
                reproductorCd.setColor(nodoReproductor.getChildNodes().item(2).getTextContent());
                reproductorCd.setMaterialDeCaja(nodoReproductor.getChildNodes().item(3).getTextContent());
                reproductorCd.setPuertos_Usb(Integer.parseInt(nodoReproductor.getChildNodes().item(4).getTextContent()));
                reproductorCd.setPrecio(Integer.parseInt(nodoReproductor.getChildNodes().item(5).getTextContent()));
                reproductorCd.setSalidaAudio(nodoReproductor.getChildNodes().item(6).getTextContent());
                listaReproductores.add(reproductorCd);
            } else {
                if (nodoReproductor.getTagName().equals("ReproductorVinilo")) {
                    reproductorVinilo = new ReproductorVinilo ();
                    reproductorVinilo.setMarca(nodoReproductor.getChildNodes().item(0).getTextContent());
                    reproductorVinilo.setModelo(nodoReproductor.getChildNodes().item(1).getTextContent());
                    reproductorVinilo.setColor(nodoReproductor.getChildNodes().item(2).getTextContent());
                    reproductorVinilo.setMaterialDeCaja(nodoReproductor.getChildNodes().item(3).getTextContent());
                    reproductorVinilo.setPuertos_Usb(Integer.parseInt(nodoReproductor.getChildNodes().item(4).getTextContent()));
                    reproductorVinilo.setPrecio(Integer.parseInt(nodoReproductor.getChildNodes().item(5).getTextContent()));
                    reproductorVinilo.setSelectorVelocidad(nodoReproductor.getChildNodes().item(6).getTextContent());
                    listaReproductores.add(reproductorVinilo);
                }
            }

        }

    }

}
