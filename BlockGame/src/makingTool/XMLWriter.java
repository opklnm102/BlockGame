package makingTool;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLWriter {
	private Document XMLDoc;
	
	public XMLWriter(String filePath){
		
		try{
		XMLDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
	
		Node root = XMLDoc.createElement("BlockGame");
		XMLDoc.appendChild(root);
		    {
		        Element people1 = XMLDoc.createElement("employee");
		        people1.setAttribute("id", "1");
		        people1.setAttribute("part", "devlopment");
		        root.appendChild(people1);
		        {
		            Element name = XMLDoc.createElement("name");
		            name.appendChild(XMLDoc.createTextNode("Gildong Hong"));
		            people1.appendChild(name);
		        }
		        {
		            Element age = XMLDoc.createElement("age");
		            age.appendChild(XMLDoc.createTextNode("25"));
		            people1.appendChild(age);
		        }
		    }
		 
		// Document 저장
		DOMSource xmlDOM = new DOMSource(XMLDoc);
		StreamResult xmlFile = new StreamResult(new File(filePath));
		TransformerFactory.newInstance().newTransformer().transform(xmlDOM, xmlFile);
		 	
		}catch(Exception e){
			System.out.println(e + "DOM 파서 생성 실패");
		}
	}
}
