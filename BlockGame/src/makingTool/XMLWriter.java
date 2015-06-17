package makingTool;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
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

	public XMLWriter(String filePath, ArrayList<Block> blockList, Map map) {

		try {
			XMLDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.newDocument();

			Node root = XMLDoc.createElement("BlockGame");
			XMLDoc.appendChild(root);
			{
				Element screen = XMLDoc.createElement("Screen");
				root.appendChild(screen);
				{
					Element size = XMLDoc.createElement("Size");
					size.setAttribute("w", "800");
					size.setAttribute("h", "900");
					screen.appendChild(size);
				}

				Element gamePanel = XMLDoc.createElement("GamePanel");
				root.appendChild(gamePanel);
				{
					Element bg = XMLDoc.createElement("Bg");
					gamePanel.appendChild(bg);
					{
						Element img = XMLDoc.createElement("Img");
						img.appendChild(XMLDoc.createTextNode(map.getBgImg()));
						bg.appendChild(img);
					}
					{
						Element sound = XMLDoc.createElement("Sound");
						sound.appendChild(XMLDoc.createTextNode(map
								.getBgSound()));
						bg.appendChild(sound);
					}
					Element block = XMLDoc.createElement("Block");
					gamePanel.appendChild(block);
					{
						for (int i = 0; i < blockList.size(); i++) {
							Block currentBlock = blockList.get(i);
							Integer x = currentBlock.getX();
							Integer y = currentBlock.getY();
							Integer w = currentBlock.getWidth();
							Integer h = currentBlock.getHeight();
							Integer type = currentBlock.getType();
							String src = currentBlock.getSrc();

							Element obj = XMLDoc.createElement("Obj");
							obj.setAttribute("x", x.toString());
							obj.setAttribute("y", y.toString());
							obj.setAttribute("w", w.toString());
							obj.setAttribute("h", h.toString());
							obj.setAttribute("type", type.toString());
							obj.setAttribute("img", src);
							block.appendChild(obj);
						}
					}
					Element bar = XMLDoc.createElement("Bar");
					gamePanel.appendChild(bar);
					{
						Element obj = XMLDoc.createElement("Obj");
						obj.setAttribute("x", "400");
						obj.setAttribute("y", "750");
						obj.setAttribute("w", "200");
						obj.setAttribute("h", "20");
						obj.setAttribute("img", "images/bar.png");
						bar.appendChild(obj);
					}
					Element ball = XMLDoc.createElement("Ball");
					gamePanel.appendChild(ball);
					{
						Element obj = XMLDoc.createElement("Obj");
						obj.setAttribute("x", "400");
						obj.setAttribute("y", "710");
						obj.setAttribute("w", "40");
						obj.setAttribute("h", "40");
						obj.setAttribute("img", "images/ball.png");
						ball.appendChild(obj);
					}					
				}
			}

			// Document 저장
			DOMSource xmlDOM = new DOMSource(XMLDoc);
			StreamResult xmlFile = new StreamResult(new File(filePath + ".xml"));
			TransformerFactory.newInstance().newTransformer()
					.transform(xmlDOM, xmlFile);

		} catch (Exception e) {
			System.out.println(e + "DOM 파서 생성 실패");
		}
	}
}
