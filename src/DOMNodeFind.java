import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class DOMNodeFind {
  private static String uri;
  private static String nodetype;
  private static String nodename;
  private static String context;
  private static boolean setValidation    = false; 
  private static Node selected_node=null;
  private static int height;
  private static int cnt=0;
  Document document;
  
  public DOMNodeFind(String path,String findNode,Document doc1,String type) {
	 this.context="";
	 this.uri=path;
	 this.document=doc1;
	 
     Document doc = doc1;
     if(type!=null)
    	 traverse(doc.getDocumentElement(), findNode, false,"");
//      nodeFind(doc.getDocumentElement(), findNode);
    
 }
  public String getData() {
	  return this.context;
  }
  public int getheight() {
	  return this.height;
  }
  public Node getNode() {
	  return this.selected_node;
  }
  public int getcnt() {
	  return this.cnt;
  }
  public void setcnt() {
	  this.cnt=0;
  }
  public void traverse (Node node, String eleName,Boolean get, String indent ) {
	    if ( node == null )
	      return;
	    
	    int type = node.getNodeType();
	    switch (type) {
	    case Node.DOCUMENT_NODE:
	    	if((node.getNodeName().equals(eleName))) {
	      this.context=this.context+"[Document]";
	      this.context=this.context+node.getNodeName();
	    	this.context=this.context+"\n";}
	      break;
	        		
	    case Node.ENTITY_NODE:
	    	if((node.getNodeName().equals(eleName))) {
	      this.context=this.context+"[ENTITY]";
	      this.context=this.context+node.getNodeName();
	    	this.context=this.context+"\n";}
	      break;
	        
	    case Node.ELEMENT_NODE:
	    	if((node.getNodeName().equals(eleName))||get) {
	      this.context=this.context+"[Element]";
	      this.context=this.context+node.getNodeName();  	
//	      this.context=this.context+"\n";
	      get=true; 
	    	}
	      
	      if (node.hasAttributes()) {
	    	  NamedNodeMap attr = node.getAttributes();    	
	    	  for (int i = 0; i < attr.getLength(); i++) {
	    		  if(get||(attr.item(i).getNodeName().equals(eleName))
	    				  ||(attr.item(i).getNodeValue().equals(eleName))) {
	    		  
	    		  this.context=this.context+"[Element]";
	    		  this.context=this.context+node.getNodeName();  	
	    		  this.context=this.context+" "+ indent + "[Attribute] "
	    		    	  + attr.item(i).getNodeName() + "=" +
	    		    	  attr.item(i).getNodeValue();
	    		  this.context=this.context+"\n";
		    	  get=true; 
	    		  }
	    	  }
 
	      }
	      
	      break;

	    case Node.ENTITY_REFERENCE_NODE:
	    	if((node.getNodeName().equals(eleName))) {
	        this.context=this.context+"[ENTITY_REFERENCE]";
	        this.context=this.context+node.getNodeName();	
	        this.context=this.context+"\n";}
	      break;

	    case Node.CDATA_SECTION_NODE:
	    	if((node.getNodeValue().equals(eleName))) {
	        this.context=this.context+"[CDATA_SECTION]";
	        this.context=this.context+node.getNodeName();
	        this.context=this.context+"  " + node.getNodeValue();
	        this.context=this.context+"\n";}
	        
	        break;

	    case Node.COMMENT_NODE:
	    	if((node.getNodeValue().equals(eleName))) {
	        this.context=this.context+"[COMMENT]";
	        this.context=this.context+node.getNodeName();
	        this.context=this.context+"  " + node.getNodeValue();
	        this.context=this.context+"\n";}

	        break;

	    case Node.TEXT_NODE:
	    	if((node.getNodeValue().equals(eleName))||get) {
	        this.context=this.context+"[TEXT]";
	        this.context=this.context+node.getNodeName();
	        this.context=this.context+"  " + node.getNodeValue();
	        if(node.getNodeValue()!=null)
	        	this.context=this.context+"\n";
	        get=true;
	    	}
	        

	        break;
	    }

	    NodeList children = node.getChildNodes();
	    if (children != null) {
	      int len = children.getLength();
	      for (int i = 0; i < len; i++)
	        traverse(children.item(i),eleName,get, indent + "   ");
	    }
	  }
  

  public void nodeFind (Node node, String eleName) {
    if ( node == null)  return;

    NodeList children = node.getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);

      if (child.getNodeName().equals(eleName)) {
//        System.out.print("[" + getDepth(child) + ", ");
//        System.out.print(getSiblingIndex(child) +"] ");
        System.out.println(child.getNodeName());
        this.selected_node=child;
        this.height=getDepth(child);
        this.cnt=this.cnt+1;
      }

      nodeFind(child, eleName);
    }
  } // print(Node)
  
  public String find_id(Node node){
	  String id="-1";
	  while(node.getParentNode()!=null) {
		  NamedNodeMap attr = node.getAttributes(); 
    	  for (int j = 0; j < attr.getLength(); j++) {
    		  if((attr.item(j).getNodeName().equals("id"))){
    			  id=attr.item(j).getNodeValue();
    			  break;
    		  }}
    	  node=node.getParentNode();
	  }
	  
	  return id;
  }
  public void IDnodeFind (Node node, String eleName,String id) {
	  if ( node == null)  return;

	    NodeList children = node.getChildNodes();
	    for (int i = 0; i < children.getLength(); i++) {
	      Node child = children.item(i);

	      if (child.getNodeName().equals(eleName)) {
	    	  System.out.print(child.getNodeName());
	    	  System.out.println(find_id(child));
	    	  if(find_id(child).equals(id)) {
	    		  System.out.println("Selected");
			        this.selected_node=child;
			        this.height=getDepth(child);
	    	  }
	      }
	      IDnodeFind(child, eleName,id);
	      }

	    }// print(Node)
  
  // Calculate  the depth of the current element.
  public int getDepth(Node node) {
    int index = 0;
    while ((node = node.getParentNode()) != null)  index++;
    return index;
  }
		
  // Calculate the element index for each depth (starting from 1) -----//
  protected int getSiblingIndex(Node node) {
    int index=1;
		
    while( (node = node.getPreviousSibling()) != null) 
    if(node.getNodeType() != Node.TEXT_NODE		
          && node.getNodeType() != Node.COMMENT_NODE)
      index++;
 
    return index;		
  }

//  public static void main(String argv[]) {
//    if ( argv.length == 0 || argv.length > 2) {
//      System.err.println("Usage: java DOMNodeFind node_name uri ...");
//      System.exit(1);
//    }
//			
//    String findNode = argv[0];
//    uri = argv[1];
//			
//    DOMNodeFind dt = new DOMNodeFind(findNode);
//  } // main(String[])
}
