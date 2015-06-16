package makingTool;

import item.Item;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ball.Ball;
import bar.Bar;
import block.Block;

public class GamePanel extends JPanel {
	ImageIcon bgImg;

	public GamePanel(Node gamePanelNode, int width, int height) {
		setLayout(null);
		setSize(width, height);
		setVisible(true);

		Node bgNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BG);
		Node bgImageNode = XMLReader.getNode(bgNode, XMLReader.E_IMG);
		Node bgSoundNode = XMLReader.getNode(bgNode, XMLReader.E_SOUND);
		bgImg = new ImageIcon(bgImageNode.getTextContent());
		// bgSound = ...? BackGround Sound 넣는곳

		// read <Fish><Obj>s from the XML parse tree, make Food objects, and add
		// them to the FishBowl panel.
		Node blockNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BLOCK);
		NodeList nodeList = blockNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE)
				continue;
			// found!!, <Obj> tag
			if (node.getNodeName().equals(XMLReader.E_OBJ)) {
				int x = Integer.parseInt(XMLReader.getAttr(node, "x"));
				int y = Integer.parseInt(XMLReader.getAttr(node, "y"));
				int w = Integer.parseInt(XMLReader.getAttr(node, "w"));
				int h = Integer.parseInt(XMLReader.getAttr(node, "h"));
				int type = Integer.parseInt(XMLReader.getAttr(node, "type"));

				Block block = new Block(x, y, w, h, XMLReader.getAttr(node,"img"), XMLReader.getAttr(node,"clickImg"), type);
				add(block);
			}
		}

		Node itemNode = XMLReader.getNode(gamePanelNode, XMLReader.E_ITEM);
		nodeList = itemNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE)
				continue;
			// found!!, <Obj> tag
			if (node.getNodeName().equals(XMLReader.E_OBJ)) {
				int x = Integer.parseInt(XMLReader.getAttr(node, "x"));
				int y = Integer.parseInt(XMLReader.getAttr(node, "y"));
				int w = Integer.parseInt(XMLReader.getAttr(node, "w"));
				int h = Integer.parseInt(XMLReader.getAttr(node, "h"));
				int type = Integer.parseInt(XMLReader.getAttr(node, "type"));

				Item item = new Item(x, y, w, h, XMLReader.getAttr(node, "img"), type);
				add(item);
			}
		}

		Node barNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BAR);
		nodeList = barNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE)
				continue;
			// found!!, <Obj> tag
			if (node.getNodeName().equals(XMLReader.E_OBJ)) {
				int x = Integer.parseInt(XMLReader.getAttr(node, "x"));
				int y = Integer.parseInt(XMLReader.getAttr(node, "y"));
				int w = Integer.parseInt(XMLReader.getAttr(node, "w"));
				int h = Integer.parseInt(XMLReader.getAttr(node, "h"));
				int type = Integer.parseInt(XMLReader.getAttr(node, "type"));

				ImageIcon icon = new ImageIcon(XMLReader.getAttr(node, "img"));
				Bar bar = new Bar(x, y, w, h, icon, type);
				add(bar);
			}
		}

		Node ballNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BALL);
		nodeList = ballNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE)
				continue;
			// found!!, <Obj> tag
			if (node.getNodeName().equals(XMLReader.E_OBJ)) {
				int x = Integer.parseInt(XMLReader.getAttr(node, "x"));
				int y = Integer.parseInt(XMLReader.getAttr(node, "y"));
				int w = Integer.parseInt(XMLReader.getAttr(node, "w"));
				int h = Integer.parseInt(XMLReader.getAttr(node, "h"));
				int type = Integer.parseInt(XMLReader.getAttr(node, "type"));

				ImageIcon icon = new ImageIcon(XMLReader.getAttr(node, "img"));
				Ball ball = new Ball(x, y, w, h, icon, type);
				add(ball);
			}
		}
	}

	// BackGround Images 그리기
	public void paintComponent(Graphics g) {
		g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(),
				this);
	}
}