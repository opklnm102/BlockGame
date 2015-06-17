package gameTool;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import makingTool.XMLReader;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ball.Ball;
import bar.Bar;
import block.Block;

import common.MapSetting;

public class GamePanel extends JPanel implements MapSetting {
	ImageIcon bgImg;
	Bar bar;
	Ball ball;
	ArrayList<Block> blocks;

	public GamePanel(Node gamePanelNode, int width, int height) {
		setLayout(null);
		setSize(width, height);
		// setBackground(Color.gray);
		System.out.println(width + " " + height);

		bgImg = new ImageIcon("images/background1.png");
		
		blocks = new ArrayList<Block>();

		if (gamePanelNode != null)
			setting(gamePanelNode);

		setVisible(true);
		System.out.println("gamePanel create end");

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				if (e.getX() - (bar.getWidth() / 2) >= 0
						&& e.getX() + (bar.getWidth() / 2 + 10) < width) {
					bar.setLocation(e.getX() - (bar.getWidth() / 2), bar.getY());
				}
				repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});

		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					BarHit();
					BlockHit();

				}
			}
		});
		th.start();
	}

	public boolean BarHit() {
		if (ball.getX() + ball.getWidth() >= bar.getX()
				&& ball.getX() + ball.getWidth() <= bar.getX() + bar.getWidth()
				&& ball.getY() + ball.getHeight() >= bar.getY()
				&& ball.getY() + ball.getHeight() <= bar.getY()
						+ bar.getHeight()) {
			// System.out.println(ball.getX()+", "+ball.getY());
			int VectorY, HitBar;
			// System.out.println(((bar.getWidth()-bar.getX())/40)+1);
			VectorY = ball.getVec();
			HitBar = ball.getX() - bar.getX();
			// System.out.println("HitBar="+HitBar);
			if ((HitBar >= 0 && HitBar < 40))
				VectorY = 10;
			else if (HitBar >= 40 && HitBar < 90)
				VectorY = 11;
			else if (HitBar >= 90 && HitBar <= 110)
				VectorY = 12;
			else if (HitBar >= 110 && HitBar <= 160)
				VectorY = 1;
			else if (HitBar >= 160 && HitBar <= 200)
				VectorY = 2;
			// System.out.println("VectorY = "+VectorY);
			ball.setVec(VectorY);
			return true;
		}
		return false;

	}

	public boolean BlockHit() {
		for (int i = 0; i < blocks.size(); i++) {
			if (ball.getX() + ball.getWidth() > blocks.get(i).getX()
					&& ball.getX() < blocks.get(i).getX()
							+ blocks.get(i).getWidth()
					&& ball.getY() + ball.getHeight() > blocks.get(i).getY()
					&& ball.getY() < blocks.get(i).getY()
							+ blocks.get(i).getHeight()) {
				System.out.println("드루와");
				int Vec;
				Vec = ball.getVec();
				System.out.println(Vec + ", " + ball.getWidth());
				if (Vec == 12) {
					Vec = 6;
				} else if (Vec == 7) {
					Vec = 11;
				} else if (Vec == 8) {
					Vec = 10;
				} else if (Vec == 4) {
					Vec = 2;
				} else if (Vec == 5) {
					Vec = 1;
				} else if (Vec == 11) {
					Vec = 8;
				} else if (Vec == 10) {
					Vec = 7;
				} else if (Vec == 1) {
					Vec = 5;
				} else if (Vec == 2) {
					Vec = 4;
				}
				repaint();
				ball.setVec(Vec);
				blocks.get(i).setVisible(false);
				blocks.remove(i);
				return true;
			}
		}
		return false;
	}

	// BackGround Images 그리기
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(bgImg);
		g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(),
				this);
	}

	public void gamePanelRepaint() {
		repaint();
	}

	@Override
	public void setting(Node gamePanelNode) {
		System.out.println("setting start");
		Node bgNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BG);
		Node bgImageNode = XMLReader.getNode(bgNode, XMLReader.E_IMG);
		Node bgSoundNode = XMLReader.getNode(bgNode, XMLReader.E_SOUND);
		bgImg = new ImageIcon(bgImageNode.getTextContent());
		System.out.println("백그라운드 이미지" + bgImageNode.getTextContent());
		System.out.println(bgImg);
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

				Block block = new Block(x, y, w, h, XMLReader.getAttr(node,
						"img"), XMLReader.getAttr(node, "clickImg"), type);
				blocks.add(block);
				add(block);
			}
		}

		// Node itemNode = XMLReader.getNode(gamePanelNode, XMLReader.E_ITEM);
		// nodeList = itemNode.getChildNodes();
		// for (int i = 0; i < nodeList.getLength(); i++) {
		// Node node = nodeList.item(i);
		// if (node.getNodeType() != Node.ELEMENT_NODE)
		// continue;
		// // found!!, <Obj> tag
		// if (node.getNodeName().equals(XMLReader.E_OBJ)) {
		// int x = Integer.parseInt(XMLReader.getAttr(node, "x"));
		// int y = Integer.parseInt(XMLReader.getAttr(node, "y"));
		// int w = Integer.parseInt(XMLReader.getAttr(node, "w"));
		// int h = Integer.parseInt(XMLReader.getAttr(node, "h"));
		// int type = Integer.parseInt(XMLReader.getAttr(node, "type"));
		//
		// Item item = new Item(x, y, w, h,
		// XMLReader.getAttr(node, "img"), type);
		// add(item);
		// }
		// }

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

				ImageIcon icon = new ImageIcon(XMLReader.getAttr(node, "img"));
				bar = new Bar(x, y, w, h, icon);
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

				ImageIcon icon = new ImageIcon(XMLReader.getAttr(node, "img"));
				ball = new Ball(x, y, w, h, icon);
				add(ball);
			}
		}
		System.out.println("setting end");
	}
}