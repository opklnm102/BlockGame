package makingTool;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import block.Block;

import common.Map;

public class XMLWriter {
	private Document XMLDoc;
	
	public XMLWriter(String filePath, ArrayList<Block> blockList, Map map){
		
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

//<jcompany>
//<employee id="1" part="devlopment">
//    <name>Gildong Hong</name>
//    <age>25</age>
//</employee>
//</jcompany>
//
//
////Document및 XML트리 생성
//Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//Node root = document.createElement("JCompany");
//document.appendChild(root);
// {
//     Element people1 = document.createElement("employee");
//     people1.setAttribute("id", "1");
//     people1.setAttribute("part", "devlopment");
//     root.appendChild(people1);
//     {
//         Element name = document.createElement("name");
//         name.appendChild(document.createTextNode("Gildong Hong"));
//         people1.appendChild(name);
//     }
//     {
//         Element age = document.createElement("age");
//         age.appendChild(document.createTextNode("25"));
//         people1.appendChild(age);
//     }
// }
//
////Document 저장
//DOMSource xmlDOM = new DOMSource(document);
//StreamResult xmlFile = new StreamResult(new File("saved.xml"));
//TransformerFactory.newInstance().newTransformer().transform(xmlDOM, xmlFile);
