package makingTool;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BlockGameFrame extends JFrame {

	public BlockGameFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		//메뉴
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		mb.add(fileMenu);
		
		fileMenu.add(new JMenuItem("New"));
		fileMenu.add(new JMenuItem("Load"));
		fileMenu.add(new JMenuItem("Save"));
		fileMenu.addSeparator();
		fileMenu.add(new JMenuItem("Exit"));
		
		setJMenuBar(mb);
		
		XMLReader xml = new XMLReader("block_game.xml");
		Node blockGameNode = xml.getBlockGameElement();
		Node sizeNode = XMLReader.getNode(blockGameNode, XMLReader.E_SIZE);
		String w = XMLReader.getAttr(sizeNode, "w");
		String h = XMLReader.getAttr(sizeNode, "h");
		setSize(Integer.parseInt(w), Integer.parseInt(h));

		setContentPane(new GamePanel(xml.getGamePanelElement()));

		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BlockGameFrame("Block Game");
	}
}

class Block extends JLabel {
	Image img;
	int type;

	public Block(int x, int y, int w, int h, ImageIcon icon, int type) {
		this.setBounds(x, y, w, h);
		img = icon.getImage();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}

class Item extends JLabel{
	Image img;
	int type;
	
	public Item(int x, int y, int w, int h, ImageIcon icon, int type){
		this.setBounds(x, y, w, h);
		img = icon.getImage();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}

class Bar extends JLabel{
	Image img;
	int type;
	
	public Bar(int x, int y, int w, int h, ImageIcon icon, int type){
		this.setBounds(x, y, w, h);
		img = icon.getImage();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}

class Ball extends JLabel{
	Image img;
	int type;
	
	public Ball(int x, int y, int w, int h, ImageIcon icon, int type){
		this.setBounds(x, y, w, h);
		img = icon.getImage();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}

class GamePanel extends JPanel {
	ImageIcon bgImg;

	public GamePanel(Node gamePanelNode) {
		setLayout(null);
		
		Node bgNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BG);
		Node bgImageNode = XMLReader.getNode(bgNode, XMLReader.E_IMG);
		Node bgSoundNode = XMLReader.getNode(bgNode, XMLReader.E_SOUND);
		bgImg = new ImageIcon(bgImageNode.getTextContent());
		//bgSound = ...? BackGround Sound 넣는곳

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

				ImageIcon icon = new ImageIcon(XMLReader.getAttr(node, "img"));
				Block block = new Block(x, y, w, h, icon, type);
				add(block);
			}
		}
		
		Node itemNode = XMLReader.getNode(gamePanelNode,XMLReader.E_ITEM);
		nodeList = itemNode.getChildNodes();
		for(int i=0; i<nodeList.getLength(); i++){
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
				Item item = new Item(x, y, w, h, icon, type);
				add(item);
			}			
		}
		
		Node barNode = XMLReader.getNode(gamePanelNode,XMLReader.E_BAR);
		nodeList = barNode.getChildNodes();
		for(int i=0; i<nodeList.getLength(); i++){
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
		
		Node ballNode = XMLReader.getNode(gamePanelNode,XMLReader.E_BALL);
		nodeList = ballNode.getChildNodes();
		for(int i=0; i<nodeList.getLength(); i++){
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

	//BackGround Images 그리기
	public void paintComponent(Graphics g) {
		g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(),
				this);
	}
}
