import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class gui_project221126 {

	public static void main(String[] args) {

    	Main ex = new Main();
    //	jframe_1 bar = new  jframe_1();
	}

}

class Main extends JFrame implements ActionListener{ 
	
private JFileChooser fileComponent = new JFileChooser();
private JButton btnOpen = new JButton("Load");
private JButton btnSave = new JButton("Save");
private JButton close = new JButton("Close");
private JLabel labelOpen = new JLabel(" ");
private JLabel labelSave = new JLabel(" ");

private JButton btnPrint = new JButton("Print");

private JLabel find= new JLabel("Find");
private String[] types= {"element","attribute","text","comment"};
private JComboBox type = new JComboBox(types);


private JTextField node_search = new JTextField();
private JButton node_submit = new JButton("Find");

private JButton insert = new JButton("insert/update/delete");

private JButton make = new JButton("make");

private JLabel xml_content= new JLabel(" ");
private JTextArea ta = new JTextArea();
JPanel jp_label, xml_find;
JScrollPane scroll,scroll2;
JLabel label;
JButton btn ;
int count =1;
GridBagLayout Gbag = new GridBagLayout();
GridBagConstraints gbc1;

private String xml_path,xsd_path,tmp;
private JTextArea finds = new JTextArea();

Document doc;



public Main(){
this.init();
this.start();
this.setSize(600, 700);
this.setVisible(true);

}

public void init(){

	
	  jp_label = new JPanel();
	  jp_label.setLayout(Gbag);  
	  jp_label.setBackground(Color.white);

	  label = new JLabel("",SwingConstants.RIGHT);
	  create_form(label, 0,0,30,10);

	  scroll = new JScrollPane(jp_label); 
	  scroll.setBounds(20,300,540,300);   

getContentPane().setLayout(null);

btnOpen.setBounds(20,30,80,30);
make.setBounds(100,30,80,30);
btnSave.setBounds(180,30,80,30);

close.setBounds(300,30,80,30);
labelOpen.setBounds(20,60,800,50);

btnPrint.setBounds(20,110,80,30);

find.setBounds(20,160,80,30);
type.setBounds(20,190,80,30);
node_search.setBounds(100,190,80,30);
node_submit.setBounds(180,190,80,30);


insert.setBounds(20,230,240,30);

add(type);
add(node_submit);
add(node_search);
add(btnPrint);
add(ta);
add(make);
add(close);
add(btnOpen);
add(btnSave);
add(labelOpen);
add(labelSave);
add(scroll);
add(find);
add(insert);
}

public void create_form(Component cmpt, int x, int y, int w, int h){

	  GridBagConstraints gbc = new GridBagConstraints();
	  gbc.fill = GridBagConstraints.BOTH;
	  gbc.gridx = x;
	  gbc.gridy = y;
	  gbc.gridwidth = w;
	  gbc.gridheight = h;
	  this.Gbag.setConstraints(cmpt, gbc);
	  jp_label.add(cmpt);
	  jp_label.updateUI();

	}


public void start(){
btnOpen.addActionListener(this);
make.addActionListener(this);
btnSave.addActionListener(this);
btnPrint.addActionListener(this);
node_submit.addActionListener(this);
close.addActionListener(this);
insert.addActionListener(this);
fileComponent.setCurrentDirectory(null);
fileComponent.setFileFilter(new FileNameExtensionFilter("xml/xsd files (*.xml,*.xsd)", "xml","xsd"));

fileComponent.setMultiSelectionEnabled(false); // Mulit-file select x
}


public void actionPerformed(ActionEvent arg0) {
	
if(arg0.getSource() == btnOpen){//load
xml_path=null;
xsd_path=null;
if(fileComponent.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
labelOpen.setText("Path : " + fileComponent.getSelectedFile().toString());
tmp=fileComponent.getSelectedFile().toString();
if((tmp.charAt(tmp.length()-1))=='d') {
	xsd_path=tmp;
}
else {xml_path=tmp;}

if(fileComponent.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
	tmp=fileComponent.getSelectedFile().toString();
}
if((tmp.charAt(tmp.length()-1))=='d') {
	xsd_path=tmp;
}
else {xml_path=tmp;}

this.xml_path=xml_path;
this.xsd_path=xsd_path;
System.out.println(this.xml_path+this.xsd_path);
if(this.xml_path!=null&&this.xsd_path!=null) {
	System.out.println("Validation...");
	ValidationCheck_XSD vc = new ValidationCheck_XSD(xml_path,xsd_path);
}
else if(this.xml_path!=null) {
	ValidationCheck_DTD vt=new ValidationCheck_DTD(xml_path,true);
}
try {
    DocumentBuilderFactory factory = 
        DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    
    doc = builder.parse(xml_path);
  } catch (FactoryConfigurationError e) {
    // unable to get a document builder factory
  	e.printStackTrace(System.err);
  } catch (ParserConfigurationException e) {
    // parser was unable to be configured
  	e.printStackTrace(System.err);
  } catch (SAXException e) {
    // parsing error
  	e.printStackTrace(System.err);
  } catch (IOException e) {
    // i/o error
  	e.printStackTrace(System.err);
  }

}}


else if(arg0.getSource() == btnSave){//save
if(xml_path==null) {
		JOptionPane.showMessageDialog(null, "Load XML File", "No XML File", JOptionPane.WARNING_MESSAGE);
	}

else{
if(fileComponent.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){ 
try {
System.out.print("Path : " + fileComponent.getSelectedFile().toString());
String uri =fileComponent.getSelectedFile().toString();


DOMImplementationRegistry registry= DOMImplementationRegistry.newInstance(); 
DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
LSSerializer serializer = impl.createLSSerializer();
serializer.getDomConfig()
.setParameter("format-pretty-print", true);
LSOutput output = impl.createLSOutput();
FileOutputStream fos = new FileOutputStream(uri);
output.setByteStream(fos);
serializer.write(doc, output);
fos.close();}

 catch (FactoryConfigurationError e) {
	// unable to get a document builder factory
	e.printStackTrace(System.err);
	} catch (IOException e) {
	// i/o error
	e.printStackTrace(System.err);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassCastException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
}
}
else if(arg0.getSource()==btnPrint) {//print
	if(xml_path==null) {
		JOptionPane.showMessageDialog(null, "Load XML File", "No XML File", JOptionPane.WARNING_MESSAGE);
	}
	else {
	ta.selectAll();
	ta.replaceSelection("");
	DOMTraverse dt = new DOMTraverse(this.xml_path,doc);
	ta.append(dt.getData());
	ta.setCaretPosition(ta.getDocument().getLength());
	jp_label.add(ta);
//	System.out.println(dt.getDoc().getChildNodes().item(0));
	//label.setText("<html>"+dt.getData()+"</html>");
}}
else if(arg0.getSource()==node_submit) {//find
	if(xml_path==null) {
		JOptionPane.showMessageDialog(null, "Load XML File", "No XML File", JOptionPane.WARNING_MESSAGE);
	}
	else {
	ta.selectAll();
	ta.replaceSelection("");
	System.out.println(type.getSelectedItem().toString());
	
	DOMNodeFind dt = new DOMNodeFind(this.xml_path,node_search.getText(),doc,type.getSelectedItem().toString());
	ta.append(dt.getData());
	jp_label.add(ta);
	}
}
else if(arg0.getSource()==close) {//close
	if(xml_path!=null) {
		if(fileComponent.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){ 
			try {
			System.out.print("Path : " + fileComponent.getSelectedFile().toString());
			String uri =fileComponent.getSelectedFile().toString();


			DOMImplementationRegistry registry= DOMImplementationRegistry.newInstance(); 
			DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
			LSSerializer serializer = impl.createLSSerializer();
			serializer.getDomConfig()
			.setParameter("format-pretty-print", true);
			LSOutput output = impl.createLSOutput();
			FileOutputStream fos = new FileOutputStream(uri);
			output.setByteStream(fos);
			serializer.write(doc, output);
			fos.close();}

			 catch (FactoryConfigurationError e) {
				// unable to get a document builder factory
				e.printStackTrace(System.err);
				} catch (IOException e) {
				// i/o error
				e.printStackTrace(System.err);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassCastException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
	}
	System.exit(0);
}
else if(arg0.getSource()==insert){
	if(xml_path==null) {
		JOptionPane.showMessageDialog(null, "Load XML File", "No XML File", JOptionPane.WARNING_MESSAGE);
	}
	else {

	data_window dw=new data_window(doc);
	doc=dw.get_doc();
	}
}
else if(arg0.getSource() == make){
	if(xml_path!=null) {
		if(fileComponent.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){ 
			try {
			System.out.print("Path : " + fileComponent.getSelectedFile().toString());
			String uri =fileComponent.getSelectedFile().toString();


			DOMImplementationRegistry registry= DOMImplementationRegistry.newInstance(); 
			DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("XML 3.0 LS 3.0");
			LSSerializer serializer = impl.createLSSerializer();
			serializer.getDomConfig()
			.setParameter("format-pretty-print", true);
			LSOutput output = impl.createLSOutput();
			FileOutputStream fos = new FileOutputStream(uri);
			output.setByteStream(fos);
			serializer.write(doc, output);
			fos.close();}

			 catch (FactoryConfigurationError e) {
				// unable to get a document builder factory
				e.printStackTrace(System.err);
				} catch (IOException e) {
				// i/o error
				e.printStackTrace(System.err);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassCastException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
	}
	
	try {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    String root = JOptionPane.showInputDialog("Input root Name ");
    // root elements
    Document new_doc = docBuilder.newDocument();
    Element rootElement;
    if(root!=null)
    	 rootElement = new_doc.createElement(root);
    else
    	 rootElement = new_doc.createElement("root");
    new_doc.appendChild(rootElement);
    xml_path="/";
    doc=new_doc;
    
	  }
	  catch (FactoryConfigurationError e) {
	      // unable to get a document builder factory
	    	e.printStackTrace(System.err);
	    } catch (ParserConfigurationException e) {
	      // parser was unable to be configured
	    	e.printStackTrace(System.err);
	    } 
}
}
}