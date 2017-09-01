/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testjjj;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author Svegencev_A_S
 */
public class SimpleWindow extends JFrame {
    public int i = 0;
   // получаем Документ хмл из объекта класса Парс
    Parse xmlobj = new Parse();
    public Document xml = xmlobj.ParseXml();
   // создаем переменные для передачи "сегодняшней" даты 
    public Locale local = new Locale ("ru","RU");
    public Date d = new Date();
    public DateFormat df  = DateFormat.getDateInstance(DateFormat.DEFAULT, local);
    
    public JSch jsch = new JSch();
    public String host;
    public String user;
    public String password;
    public int port;
    public Session session;
    public ChannelSftp channel;
    public String path;
    public SftpProgressMonitor monitor = new MyProgressMonitor();
  
SimpleWindow () {
    super ("ВВод данных по банкоматам для отправки в Яндекс");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    

    // создаем панельку для формы
    JPanel panel = new JPanel();
    panel.setLayout(null);
    
    
    
     // создаем текстовое поле для ID филиала и добавляем в панельку   
   JTextField text_id = new JTextField();
   text_id.setSize(100, 20);
   text_id.setLocation(200, 80);
   panel.add(text_id);
    // создаем текстовое поле для названия и добавляем в панельку
   JTextField text_name = new JTextField();
   text_name.setSize(300, 20);
   text_name.setLocation(200, 140);
   panel.add(text_name);
    // создаем текстовое поле для адреса и добавляем в панельку
   JTextField text_adress = new JTextField();
   text_adress.setSize(300, 20);
   text_adress.setLocation(200, 200);
   panel.add(text_adress);
       // создаем текстовое поле для страны и добавляем в панельку
   JTextField text_country = new JTextField();
   text_country.setSize(200, 20);
   text_country.setLocation(520, 200);
   panel.add(text_country);
    // создаем текстовое поле для режима работы и добавляем в панельку
   JTextField text_working_time = new JTextField();
   text_working_time.setSize(300, 20);
   text_working_time.setLocation(200, 260);
   panel.add(text_working_time);
    // создаем текстовое поле для рубрики и добавляем в панельку
   JTextField text_rubric_id = new JTextField();
   text_rubric_id.setSize(100, 20);
   text_rubric_id.setLocation(200, 320);
   panel.add(text_rubric_id);
    // создаем текстовое поле для долготы и добавляем в панельку
   JTextField text_lon = new JTextField();
   text_lon.setSize(150, 20);
   text_lon.setLocation(200, 400);
   panel.add(text_lon);
    // создаем текстовое поле для широтыи добавляем в панельку
   JTextField text_lat = new JTextField();
   text_lat.setSize(150, 20);
   text_lat.setLocation(370, 400);
   panel.add(text_lat);
    // создаем текстовое поле для номера телефона, добавляем в панельку
   JTextField text_phone_number = new JTextField();
   text_phone_number.setSize(150, 20);
   text_phone_number.setLocation(200, 490);
   panel.add(text_phone_number);
    // создаем текстовое поле для типа номера, добавляем в панельку
   JTextField text_phone_type = new JTextField();
   text_phone_type.setSize(150, 20);
   text_phone_type.setLocation(370, 490);
   panel.add(text_phone_type);
    // создаем текстовое поле для сайта
   JTextField text_url = new JTextField();
   text_url.setSize(150, 20);
   text_url.setLocation(200, 550);
   panel.add(text_url);
    // создаем текстовое поле для даты
   JTextField text_actualization_date = new JTextField();
   text_actualization_date.setSize(150, 20);
   text_actualization_date.setLocation(370, 550);
   panel.add(text_actualization_date);

   
    // создем подпись для ID филиала
   JLabel lab_ID = new JLabel("ID филиала");
   lab_ID.setLocation(200, 50);
   lab_ID.setSize(150, 20);
   panel.add(lab_ID);
    // создем подпись для названия организации
   JLabel lab_name = new JLabel("Название организации");
   lab_name.setLocation(200, 110);
   lab_name.setSize(150, 20);
   panel.add(lab_name);
  // создем подпись для адреса
   JLabel lab_adress = new JLabel("адрес");
   lab_adress.setLocation(200, 170);
   lab_adress.setSize(150, 20);
   panel.add(lab_adress);
  // создаем подпись для страны
   JLabel lab_country = new JLabel("страна");
   lab_country.setLocation(520, 170);
   lab_country.setSize(150, 20);
   panel.add(lab_country);
  // создем подпись для режима работы
   JLabel lab_working_time = new JLabel("режим работы");
   lab_working_time.setLocation(200, 230);
   lab_working_time.setSize(150, 20);
   panel.add(lab_working_time);
  // создем подпись для рубрики
   JLabel lab_rubric_id = new JLabel("рубрика (вид деятельности)");
   lab_rubric_id.setLocation(200, 290);
   lab_rubric_id.setSize(200, 20);
   panel.add(lab_rubric_id);
     // создем подпись для координат
   JLabel lab_coordinates = new JLabel("координаты");
   lab_coordinates.setLocation(280, 350);
   lab_coordinates.setSize(200, 20);
   panel.add(lab_coordinates);
   // создем подпись для долготы
   JLabel lab_lon = new JLabel("Долгота");
   lab_lon.setLocation(200, 370);
   lab_lon.setSize(220, 30);
   panel.add(lab_lon);
   // создем подпись для широты
   JLabel lab_lat = new JLabel("Широта");
   lab_lat.setLocation(370, 370);
   lab_lat.setSize(100, 30);
   panel.add(lab_lat);
   // создем подпись для Телефона
   JLabel lab_phone = new JLabel("Телефон");
   lab_phone.setLocation(280, 430);
   lab_phone.setSize(100, 30);
   panel.add(lab_phone);
    // создем подпись для Телефона
   JLabel lab_phone_number = new JLabel("Номер телефона");
   lab_phone_number.setLocation(200, 460);
   lab_phone_number.setSize(100, 30);
   panel.add(lab_phone_number);
       // создем подпись для Телефона
   JLabel lab_phone_type = new JLabel("Тип номера");
   lab_phone_type.setLocation(370, 460);
   lab_phone_type.setSize(100, 30);
   panel.add(lab_phone_type);
   // создем подпись для Телефона
   JLabel lab_url = new JLabel("Сайт");
   lab_url.setLocation(200, 520);
   lab_url.setSize(100, 30);
   panel.add(lab_url);
   // создем подпись для Телефона
   JLabel lab_actualization_date  = new JLabel("Дата создания");
   lab_actualization_date.setLocation(370, 520);
   lab_actualization_date.setSize(100, 30);
   panel.add(lab_actualization_date);
   // создаем чекбокс
   JCheckBox chekbox_data = new JCheckBox ("ввести дату вручную");
   chekbox_data.setLocation(470, 520);
   chekbox_data.setSize(200, 30);
   panel.add(chekbox_data);
// создаем кнопки
        JButton addButton = new JButton("Добавить");
        addButton.setLocation(200, 580);
        addButton.setSize(100, 40);
        panel.add(addButton);
        
        JButton updateButton = new JButton("Изменить");
        updateButton.setLocation(320, 580);
        updateButton.setSize(100, 40);
        panel.add(updateButton);
        
        JButton deleteButton = new JButton("Удалить");
        deleteButton.setLocation(440, 580);
        deleteButton.setSize(100, 40);
        panel.add(deleteButton);
        
        JButton cleareButton = new JButton("Очистить");
        cleareButton.setLocation(560, 580);
        cleareButton.setSize(100, 40);
        panel.add(cleareButton);
        
        JButton sendButton = new JButton("Отправить на сервер");
        sendButton.setLocation(200, 640);
        sendButton.setSize(220, 40);
        panel.add(sendButton);
        
        
        

 // строим список из хмл докуметна по тег компани ид
    final DefaultListModel listModel = new DefaultListModel();
    
        for (i = 0; i < xml.getElementsByTagName("company").getLength(); i++) {
            Node company = xml.getElementsByTagName("company").item(i);    
            NodeList list = company.getChildNodes(); 
            for (int j = 0; j<list.getLength();j++) {
                Node nod = list.item(j);
                if ("company-id".equals(nod.getNodeName())) {
                listModel.addElement((i+1) + " " + nod.getTextContent());
                }
            
            }      
        }
 // добавляем визуальный лист, в конструкторе указываем список созданный ранее
        final JList list = new JList(listModel);
        list.setSelectedIndex(0);
        list.setFocusable(false);
        list.setAlignmentX(JComponent.LEFT_ALIGNMENT);
    list.setSize(150, 600);
    list.setLocation(60 , 60);
  // добавляем скрол для листа
    JScrollPane eva = new JScrollPane(list);
    eva.setSize(130, 600);
    eva.setLocation(60 , 60);
    panel.add(eva);
    
    // добавляем слушателя на действие кнопки добавить
    list.addListSelectionListener(
            new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent e){
 
    // обнуляем значения координат
        text_lon.setText(null);
        text_lat.setText(null);
    // делаем неактивным поле даты
     text_actualization_date.setEnabled(false);
    // создаем цикл для перечисления все фидов company
        for (i = 0; i < xml.getElementsByTagName("company").getLength(); i++) {
            Node company = xml.getElementsByTagName("company").item(i);    
            NodeList list_child_company = company.getChildNodes();
    //  создаем цикл, для перечисления всех подфидов фида coordinates company
            for (int j = 0; j<list_child_company.getLength();j++) {
                Node nod = list_child_company.item(j);
    // условие для фида coordinates
                if ("coordinates".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                        NodeList list_child_coordinates = nod.getChildNodes();
    // создаем цикл, для перечисления всех подфидов фида coordinates
                        for (int l = 0; l<list_child_coordinates.getLength(); l++)
                        {
                        Node nod2 = list_child_coordinates.item(l);
                            if ("lon".equals(nod2.getNodeName()))
                            {
                            text_lon.setText(nod2.getTextContent()); 
                            }
                            if ("lat".equals(nod2.getNodeName()))
                            {
                            text_lat.setText(nod2.getTextContent()); 
                            }
                            
                        }
                    }
    // условие для фида phone           
                if ("phone".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                        NodeList list_child_phone = nod.getChildNodes();
    // создаем цикл, для перечисления всех подфидов фида coordinates
                        for (int l = 0; l<list_child_phone.getLength(); l++)
                        {
                        Node phone = list_child_phone.item(l);
                            if ("number".equals(phone.getNodeName()))
                            {
                            text_phone_number.setText(phone.getTextContent()); 
                            }
                            if ("type".equals(phone.getNodeName()))
                            {
                            text_phone_type.setText(phone.getTextContent()); 
                            }
                            
                        }
                    }
                
                

    // условие для фида company-id
                if ("company-id".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                    text_id.setText(nod.getTextContent()); 
                    }
    // условие для фида name
                if ("name".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                    text_name.setText(nod.getTextContent()); 
                    }
    // условие для фида address
                if ("address".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                    text_adress.setText(nod.getTextContent()); 
                    }
    // условие для фида country
                if ("country".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                    text_country.setText(nod.getTextContent()); 
                    }
    // условие для фида working-time
                if ("working-time".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                    text_working_time.setText(nod.getTextContent()); 
                    }
        // условие для фида rubric-id
                if ("rubric-id".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                    text_rubric_id.setText(nod.getTextContent()); 
                    }
                if ("url".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                    text_url.setText(nod.getTextContent()); 
                    }
                if ("actualization-date".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                    text_actualization_date.setText(nod.getTextContent()); 
                    }

            }
        }

                }    
            });
    
    // добавляем слушателя на кнопку добавить
            addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xmlobj.writeLog(xml);
                // Получаем корневой элемент
                Node root = xml.getDocumentElement();
                // Создаем по элементам
                Element company = xml.createElement("company");
                Element company_id= xml.createElement("company-id");
                Element name = xml.createElement("name");
                Element address = xml.createElement("address");
                Element country = xml.createElement("country");
                Element coordinates = xml.createElement("coordinates");
                Element lat = xml.createElement("lat");
                Element lon = xml.createElement("lon");
                Element phone = xml.createElement("phone");
                Element number = xml.createElement("number");
                Element type = xml.createElement("type");
                
                
                Element url = xml.createElement("url");
                Element working_time  = xml.createElement("working-time");        
                Element rubric_id  = xml.createElement("rubric-id");        
                rubric_id.setTextContent("184105402");
                Element feature_boolean = xml.createElement("feature-boolean");

        
        
                // Устанавливаем значение текста внутри тега
                company_id.setTextContent(text_id.getText());                
                name.setTextContent(text_name.getText());
                name.setAttribute("lang", "ru");
                address.setTextContent(text_adress.getText());
                address.setAttribute("lang", "ru");
                country.setTextContent(text_country.getText());
                country.setAttribute("lang", "ru");
                lat.setTextContent(text_lat.getText());
                lon.setTextContent(text_lon.getText());
                
                number.setTextContent(text_phone_number.getText());
                type.setTextContent(text_phone_type.getText());
                // добавляем условие на дату, если она активна - добавляем из окошка,
                // если нет, добавляем по умолчанию
                Element actualization_date = null;
                if (text_actualization_date.isEnabled()== false) {
                actualization_date  = xml.createElement("actualization-date");        
                actualization_date.setTextContent(df.format(d).toString());
                }
                if (text_actualization_date.isEnabled()== true)
                {
                actualization_date  = xml.createElement("actualization-date"); 
                actualization_date.setTextContent(text_actualization_date.getText());       
                }
                
                url.setTextContent(text_url.getText());
                working_time.setTextContent(text_working_time.getText());
                working_time.setAttribute("lang", "ru");
                rubric_id.setTextContent(text_rubric_id.getText());
                feature_boolean.setAttribute("name", "accepts_card_mir");
                feature_boolean.setAttribute("value", "1");

                // Добавляем внутренние элементы 
                company.appendChild(company_id);
                company.appendChild(name);
                company.appendChild(address);
                company.appendChild(country);
                //Если забить нулевое значение в широту и долготу, фид с координатами не добавляется
                if (!lat.getTextContent().equals("") || !lon.getTextContent().equals("")) {
                company.appendChild(coordinates);
                coordinates.appendChild(lat);
                coordinates.appendChild(lon);
                }
                company.appendChild(phone);
                phone.appendChild(number);
                phone.appendChild(type);
                company.appendChild(actualization_date);
                company.appendChild(url);
                company.appendChild(working_time);
                company.appendChild(rubric_id);
                company.appendChild(feature_boolean);
                        // Добавляем в корневой элемент
                root.appendChild(company);
                        // Записываем XML в файл
                xmlobj.writeDocument(xml);
                
                xml = xmlobj.ParseXml();
                listModel.clear();
                    
                // обновляем список в листе
                for (int i=0;i < xml.getElementsByTagName("company").getLength(); i++ )
                {
                    
                    Node company_new = xml.getElementsByTagName("company").item(i);    
                    NodeList list = company_new.getChildNodes(); 
                            for (int j = 0; j<list.getLength();j++) {
                            Node nod = list.item(j);
                            
                            if ("company-id".equals(nod.getNodeName())) {
                            listModel.addElement((i+1) + " " + nod.getTextContent());
                            }
            
                            }      
                  
                    
                }
                // выделяем последний элемент списка
                int index = listModel.size() - 1;
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        });
        // добавляем слушателя на кнопку очистить
            cleareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            text_id.setText(null);
            text_name.setText(null);
            text_adress.setText(null);
            text_country.setText(null);
            text_working_time.setText(null);
            text_rubric_id.setText(null);
            text_lon.setText(null);
            text_lat.setText(null);
            text_phone_number.setText(null);
            text_phone_type.setText(null);
            text_url.setText(null);
            text_actualization_date.setText(null);
            chekbox_data.setSelected(false);
            }
        });
            
        // добавляем слушателя на кнопку удалить
            updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            xmlobj.writeLog(xml);
            for (i = 0; i < xml.getElementsByTagName("company").getLength(); i++) {
            Node company = xml.getElementsByTagName("company").item(list.getSelectedIndex());    
            NodeList list_child_company = company.getChildNodes();
    //  создаем цикл, для перечисления всех подфидов фида coordinates company
            for (int j = 0; j<list_child_company.getLength();j++) {
                Node nod = list_child_company.item(j);
                        if ("company-id".equals(nod.getNodeName()))
                {
                       nod.setTextContent(text_id.getText());
                }
                        if ("name".equals(nod.getNodeName()))
                {
                       nod.setTextContent(text_name.getText());
                }
                        if ("address".equals(nod.getNodeName()))
                {
                       nod.setTextContent(text_adress.getText());
                }
                        if ("country".equals(nod.getNodeName()))
                {
                       nod.setTextContent(text_country.getText());
                }
                        if ("working-time".equals(nod.getNodeName()))
                {
                       nod.setTextContent(text_working_time.getText());
                }
                        if ("rubric-id".equals(nod.getNodeName()))
                {
                       nod.setTextContent(text_rubric_id.getText());
                }
                        if ("adress".equals(nod.getNodeName()))
                {
                       nod.setTextContent(text_adress.getText());
                }
                        if ("actualization-date".equals(nod.getNodeName()))
                {
                       nod.setTextContent(text_actualization_date.getText());
                }
                        if ("url".equals(nod.getNodeName()))
                {
                       nod.setTextContent(text_url.getText());
                }

                if ("coordinates".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                        NodeList list_child_coordinates = nod.getChildNodes();
    // создаем цикл, для перечисления всех подфидов фида coordinates
                        for (int l = 0; l<list_child_coordinates.getLength(); l++)
                        {
                        Node nod2 = list_child_coordinates.item(l);
                            if ("lon".equals(nod2.getNodeName()))
                            {
                            nod2.setTextContent(text_lon.getText());
                            }
                            if ("lat".equals(nod2.getNodeName()))
                            {
                            nod2.setTextContent(text_lat.getText());
                            }
                            
                        }
                    }
//     условие для фида phone           
                if ("phone".equals(nod.getNodeName()) && list.getSelectedIndex()== i)
                    {
                        NodeList list_child_phone = nod.getChildNodes();
    // создаем цикл, для перечисления всех подфидов фида coordinates
                        for (int l = 0; l<list_child_phone.getLength(); l++)
                        {
                        Node phone = list_child_phone.item(l);
                            if ("number".equals(phone.getNodeName()))
                            {
                            phone.setTextContent(text_phone_number.getText());
                            }
                            if ("type".equals(phone.getNodeName()))
                            {
                            phone.setTextContent(text_phone_type.getText());
                            }
                            
                        }
                    }

            }
                        
            }
            chekbox_data.setSelected(false);    
            xmlobj.writeDocument(xml);
            
            }
        });

            // кнопка удалить
            deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xmlobj.writeLog(xml);
//                xml.getParentNode().removeChild(xml.getElementsByTagName("company").item(3));
                int JAJA =   list.getSelectedIndex(); 
                listModel.clear();
                xml = xmlobj.ParseXml();
                Node root = xml.getDocumentElement();
                Node child = xml.getElementsByTagName("company").item(JAJA); 
                root.removeChild(child);
                xmlobj.writeDocument(xml);
                for (int i=0;i < xml.getElementsByTagName("company").getLength(); i++ )
                {
                    
                    Node company_new = xml.getElementsByTagName("company").item(i);    
                    NodeList list = company_new.getChildNodes(); 
                            for (int j = 0; j<list.getLength();j++) {
                            Node nod = list.item(j);
                            
                            if ("company-id".equals(nod.getNodeName())) {
                            listModel.addElement((i+1) + " " + nod.getTextContent());
                            }
            
                            }      
                  
                    
                }  
            }
        });
            
            sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              path = "***";
              host = "****";
              port = **;
              user = "*****";
              password = "******";
        try
        {
              session = jsch.getSession(user, host, port);
              testjjj.MyUserInfo ui = new testjjj.MyUserInfo();
              ui.setPassword(password);
              session.setUserInfo(ui);
              session.connect();
              Channel c = session.openChannel("sftp");
              c.connect();
              channel = (ChannelSftp) c;
                  try {
                      channel.cd(path);
                  } catch (SftpException ex) {
                      Logger.getLogger(SimpleWindow.class.getName()).log(Level.SEVERE, null, ex);
                  }
              
              //SftpProgressMonitor monitor = new MyProgressMonitor();
              int mode=ChannelSftp.OVERWRITE;
                  try {
                      channel.put("embmaps.xml", "embmaps.xml", monitor, mode);
                  } catch (SftpException ex) {
                      Logger.getLogger(SimpleWindow.class.getName()).log(Level.SEVERE, null, ex);
                  }
               
               
        if (channel != null)
        {
            channel.disconnect();
        }
        if (session != null)
        {
            session.disconnect();
        }
               
        }
        catch (JSchException ex)
        {
         
        }


            }
        });
            
      // Добавляем слушателя на чек лист
         chekbox_data.addItemListener(new ItemListener() {
             public void itemStateChanged(ItemEvent e) {
      // проверяем статус текстового поля, и изменяем его
                 if (text_actualization_date.isEnabled()==true)
                 text_actualization_date.setEnabled(false);
                 else
                 text_actualization_date.setEnabled(true);    
             }
             
         });
            

         
         

    

    
    



    setContentPane(panel);
    setSize(800, 800);
    }
}
