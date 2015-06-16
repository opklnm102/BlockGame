package makingTool;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	// public static final String itemName[] = { "생명줄 생성", "미사일", "자석", "관통 볼",
	// "공속도 조절",
	// "바속도 조절", "공크기 조절", "공 분열", "바길이 조절" };

	Block blocks[]; // Block Panel의 블럭 목록
	BlockCreateListener listener;
	Block block; // 선택된 블럭
	Block tmp; // 생성될 블럭
	int check; // 블럭 선택 여부

	JRadioButton blockRadioBtns[] = new JRadioButton[9]; // 블럭 제작용
	JCheckBox itemCheckBoxs[] = new JCheckBox[9]; // 아이템 편집용
	JRadioButton imgRadioBtns[] = new JRadioButton[9]; // 이미지 편집용

	// Dialog용
	BlockEditDialog blockEditDialog;
	int imgCheck = 0;
	boolean itemCheck[] = new boolean[9];

	// Map Panel용
	ArrayList<Block> blockList;
	int x, y;

	public EditPanel(int width, int height) {
		setLayout(null);
		setSize(width, height);

		blocks = new Block[9];

		BlockPanel blockPanel = new BlockPanel();
		MapPanel mapPanel = new MapPanel();

		blockEditDialog = new BlockEditDialog((JFrame) getParent(), "블록 편집");

		add(blockPanel);
		add(mapPanel);

		setVisible(true);
	}

	class MapPanel extends JPanel {
		public MapPanel() {
			setLayout(null);
			setSize(800, 900);
			setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
					Color.red, Color.pink));

			blockList = new ArrayList<Block>();

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
							// 다이얼로그 띄우기

							blockEditDialog.setVisible(true);
						}
					} else if (e.getClickCount() == 1) { // 블럭 생성
						System.out.println("map Click");
						if (check != 0) {
							switch (check) {
							case 1:
								tmp = new Block(x, y, 50, 30,
										blockImg[check - 1],
										"images/item1.png", 1);
								break;
							case 2:
								tmp = new Block(x, y, 50, 30,
										blockImg[check - 1],
										"images/item1.png", 2);
								break;
							case 3:
								tmp = new Block(x, y, 50, 30,
										blockImg[check - 1],
										"images/item1.png", 3);
								break;
							case 4:
								tmp = new Block(x, y, 50, 30,
										blockImg[check - 1],
										"images/item1.png", 4);
								break;
							case 5:
								tmp = new Block(x, y, 50, 30,
										blockImg[check - 1],
										"images/item1.png", 5);
								break;
							case 6:
								tmp = new Block(x, y, 50, 30,
										blockImg[check - 1],
										"images/item1.png", 6);
								break;
							case 7:
								tmp = new Block(x, y, 50, 30,
										blockImg[check - 1],
										"images/item1.png", 7);
								break;
							case 8:
								tmp = new Block(x, y, 50, 30,
										blockImg[check - 1],
										"images/item1.png", 8);
								break;
							case 9:
								tmp = new Block(x, y, 50, 30,
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

			setBackground(Color.gray);
			setVisible(true);
		}

		boolean isBlockLocation() {
			System.out.println("블럭리스트 사이즈 " + blockList.size());
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
			JTabbedPane pane = new JTabbedPane();
			pane.addTab("Image", new ImagePanel());
			pane.addTab("Item", new ItemPanel());

			return pane;
		}
	}

	class ImagePanel extends JPanel {
		ImagePanel() {
			setLayout(null);
			ImgCheckListener imgListener = new ImgCheckListener();

			ButtonGroup g = new ButtonGroup();

			// 블럭 추가
			for (int i = 0, h = 0; i < 9; i++) {
				if (i > 7)
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

				imgRadioBtns[i].setBounds(20 + 100 * (i % 4), h + 30, 65, 39);

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
				// JCheckBox itemCheckBox = new JCheckBox(itemName[i], icon);

				itemCheckBoxs[i] = new JCheckBox(icon);
				itemCheckBoxs[i].setBorderPainted(true);
				itemCheckBoxs[i].setSelectedIcon(selectedIcon);

				// 툴팁달기

				System.out.println((20 + 100 * (i % 4)) + ", " + (h + 50));

				itemCheckBoxs[i].setBounds(20 + 100 * (i % 4), h + 30, 65, 39);

				itemCheckBoxs[i].addItemListener(itemListener);

				add(itemCheckBoxs[i]);
			}
		}
	}

	class ImgCheckListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println("image Click");
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				imgCheck = 0;
				return;
			}
			if (imgRadioBtns[0].isSelected()) {
				imgCheck = 1;
			} else if (imgRadioBtns[1].isSelected()) {
				imgCheck = 2;
			} else if (imgRadioBtns[2].isSelected()) {
				imgCheck = 3;
			} else if (imgRadioBtns[3].isSelected()) {
				imgCheck = 4;
			} else if (imgRadioBtns[4].isSelected()) {
				imgCheck = 5;
			} else if (imgRadioBtns[5].isSelected()) {
				imgCheck = 6;
			} else if (imgRadioBtns[6].isSelected()) {
				imgCheck = 7;
			} else if (imgRadioBtns[7].isSelected()) {
				imgCheck = 8;
			} else if (imgRadioBtns[8].isSelected()) {
				imgCheck = 9;
			}
			System.out.println(imgCheck);
		}
	}

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
