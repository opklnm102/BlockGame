package makingTool;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;

import makingTool.BlockEditDialogFrame.BlockCheckListener;
import block.Block;

public class EditPanel extends JPanel {
	public static final String itemSelectedImg[] = { "images/block1.png",
			"images/block2.png", "images/block3.png", "images/block4.png",
			"images/block5.png", "images/block6.png", "images/block1.png",
			"images/block2.png", "images/block3.png" };

	public static final String itemImg[] = { "images/item1.png",
			"images/item2.png", "images/item3.png", "images/item4.png",
			"images/item5.png", "images/item6.png", "images/item7.png",
			"images/item8.png", "images/item9.png", "images/item10.png" };

	public static final String blockSelectedImg[] = { "images/block1.png",
			"images/block2.png", "images/block3.png", "images/block4.png",
			"images/block5.png", "images/block6.png", "images/block1.png",
			"images/block2.png", "images/block3.png" };

	public static final String blockImg[] = { "images/block1.png",
			"images/block2.png", "images/block3.png", "images/block4.png",
			"images/block5.png", "images/block6.png", "images/block1.png",
			"images/block2.png", "images/block3.png" };

	Block blocks[]; // Block Panel의 블럭 목록
	BlockCreateListener listener;
	Block block; // 선택된 블럭
	Block tmp; // 생성될 블럭
	int check; // 블럭 선택 여부

	JRadioButton blockRadioBtns[] = new JRadioButton[9];

	public EditPanel(int width, int height) {
		setLayout(null);
		setSize(width, height);

		blocks = new Block[9];

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
					x = e.getX();
					y = e.getY();
					System.out.println(x + " " + y);
					System.out.println(check);

					if (e.getClickCount() == 2) { // 블 편집 dialog
						System.out.println("map Double Click");
						if (isBlockLocation()) {

							
							
							
							
						}
					} else if (e.getClickCount() == 1) { // 블럭 생성
						System.out.println("map Click");
						switch (check) {
						case 1:
							tmp = new Block(x, y, 50, 30, blockImg[check - 1],
									"images/item1.png", 1);
							break;
						case 2:
							tmp = new Block(x, y, 50, 30, blockImg[check - 1],
									"images/item1.png", 2);
							break;
						case 3:
							tmp = new Block(x, y, 50, 30, blockImg[check - 1],
									"images/item1.png", 3);
							break;
						case 4:
							tmp = new Block(x, y, 50, 30, blockImg[check - 1],
									"images/item1.png", 4);
							break;
						case 5:
							tmp = new Block(x, y, 50, 30, blockImg[check - 1],
									"images/item1.png", 5);
							break;
						case 6:
							tmp = new Block(x, y, 50, 30, blockImg[check - 1],
									"images/item1.png", 6);
							break;
						case 7:
							tmp = new Block(x, y, 50, 30, blockImg[check - 1],
									"images/item1.png", 7);
							break;
						case 8:
							tmp = new Block(x, y, 50, 30, blockImg[check - 1],
									"images/item1.png", 8);
							break;
						case 9:
							tmp = new Block(x, y, 50, 30, blockImg[check - 1],
									"images/item1.png", 9);
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
				if (blockList.get(i).getX() == x
						&& blockList.get(i).getY() == y)
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

			ButtonGroup g = new ButtonGroup();

			// 리스너 설정
			listener = new BlockCreateListener();

			// 블럭 추가
			for (int i = 0; i < 9; i++) {
				System.out.println(20 + ", " + (20 + (i * 50)));

				ImageIcon icon = new ImageIcon(blockImg[i]);
				ImageIcon selectedIcon = new ImageIcon(blockSelectedImg[i]);
				blockRadioBtns[i] = new JRadioButton(icon);
				blockRadioBtns[i].setBorderPainted(true);
				blockRadioBtns[i].setSelectedIcon(selectedIcon);

				// 툴팁 달기

				blockRadioBtns[i].setBounds(20, 20 + (i * 50), 50, 30);
				
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
			}
		}
	}
}
