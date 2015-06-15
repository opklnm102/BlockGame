package makingTool;

import java.awt.Color;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.plaf.SplitPaneUI;

import block.Block;

public class EditPanel extends JPanel {
	Block blocks[];
	MyMouseListener listener;
	Block block;
	int x, y;
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
		public MapPanel() {
			setLayout(null);
			setSize(800, 900);

			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("pressed");
					x = e.getX();
					y = e.getY();
					System.out.println(x + " " + y);

					switch (check) {
					case 1:
						tmp = new Block(x, y, 50, 30, "images/block1.png", 1);
						break;
					case 2:
						tmp = new Block(x, y, 50, 30, "images/block2.png", 2);
						break;
					case 3:
						tmp = new Block(x, y, 50, 30, "images/block3.png", 3);
						break;
					case 4:
						tmp = new Block(x, y, 50, 30, "images/block4.png", 4);
						break;
					case 5:
						tmp = new Block(x, y, 50, 30, "images/block5.png", 5);
						break;
					case 6:
						tmp = new Block(x, y, 50, 30, "images/block6.png", 6);
						break;
					case 7:
						tmp = new Block(x, y, 50, 30, "images/block7.png", 7);
						break;
					case 8:
						tmp = new Block(x, y, 50, 30, "images/block1.png", 8);
						break;
					case 9:
						tmp = new Block(x, y, 50, 30, "images/block2.png", 9);
						break;
					case 10:
						tmp = new Block(x, y, 50, 30, "images/block3.png", 10);
						break;
					}		
				
					add(tmp);
					repaint();
				}
			});

			setBackground(Color.gray);
			setVisible(true);
		}
	}

	class BlockPanel extends JPanel {
		String imgSources[] = { "images/block1.png", "images/block2.png",
				"images/block3.png", "images/block4.png", "images/block5.png",
				"images/block6.png", "images/block1.png", "images/block2.png",
				"images/block3.png", "images/block4.png" };

		public BlockPanel() {
			setLayout(null);
			setBackground(new Color(153, 204, 230));

			// 블럭 생성
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new Block(20, 20 + (i * 50), 50, 30, imgSources[i],
						i + 1);
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

	class MyMouseListener extends MouseAdapter {  //더블클릭처리

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("click");
			block = (Block) e.getSource();

			if (check == 0) {  //선택 이미지 넣어 줘야한다.
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
			} else {
				check = 0;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}
	}
}
