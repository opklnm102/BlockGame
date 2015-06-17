package makingTool;

import item.Item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ball.Ball;
import bar.Bar;
import block.Block;
import common.Map;
import common.MapSetting;

public class EditPanel extends JPanel {
	public static final String itemSelectedImg[] = { "images/block1.png",
			"images/block2.png", "images/block3.png", "images/block4.png",
			"images/block5.png", "images/block6.png", "images/block7.png",
			"images/block8.png", "images/block9.png" };

	public static final String itemImg[] = { "images/item1.png",
			"images/item2.png", "images/item3.png", "images/item4.png",
			"images/item5.png", "images/item6.png", "images/item7.png",
			"images/item8.png", "images/item9.png" };

	public static final String blockSelectedImg[] = { "images/block1.png",
			"images/block2.png", "images/block3.png", "images/block4.png",
			"images/block5.png", "images/block6.png", "images/block7.png",
			"images/block8.png", "images/block9.png", "images/block10.png",
			"images/block11.png", "images/block12.png", "images/block13.png",
			"images/block14.png" };

	public static final String blockImg[] = { "images/block1.png",
			"images/block2.png", "images/block3.png", "images/block4.png",
			"images/block5.png", "images/block6.png", "images/block7.png",
			"images/block8.png", "images/block9.png", "images/block10.png",
			"images/block11.png", "images/block12.png", "images/block13.png",
			"images/block14.png" };

	// public static final String itemName[] = { "생명줄 생성", "미사일", "자석", "관통 볼",
	// "공속도 조절",
	// "바속도 조절", "공크기 조절", "공 분열", "바길이 조절" };

	Block blocks[]; // Block Panel의 블럭 목록
	BlockCreateListener listener;
	Block block; // 선택된 블럭
	Block tmp; // 생성될 블럭
	int check; // 블럭 선택 여부

	BlockPanel blockPanel;
	MapPanel mapPanel;

	JRadioButton blockRadioBtns[] = new JRadioButton[14]; // 블럭 제작용

	// Dialog
	BlockEditDialog blockEditDialog;
	ImagePanel imagePanel;
	ItemPanel itemPanel;
	int imgCheck = 0;
	boolean itemCheck[] = new boolean[9];
	JCheckBox itemCheckBoxs[] = new JCheckBox[9]; // 아이템 편집용
	JRadioButton imgRadioBtns[] = new JRadioButton[14]; // 이미지 편집용

	// Map Panel용
	ArrayList<Block> blockList;
	int x, y;
	int blockIdx;

	public ArrayList<Block> getBlockList() {
		return blockList;
	}

	public EditPanel(int width, int height) {
		setLayout(null);
		setSize(width, height);

		blocks = new Block[9];

		blockPanel = new BlockPanel();
		mapPanel = new MapPanel(null);

		blockEditDialog = new BlockEditDialog((JFrame) getParent(), "블록 편집");

		add(blockPanel);
		add(mapPanel);

		setVisible(true);
	}

	public void removeMapPanel() {
		remove(mapPanel);
		// repaint();
	}

	public MapPanel createMapPanel(Node gamePanelNode) {
		return new MapPanel(gamePanelNode);
	}

	class MapPanel extends JPanel implements MapSetting {
		ImageIcon bgImg;

		public MapPanel(Node gamePanelNode) {
			setLayout(null);
			setSize(800, 900);
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
					Color.red, Color.pink));

			bgImg = new ImageIcon("images/background1.png");

			blockList = new ArrayList<Block>();

			if (gamePanelNode != null) {
				setting(gamePanelNode);
			}

			addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					System.out.println("map dragged");
					x = e.getX();
					y = e.getY();
					if (!isBlockLocation()) {
						if (check != 0) {
							switch (check) {
							case 1:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 1);
								break;
							case 2:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 2);
								break;
							case 3:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 3);
								break;
							case 4:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 4);
								break;
							case 5:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 5);
								break;
							case 6:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 6);
								break;
							case 7:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 7);
								break;
							case 8:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 8);
								break;
							case 9:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 9);
								break;
							case 10:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 9);
								break;
							case 11:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 9);
								break;
							case 12:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 9);
								break;
							case 13:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 9);
								break;
							case 14:
								tmp = new Block(x, y, 50, 50,
										blockImg[check - 1],
										"images/item1.png", 9);
								break;
							}
							blockList.add(tmp);
							add(tmp);
							repaint();
						}
					}
				}
			});

			addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					x = e.getX();
					y = e.getY();
					if (e.getButton() == MouseEvent.BUTTON3) { // 오른쪽 버튼, 블럭 삭제
						System.out.println("map right pressed");
						if (isBlockLocation()) {
							mapPanel.remove(blockList.get(blockIdx));
							blockList.remove(blockIdx);
							mapPanel.repaint();
							System.out.println("삭제 후 리스트 사이즈"
									+ blockList.size());
						}
					} else if (e.getButton() == MouseEvent.BUTTON1) { // 왼쪽 버튼,
						System.out.println("map left pressed");
						if (!isBlockLocation()) {
							if (check != 0) {
								switch (check) {
								case 1:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 1);
									break;
								case 2:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 2);
									break;
								case 3:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 3);
									break;
								case 4:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 4);
									break;
								case 5:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 5);
									break;
								case 6:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 6);
									break;
								case 7:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 7);
									break;
								case 8:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 8);
									break;
								case 9:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 9);
									break;
								case 10:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 9);
									break;
								case 11:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 9);
									break;
								case 12:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 9);
									break;
								case 13:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 9);
									break;
								case 14:
									tmp = new Block(x, y, 50, 50,
											blockImg[check - 1],
											"images/item1.png", 9);
									break;
								}
								blockList.add(tmp);
								add(tmp);
								repaint();
							}
						}
					}
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					x = e.getX();
					y = e.getY();
					System.out.println(x + " " + y);
					System.out.println(check);

					if (e.getClickCount() == 2) { // 블 편집 Dialog
						System.out.println("map Double Click");
						if (isBlockLocation()) {
							// 다이얼로그 띄우기

							// 해당 블록의 이미지는 선택해놓는다.
							imgRadioBtns[check - 1].setSelected(true);

							blockEditDialog.setVisible(true);
						}
					}
				}
			});

			setBackground(Color.gray);
			setVisible(true);
		}

		// 현재 좌표에 블럭있는지 검사
		boolean isBlockLocation() {
			int w = 50;
			int h = 50;

			System.out.println("블럭리스트 사이즈 " + blockList.size());
			for (int i = 0; i < blockList.size(); i++) {
				int oldX = blockList.get(i).getX();
				int oldY = blockList.get(i).getY();

				if (((oldX <= x && x <= oldX + w) && (oldY <= y + h && y + h <= oldY
						+ h))
						|| ((oldX <= x && x <= oldX + w) && (oldY <= y && y <= oldY
								+ h))
						|| ((oldX <= x + w && x + w <= oldX + w) && (oldY <= y
								+ h && y + h <= oldY + h))
						|| ((oldX <= x + w && x + w <= oldX + w) && (oldY <= y && y <= oldY
								+ h))) {
					blockIdx = i;
					return true;
				}
			}
			return false;
		}

		public void mapPanelRepaint() {
			repaint();
		}

		public void setMap(Map map) {
			bgImg = new ImageIcon(map.getBgImg());
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			System.out.println(bgImg);
			g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(),
					this.getHeight(), this);
		}

		@Override
		public void setting(Node gamePanelNode) {
			Node bgNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BG);
			Node bgImageNode = XMLReader.getNode(bgNode, XMLReader.E_IMG);
			Node bgSoundNode = XMLReader.getNode(bgNode, XMLReader.E_SOUND);
			bgImg = new ImageIcon(bgImageNode.getTextContent());
			// bgSound = ...? BackGround Sound 넣는곳

			// read <Fish><Obj>s from the XML parse tree, make Food objects, and
			// add
			// them to the FishBowl panel.
			Node blockNode = XMLReader
					.getNode(gamePanelNode, XMLReader.E_BLOCK);
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
					int type = Integer
							.parseInt(XMLReader.getAttr(node, "type"));

					Block block = new Block(x, y, w, h, XMLReader.getAttr(node,
							"img"), XMLReader.getAttr(node, "clickImg"), type);
					blockList.add(block);
					add(block);
				}
			}

			// Node itemNode = XMLReader.getNode(gamePanelNode,
			// XMLReader.E_ITEM);
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
			// int type = Integer
			// .parseInt(XMLReader.getAttr(node, "type"));
			//
			// Item item = new Item(x, y, w, h, XMLReader.getAttr(node,
			// "img"), type);
			// add(item);
			// }
			// }

			// Node barNode = XMLReader.getNode(gamePanelNode, XMLReader.E_BAR);
			// nodeList = barNode.getChildNodes();
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
			// int type = Integer
			// .parseInt(XMLReader.getAttr(node, "type"));
			//
			// ImageIcon icon = new ImageIcon(XMLReader.getAttr(node,
			// "img"));
			// Bar bar = new Bar(x, y, w, h, icon, type);
			// add(bar);
			// }
			// }

			// Node ballNode = XMLReader.getNode(gamePanelNode,
			// XMLReader.E_BALL);
			// nodeList = ballNode.getChildNodes();
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
			// int type = Integer
			// .parseInt(XMLReader.getAttr(node, "type"));
			//
			// ImageIcon icon = new ImageIcon(XMLReader.getAttr(node,
			// "img"));
			// Ball ball = new Ball(x, y, w, h, icon, type);
			// add(ball);
			// }
			// }
		}
	}

	class BlockPanel extends JPanel {

		public BlockPanel() {
			setLayout(null);
			setBackground(new Color(153, 204, 230));
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
					Color.red, Color.pink));

			ButtonGroup g = new ButtonGroup();

			// 리스너 설정
			listener = new BlockCreateListener();

			// 블럭 추가
			for (int i = 0, locationX = 0, locationY = 0; i < 14; i++) {
				System.out.println(20 + ", " + (20 + (i * 50)));

				ImageIcon icon = new ImageIcon(blockImg[i]);
				ImageIcon selectedIcon = new ImageIcon(blockSelectedImg[i]);
				blockRadioBtns[i] = new JRadioButton(icon);
				blockRadioBtns[i].setBorderPainted(true);
				blockRadioBtns[i].setSelectedIcon(selectedIcon);

				// 툴팁 달기

				if (i % 2 == 1) {
					locationX = 110;
				} else {
					locationX = 0;
					locationY += 70;
				}

				blockRadioBtns[i].setBounds(60 + locationX, 100 + locationY,
						50, 50);

				blockRadioBtns[i].addItemListener(listener);

				g.add(blockRadioBtns[i]); // 그룹에 추가

				// 패널에 추가
				add(blockRadioBtns[i]);
			}
			setBounds(800, 0, 300, 900);
			setVisible(true);
		}
	}

	class BlockCreateListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println("block Click");
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				check = 0;
				return;
			}
			if (blockRadioBtns[0].isSelected()) {
				check = 1;
			} else if (blockRadioBtns[1].isSelected()) {
				check = 2;
			} else if (blockRadioBtns[2].isSelected()) {
				check = 3;
			} else if (blockRadioBtns[3].isSelected()) {
				check = 4;
			} else if (blockRadioBtns[4].isSelected()) {
				check = 5;
			} else if (blockRadioBtns[5].isSelected()) {
				check = 6;
			} else if (blockRadioBtns[6].isSelected()) {
				check = 7;
			} else if (blockRadioBtns[7].isSelected()) {
				check = 8;
			} else if (blockRadioBtns[8].isSelected()) {
				check = 9;
			} else if (blockRadioBtns[9].isSelected()) {
				check = 10;
			} else if (blockRadioBtns[10].isSelected()) {
				check = 11;
			} else if (blockRadioBtns[11].isSelected()) {
				check = 12;
			} else if (blockRadioBtns[12].isSelected()) {
				check = 13;
			} else if (blockRadioBtns[13].isSelected()) {
				check = 14;
			}
		}
	}

	class BlockEditDialog extends JDialog {
		JButton okBtn;
		JButton closeBtn;

		BlockEditDialog(JFrame frame, String title) {
			super(frame, title);
			setLayout(null);
			setSize(450, 550);

			JTabbedPane pane = createTabbedPane();
			okBtn = new JButton("Ok");
			closeBtn = new JButton("Close");

			pane.setBounds(10, 10, 410, 450);
			okBtn.setBounds(280, 474, 66, 25);
			closeBtn.setBounds(352, 474, 66, 25);

			add(pane);
			add(okBtn);
			add(closeBtn); // 리스너 만들기

			okBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// 선택된 이미지로 변경
					blockList.get(blockIdx).setImg(blockImg[imgCheck]);
					mapPanel.repaint();
					setVisible(false);
				}
			});

			closeBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});

		}

		JTabbedPane createTabbedPane() {
			imagePanel = new ImagePanel();
			itemPanel = new ItemPanel();
			JTabbedPane pane = new JTabbedPane();
			pane.addTab("Image", imagePanel);
			pane.addTab("Item", itemPanel);

			return pane;
		}
	}

	class ImagePanel extends JPanel {
		ImagePanel() {
			setLayout(null);
			ImgCheckListener imgListener = new ImgCheckListener();

			ButtonGroup g = new ButtonGroup();

			// 블럭 추가
			for (int i = 0, h = 0; i < 14; i++) {
				if (i > 11)
					h = 210;
				else if (i > 7)
					h = 140;
				else if (i > 3)
					h = 70;
				System.out.println((20 + 100 * (i % 4)) + ", " + (h + 50));

				ImageIcon icon = new ImageIcon(blockImg[i]);
				ImageIcon selectedIcon = new ImageIcon(blockSelectedImg[i]);
				imgRadioBtns[i] = new JRadioButton(icon);
				imgRadioBtns[i].setBorderPainted(true);
				imgRadioBtns[i].setSelectedIcon(selectedIcon);

				// 툴팁 달기

				imgRadioBtns[i].setBounds(20 + 100 * (i % 4), h + 30, 50, 50);

				imgRadioBtns[i].addItemListener(imgListener);

				g.add(imgRadioBtns[i]);
				add(imgRadioBtns[i]);
			}

			// setVisible(true);
		}
	}

	class ItemPanel extends JPanel {
		ItemPanel() {
			setLayout(null);

			ItemCheckListener itemListener = new ItemCheckListener();

			for (int i = 0, h = 0; i < 9; i++) {
				if (i > 7)
					h = 140;
				else if (i > 3)
					h = 70;

				ImageIcon icon = new ImageIcon(itemImg[i]);
				ImageIcon selectedIcon = new ImageIcon(itemSelectedImg[i]);
				// JCheckBox itemCheckBox = new JCheckBox(itemName[i],
				// icon);

				itemCheckBoxs[i] = new JCheckBox(icon);
				itemCheckBoxs[i].setBorderPainted(true);
				itemCheckBoxs[i].setSelectedIcon(selectedIcon);

				// 툴팁달기

				System.out.println((20 + 100 * (i % 4)) + ", " + (h + 50));

				itemCheckBoxs[i].setBounds(20 + 100 * (i % 4), h + 30, 50, 50);

				itemCheckBoxs[i].addItemListener(itemListener);

				add(itemCheckBoxs[i]);
			}
		}
	}

	// Dialog Listener
	class ImgCheckListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println("image Click");
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				imgCheck = -1;
				return;
			}
			if (imgRadioBtns[0].isSelected()) {
				imgCheck = 0;
			} else if (imgRadioBtns[1].isSelected()) {
				imgCheck = 1;
			} else if (imgRadioBtns[2].isSelected()) {
				imgCheck = 2;
			} else if (imgRadioBtns[3].isSelected()) {
				imgCheck = 3;
			} else if (imgRadioBtns[4].isSelected()) {
				imgCheck = 4;
			} else if (imgRadioBtns[5].isSelected()) {
				imgCheck = 5;
			} else if (imgRadioBtns[6].isSelected()) {
				imgCheck = 6;
			} else if (imgRadioBtns[7].isSelected()) {
				imgCheck = 7;
			} else if (imgRadioBtns[8].isSelected()) {
				imgCheck = 8;
			} else if (imgRadioBtns[9].isSelected()) {
				imgCheck = 9;
			} else if (imgRadioBtns[10].isSelected()) {
				imgCheck = 10;
			} else if (imgRadioBtns[11].isSelected()) {
				imgCheck = 11;
			} else if (imgRadioBtns[12].isSelected()) {
				imgCheck = 12;
			} else if (imgRadioBtns[13].isSelected()) {
				imgCheck = 13;
			}
			System.out.println(imgCheck);
		}
	}

	// Dialog Listener
	class ItemCheckListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println("item Click");
			// if (e.getStateChange() == ItemEvent.DESELECTED) {
			// initItemCheck();
			// return;
			// }
			if (itemCheckBoxs[0].isSelected())
				itemCheck[0] = true;
			else
				itemCheck[0] = false;

			if (itemCheckBoxs[1].isSelected())
				itemCheck[1] = true;
			else
				itemCheck[1] = false;

			if (itemCheckBoxs[2].isSelected())
				itemCheck[2] = true;
			else
				itemCheck[2] = false;

			if (itemCheckBoxs[3].isSelected())
				itemCheck[3] = true;
			else
				itemCheck[3] = false;

			if (itemCheckBoxs[4].isSelected())
				itemCheck[4] = true;
			else
				itemCheck[4] = false;

			if (itemCheckBoxs[5].isSelected())
				itemCheck[5] = true;
			else
				itemCheck[5] = false;

			if (itemCheckBoxs[6].isSelected())
				itemCheck[6] = true;
			else
				itemCheck[6] = false;

			if (itemCheckBoxs[7].isSelected())
				itemCheck[7] = true;
			else
				itemCheck[7] = false;

			if (itemCheckBoxs[8].isSelected())
				itemCheck[8] = true;
			else
				itemCheck[8] = false;

			// for(int i=0; i<itemCheck.length; i++)
			// System.out.println(itemCheck[i]);
		}
	}

	public void initItemCheck() {
		for (int i = 0; i < itemCheck.length; i++) {
			itemCheck[i] = false;
		}
	}
}
