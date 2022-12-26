import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;


public class data_window extends JDialog implements ActionListener {

	private JButton close = new JButton("Close");
	Document document;
	Node selected_node;
	int node_height;
	private String[] names;
	
	private JComboBox elem_name;
	private JLabel selected_elem = new JLabel(" ");
	private JButton node_submit = new JButton("submit");
	
	private String[] types= {"element","attribute","text","comment"};
	private String[] types2= {"element","att name","att value","text","comment"};
	
	private JComboBox type = new JComboBox(types);
	private JButton insert =new JButton("insert");
	private JTextField make_name = new JTextField();
	
	private JComboBox type2 = new JComboBox(types2);
	private JButton update = new JButton("update");
	private JTextField update_name = new JTextField();
	
	private JComboBox type3 = new JComboBox(types);
	private JButton delete =new JButton("delete");

	
	public data_window(Document doc) {
		
		this.document=doc;
		this.init();
		this.start();
		//this.setSize(600, 700);
		setBounds(0,0,600,700);
		setVisible(true);
		
	}
	public Document get_doc() {
		return this.document;
	}
	
	public void init() {
		DOMTraverse tr = new DOMTraverse(null,this.document);
		names=tr.getElem();
		
        LinkedHashSet<String> linkedHashSet = 
                new LinkedHashSet<>(Arrays.asList(names));
 
        names= linkedHashSet.toArray(new String[0]);
		elem_name = new JComboBox(names);
		this.setLayout(null);
		
		close.setBounds(500,30,80,30);
		
		elem_name.setBounds(20,30,220,30);
		node_submit.setBounds(240,30,80,30);
		selected_elem.setBounds(20,70,220,30);
		type.setBounds(20,100,80,30);
		insert.setBounds(180,100,80,30);
		make_name.setBounds(100,100,80,30);
		
		type2.setBounds(20,300,80,30);
		update.setBounds(180,300,80,30);
		update_name.setBounds(100,300,80,30);
		
		type3.setBounds(20,500,80,30);
		delete.setBounds(180,500,80,30);

		
		add(elem_name);
		add(close);
		add(node_submit);
		add(selected_elem);
		add(type);
		add(insert);
		add(make_name);
		
		add(type2);
		add(update);
		add(update_name);
		
		add(type3);
		add(delete);

		
	}
	public void start() {
		close.addActionListener(this);
		node_submit.addActionListener(this);
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
	}
	public void actionPerformed(ActionEvent arg0) {
		 if(arg0.getSource()==close) {//close
				dispose();
			}
		 else if(arg0.getSource()==node_submit) {//submit
			 
			 String node_name=elem_name.getSelectedItem().toString();
			 String id="-1";
			 DOMNodeFind node1=new DOMNodeFind(null,node_name,this.document,null);
			 node1.nodeFind(this.document,node_name);
			 System.out.println(node1.getNode());
			 String check_id=node1.find_id(node1.getNode());
//			 if(node_name!="travel"&&node_name!="user_table"&&node_name!="guide_table"&&
//				node_name!="info:travel_product_info"&&node_name!="info_:place_info") 
			 if(check_id!="-1")
			 {
		       id = JOptionPane.showInputDialog("Input ID ");
		       if(id != null && id.matches("[0-9.]+")) {
		       	System.out.println(id);
		       }
		       else
		    	 System.out.println("NOPE");
			 }
			 
			 DOMNodeFind nf = new DOMNodeFind(null,node_name,this.document,null);

			 if(id.equals("-1")) {
				 System.out.println("NODEFIND");
				 nf.nodeFind(this.document,node_name);
				 id="";
				 }
			 else {
				 System.out.println("NODEFINDWITHID");
				 nf.IDnodeFind(this.document,node_name,id);

				 
			 }
				
			 if(nf.getNode()!=null) {
			 this.selected_node=nf.getNode();
			 selected_elem.setText(node_name+" "+id);
			 }
			 else {
				 JOptionPane.showMessageDialog(null, "Can't find Element", "No Element", JOptionPane.WARNING_MESSAGE);
					
			 }
			
			}
		 else if(arg0.getSource()==insert){
			 try {
			System.out.println(selected_elem.getText());
			 System.out.println(type2.getSelectedItem());
			 System.out.println(make_name.getText());
			 insert(make_name.getText(),this.selected_node,type.getSelectedItem().toString());
			 }
			 catch(Exception e){
				 System.out.println(e);
				 JOptionPane.showMessageDialog(null, "Fail to insert", "No Element", JOptionPane.WARNING_MESSAGE);
			 }
		 }
		 else if(arg0.getSource()==update) {
			 try {
				System.out.println(selected_elem.getText());
				 System.out.println(type2.getSelectedItem());
				 System.out.println(update_name.getText());
				 update(update_name.getText(),this.selected_node,type2.getSelectedItem().toString());
			 }
			 catch(Exception e){
				 System.out.println(e);
				 JOptionPane.showMessageDialog(null, "Fail to update", "No Node", JOptionPane.WARNING_MESSAGE);
			 }
			 
		 }
		 else if(arg0.getSource()==delete) {
			try {
			 	System.out.println(selected_elem.getText());
				System.out.println(type3.getSelectedItem());
				delete(selected_node,type3.getSelectedItem().toString());
			}
			catch(Exception e){
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Fail to delete", "No Element", JOptionPane.WARNING_MESSAGE);
			 }
		 }

	}
	public void delete(Node selected_node,String type){
//		 Document doc=this.document;		 
		 Node node = selected_node.getParentNode();
		 //&&selected_node.getParentNode()!=this.document.getDocumentElement()
		if(type.equals("element")) {
			System.out.println(selected_node.getParentNode());
			System.out.println(selected_node);
			System.out.println(selected_node.getNextSibling());
			node.removeChild(selected_node.getNextSibling());
			node.removeChild(selected_node);
		}
		
		else if(type.equals("attribute")) {
			if(selected_node.hasAttributes()) {
			String value = JOptionPane.showInputDialog("Input Attribute Name ");
			int i,len;  
			if(value!=null) {
				  NamedNodeMap attr = selected_node.getAttributes(); 
				  len=attr.getLength();
		    	  for (i = 0; i < attr.getLength(); i++) {
		  
		    		  if((attr.item(i).getNodeName().equals(value))) {
		    			  ((Element) selected_node).removeAttributeNode((Attr)(attr.item(i)));
		    			  break;
		    		  }}
		    	  if(i==len)
		    		  JOptionPane.showMessageDialog(null, "No such attribute", "No Attribute", JOptionPane.WARNING_MESSAGE);
			  }
			  else
				  JOptionPane.showMessageDialog(null, "Empty attribute", "No Attribute", JOptionPane.WARNING_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "This node doesn't have an attribute", "No Attribute", JOptionPane.WARNING_MESSAGE);
			
		}
		else if(type.equals("text")){
			  Document doc=this.document;		 
			  node=selected_node;
			  NodeList children = node.getChildNodes();
			  int i,len=children.getLength();
			  for (i = 0; i < children.getLength(); i++) {
			      Node child = children.item(i);
			      if(child.getNodeType()==Node.TEXT_NODE) {
			    	  if(child.getNodeValue().replaceAll(" ", "").matches("^[a-zA-Z0-9]*$")) {
			    		  System.out.println(child.getNodeValue());
			    		  node.removeChild(child);
			    	  	  break;
			    	  	 }
			      }
			      else
			    	  continue;
			  }
			  if(i==len)
				  JOptionPane.showMessageDialog(null, "This node doesn't have an Text", "No Text", JOptionPane.WARNING_MESSAGE);
			  
		}
		else if(type.equals("comment")) {
			  node=selected_node;
			  NodeList children = node.getChildNodes();
			  int i, len=children.getLength();
			  for (i = 0; i < children.getLength(); i++) {
			      Node child = children.item(i);
			      if(child.getNodeType()==Node.COMMENT_NODE) {
			    	  		node.removeChild(child);
			    	  		break;
			      }
			      else
			    	  continue;
			  }
			  if(i==children.getLength())
				  JOptionPane.showMessageDialog(null, "This node doesn't have an Comment", "No Comment", JOptionPane.WARNING_MESSAGE);
			  
		
		}

	}
	public void update(String text,Node selected_node,String type){

		String value;
		int i;
		if(type.equals("element")) {
			this.document.renameNode(selected_node,selected_node.getNamespaceURI(),text);
		}
		else if(type.equals("att name")) {
		      if (selected_node.hasAttributes()) {
		    	  
		    	  NamedNodeMap attr = selected_node.getAttributes();    	
		    	  for (i = 0; i < attr.getLength(); i++) {
		    		  if((attr.item(i).getNodeName().equals(text))) {
		    			  value = JOptionPane.showInputDialog("Update value with: ");
		    			  this.document.renameNode(attr.item(i),attr.item(i).getNamespaceURI() , value);
		    		  		break;}
		    	  }
		    	  if(i==attr.getLength())
		    		  JOptionPane.showMessageDialog(null, "This node doesn't have attribute with that name", "No Attribute", JOptionPane.WARNING_MESSAGE);
		      }
		      else
		    	  JOptionPane.showMessageDialog(null, "This node doesn't have an attribute", "No Attribute", JOptionPane.WARNING_MESSAGE);
		    	  
		}
		else if(type.equals("att value")) {
			if (selected_node.hasAttributes()) {
		    	  
		    	  NamedNodeMap attr = selected_node.getAttributes();    	
		    	  for (i = 0; i < attr.getLength(); i++) {
		    		  if((attr.item(i).getNodeName().equals(text))) {
		    			  value = JOptionPane.showInputDialog("Update value with: ");
		    			  attr.item(i).setNodeValue(value);
		    		  		break;}
		    	  }
		    	  if(i==attr.getLength())
		    		  JOptionPane.showMessageDialog(null, "This node doesn't have attribute with that name", "No Attribute", JOptionPane.WARNING_MESSAGE);
		      }
		      else
		    	  JOptionPane.showMessageDialog(null, "This node doesn't have an attribute", "No Attribute", JOptionPane.WARNING_MESSAGE);
		    
			}
		else if(type.equals("text")) {
			  Document doc=this.document;		 
			  Node node = doc.getDocumentElement();
			  node=selected_node;
			  NodeList children = node.getChildNodes();

			  for (i = 0; i < children.getLength(); i++) {
			      Node child = children.item(i);
			      if(child.getNodeType()==Node.TEXT_NODE) {
			    	  if(child.getNodeValue().replaceAll(" ", "").matches("^[a-zA-Z0-9]*$")) {
			    		  System.out.println(child.getNodeValue());
			    	  	  child.setNodeValue(text);
			    	  	  break;
			    	  	 }
			      }
			      else
			    	  continue;
			  }
			  if(i==children.getLength())
				  JOptionPane.showMessageDialog(null, "This node doesn't have an Text", "No Text", JOptionPane.WARNING_MESSAGE);
			  		
		}
		else if(type.equals("comment")) {
			  Document doc=this.document;		 
			  Node node = doc.getDocumentElement();
			  node=selected_node;
			  NodeList children = node.getChildNodes();

			  for (i = 0; i < children.getLength(); i++) {
			      Node child = children.item(i);
			      if(child.getNodeType()==Node.COMMENT_NODE) {
			    	  		child.setNodeValue(text);
			    	  		break;
			      }
			      else
			    	  continue;
			  }
			  if(i==children.getLength())
				  JOptionPane.showMessageDialog(null, "This node doesn't have an Comment", "No Comment", JOptionPane.WARNING_MESSAGE);
			  
		
		}
		
	
	}
	
	
	public void insert(String text,Node selected_node,String type){

		  Document doc=this.document;
		 
		  Node node = doc.getDocumentElement();
		  node=selected_node;
		  System.out.println(node);
		  NodeList children = node.getChildNodes();
		  System.out.println(children);

		  if(type.equals("element")) {
			 Element item = doc.createElement(text);  
			 
		  try {
		  if(text.equals("user")||text.equals("guide")
		||text.contains("travel_product")&&!text.contains("info")
		||text.contains("place")&&!text.contains("info")
				  ){
			  DOMNodeFind nf_cnt= new DOMNodeFind(null,text,this.document,null);
			  nf_cnt.setcnt();
			  nf_cnt.nodeFind(this.document, text);
			  int cnt=nf_cnt.getcnt()+1;
			  Attr attrItem = doc.createAttribute("id");
			  String length=Integer.toString(cnt);
			  attrItem.setValue(length);

			  item.setAttributeNode(attrItem);
		  }
		  node.insertBefore(doc.createTextNode("\n"), children.item(children.getLength()-1));
		  node.insertBefore(item,children.item(children.getLength()-1));
		  
		  }catch(Exception e) {
			  node.appendChild(doc.createTextNode("\n"));
			  node.appendChild(item);
		  }}
		  else if(type.equals("text")) {
			  Text item = doc.createTextNode(text);
			  try {

				  node.insertBefore(doc.createTextNode("\n"), children.item(children.getLength()-1));
				  node.insertBefore(item,children.item(children.getLength()-1));
				  }catch(Exception e) {
					  node.appendChild(doc.createTextNode("\n"));
					  node.appendChild(item);
				  }
		  }
		  else if(type.equals("attribute")){
		
			  String value = JOptionPane.showInputDialog("Input Value ");
			  if(value!=null) {
			  Attr attrItem = doc.createAttribute(text);
			  attrItem.setValue(value);
			  Element e=(Element) node;
			  e.setAttributeNode(attrItem);
			  
			  }
			  else {
				  JOptionPane.showMessageDialog(null, "Input Value", "No Value", JOptionPane.WARNING_MESSAGE);
			  }
		  }
		  else if(type.equals("comment")) {
//			  Text item = doc.createTextNode(text);
			  Comment item=doc.createComment(text);
			  try {

				  node.insertBefore(doc.createTextNode("\n"), children.item(children.getLength()-1));
				  node.insertBefore(item,children.item(children.getLength()-1));
				  }catch(Exception e) {
					  node.appendChild(doc.createTextNode("\n"));
					  node.appendChild(item);
				  }
		  }


	   
	}
}
