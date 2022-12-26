import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

public class DOMTraverse {
  private static boolean setValidation    = false; //defaults
  private static String uri;
  private static String context;
  private String[] arr= {};
  Document document;
  public DOMTraverse(String path,Document doc1) {
	this.uri=path;
	this.context="";
    this.document=doc1;
      Document doc = doc1;
      traverse(doc.getDocumentElement(), "");
//      System.out.print(uri);
    
  }
  public Document getDoc() {
	  return this.document;
  }
  public String getData() {
	  return this.context;
  }
  public String[] getElem(){
	  return this.arr;
  }
  public void traverse (Node node, String indent) {
    if ( node == null )
      return;

    int type = node.getNodeType();
    switch (type) {
    case Node.DOCUMENT_NODE:
      this.context=this.context+"[Document]";
      this.context=this.context+node.getNodeName();
//      System.out.println(indent + "[Document] " + node.getNodeName());
      break;
        		
    case Node.ENTITY_NODE:
      this.context=this.context+"[ENTITY]";
      this.context=this.context+node.getNodeName();
//      System.out.println(indent + "[ENTITY] " + node.getNodeName());
      break;
        
    case Node.ELEMENT_NODE:
      this.context=this.context+"[Element]";
      this.context=this.context+node.getNodeName();  	
      
      String[] newArr = new String[arr.length + 1];
      System.arraycopy(arr, 0, newArr, 0, arr.length);
      newArr[arr.length]=node.getNodeName();
      this.arr=newArr;
//      System.out.println(indent + "[Element] " + node.getNodeName());
      
      if (node.hasAttributes()) {
    	  NamedNodeMap attr = node.getAttributes();
    	  for (int i = 0; i < attr.getLength(); i++) {
    		  this.context=this.context+" " + indent + "[Attribute] "
    		    	  + attr.item(i).getNodeName() + "=" +
    		    	  attr.item(i).getNodeValue();
    		  
//    	  System.out.println(" " + indent + "[Attribute] "
//    	  + attr.item(i).getNodeName() + "=" +
//    	  attr.item(i).getNodeValue());
    	  }
    	  }
      
      break;

    case Node.ENTITY_REFERENCE_NODE:
        this.context=this.context+"[ENTITY_REFERENCE]";
        this.context=this.context+node.getNodeName();	
//      System.out.print(indent + "[ENTITY_REFERENCE] " + node.getNodeName());
      break;

    case Node.CDATA_SECTION_NODE:
        this.context=this.context+"[CDATA_SECTION]";
        this.context=this.context+node.getNodeName();
        this.context=this.context+"  " + node.getNodeValue();
 
        
//        System.out.print(indent + "[CDATA_SECTION] ");
//        System.out.print(node.getNodeName());
//        System.out.println("  " + node.getNodeValue());
        break;

    case Node.COMMENT_NODE:
        this.context=this.context+"[COMMENT]";
//        this.context=this.context+node.getNodeName();
        this.context=this.context+"  " + node.getNodeValue();
 
//        System.out.print(indent + "[COMMENT] ");
//        System.out.print(node.getNodeName());
//        System.out.println("  " + node.getNodeValue());
        break;

    case Node.TEXT_NODE:
        this.context=this.context+"[TEXT]";
//        this.context=this.context+node.getNodeName();
        this.context=this.context+"  " + node.getNodeValue();

        
//        System.out.print(indent + "[TEXT] ");
//        System.out.print(node.getNodeName());
//        System.out.println("  " + node.getNodeValue());
        break;
    }

    NodeList children = node.getChildNodes();
    if (children != null) {
      int len = children.getLength();
      for (int i = 0; i < len; i++)
        traverse(children.item(i), indent + "   ");
    }
  } // print(Node)

//  public static void main(String argv[]) {
//    if ( argv.length == 0) {
//      System.err.println("Usage: java DOMTraverse uri ...");
//      System.exit(1);
//    }
//    if (argv[0].equals("-v")) {
//      setValidation = true;
//      uri = argv[1];
//    } else if (argv[0].equals("-V")) {
//      setValidation = false;
//      uri = argv[1];
//    } else
//      uri = argv[0];
//			
//    DOMTraverse dt = new DOMTraverse();
//  } // main(String[])
}
