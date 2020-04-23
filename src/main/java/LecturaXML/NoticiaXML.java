
package LecturaXML;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class NoticiaXML extends DefaultHandler{
    private ArrayList <Noticia> noticias;
    private Noticia noticiaAux;
    private String cadeaTexto;
    
    public NoticiaXML(){
        super();
    }
    
    @Override
    public void startDocument() throws SAXException{
        noticias = new ArrayList<>();
    }
    
    @Override
    public void endDocument() throws SAXException{    
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{    
        if ("title".equals(localName)){
            this.noticiaAux = new Noticia();
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException{    
        if (localName.equals("title")){
            this.noticiaAux.setTitular(this.cadeaTexto);
            this.noticias.add(this.noticiaAux);      
        }
    }
    
    @Override
    public void characters (char[] ch, int start, int length) throws SAXException{
        this.cadeaTexto = new String (ch,start,length);
    }    
    
    public ArrayList <Noticia> getNoticias(){
        return noticias;
    }    
}
