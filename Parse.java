/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testjjj;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author Svegencev_A_S
 */
public class Parse {
        
       public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
       
       public Locale local = new Locale ("ru","RU");
       public Date d = new Date();
       public DateFormat df  = DateFormat.getDateInstance(DateFormat.DEFAULT, local);
    public Document ParseXml() {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("embmaps.xml");
            document.getDocumentElement().normalize();
            
            return document;
            }
        catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
            
        }
    return null;
    }
        public void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            document.setXmlStandalone(true);
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("embmaps.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
        
        }
        
                public void writeLog(Document document) throws TransformerFactoryConfigurationError {
        try {
            Date d2 = new Date();
            String path = "logs/" + dateFormat.format(d2) + "embmaps.xml";
            document.setXmlStandalone(true);
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(path);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
        
        }
//        public final DefaultListModel CreateList (Document xml) {
//            //    try {
//            final DefaultListModel listModel = new DefaultListModel();
//            for (int i=0;i < xml.getElementsByTagName("company").getLength(); i++ )
//                {
//                    
//                    Node company_new = xml.getElementsByTagName("company").item(i);    
//                    NodeList list = company_new.getChildNodes(); 
//                            for (int j = 0; j<list.getLength();j++) {
//                            Node nod = list.item(j);
//                            
//                            if ("company-id".equals(nod.getNodeName())) {
//                            listModel.addElement((i+1) + " " + nod.getTextContent());
//                            }
//            
//                            }      
//                  
//                   
//                }
//            return listModel; 
//                }
         /*          catch  (IOException ex) {
            ex.printStackTrace(System.out);*/
                
        

        
    



       

          
    
}
