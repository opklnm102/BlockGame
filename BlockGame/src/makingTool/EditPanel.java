package makingTool;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import block.Block;

public class EditPanel extends JPanel {
	
	public static String imgSources[] = { "images/block1.png", "images/block2.png",
		"images/block3.png", "images/block4.png", "images/block5.png",
		"images/block6.png", "images/block1.png", "images/block2.png",
		"images/block3.png", "images/block4.png" };
	
	Block blocks[];
	MyMouseListener listener;
	Block block;
	Block tmp;
	int check;

	public EditPanel(int width, int height) {
		setLayout(null);
		setSize(width, height);

		blocks = new Block[10];

		BlockPanel blockPanel = new BlockPanel();

		add(blockPanel);

		MapPanel mapPanel = new MapPanel();

		add(mapPanel);

		setVisible(true);
	}

	class MapPanel extends JPanel {
		ArrayList<Block> blockList;
		int x, y;

		public MapPanel() {
			setLayout(null);
			setSize(800, 900);
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
					Color.red, Color.pink));

			blockList = new ArrayList<>();

			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("map Click");
					x = e.getX();
					y = e.getY();
					System.out.println(x + " " + y);

					if (e.getClickCount() == 2) {  //블 편집 dialog
						if(isBlockLocation()){
														
						}

					} else if (e.getClickCount() == 1) {  //블럭 생성
						switch (check) {
						case 1:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 1);
							break;
						case 2:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 2);
							break;
						case 3:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 3);
							break;
						case 4:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 4);
							break;
						case 5:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 5);
							break;
						case 6:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 6);
							break;
						case 7:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 7);
							break;
						case 8:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 8);
							break;
						case 9:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 9);
							break;
						case 10:
							tmp = new Block(x, y, 50, 30, imgSources[check-1],
									"images/item1.png", 10);
							break;
						}

						blockList.add(tmp);
						add(tmp);
						repaint();
					}
				}
			});

			setBackground(Color.gray);
			setVisible(true);
		}

		boolean isBlockLocation() {
			for (int i = 0; i < blockList.size(); i++) {
				if (blockList.get(i).getX() == x && blockList.get(i).getY() == y)
					return true;
			}
			return false;
		}
	}

	class BlockPanel extends JPanel {		

		public BlockPanel() {
			setLayout(null);
			setBackground(new Color(153, 204, 230));
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
					Color.red, Color.pink));

			// 블럭 생성
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new Block(20, 20 + (i * 50), 50, 30, imgSources[i],
						"images/item1.png", i + 1);
			}

			// 리스너 설정
			listener = new MyMouseListener();

			for (Block b : blocks) {
				b.addMouseListener(listener);
			}

			// 블럭 패널에 추가
			for (Block b : blocks) {
				add(b);
			}

			setBounds(800, 0, 300, 900);
			setVisible(true);
		}
	}

	class MyMouseListener extends MouseAdapter { 

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("block Click");
			block = (Block) e.getSource();

			if (check == 0) {
				System.out.println("check true");
				switch (block.getType()) {
				case 1:
					check = 1;
					break;
				case 2:
					check = 2;
					break;
				case 3:
					check = 3;
					break;
				case 4:
					check = 4;
					break;
				case 5:
					check = 5;
					break;
				case 6:
					check = 6;
					break;
				case 7:
					check = 7;
					break;
				case 8:
					check = 8;
					break;
				case 9:
					check = 9;
					break;
				case 10:
					check = 10;
					break;
				}
				block.clickChangeImg();
				repaint();
			} else {
				block.clickChangeImg();
				repaint();
				check = 0;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}
	}
}
