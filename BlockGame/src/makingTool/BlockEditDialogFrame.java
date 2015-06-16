package makingTool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import block.Block;

public class BlockEditDialogFrame extends JFrame {
	BlockEditDialog blockEditDialog;
	Block block;
	int blockCheck = 0;
	int itemCheck = 0;

	BlockEditDialogFrame() {
		super("Dialog Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton btn = new JButton("show dialog");

		blockEditDialog = new BlockEditDialog(this, "블록 편집");

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				blockEditDialog.setVisible(true);
			}
		});

		getContentPane().add(btn);
		setSize(250, 200);
		setVisible(true);
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
		}

		JTabbedPane createTabbedPane() {
			JTabbedPane pane = new JTabbedPane();
			pane.addTab("Image", new ImagePanel());
			pane.addTab("Item", new ItemPanel());

			return pane;
		}
	}

	class ImagePanel extends JPanel {
		public final String blockSelectedImg[] = { "images/block1.png",
				"images/block2.png", "images/block3.png", "images/block4.png",
				"images/block5.png", "images/block6.png", "images/block1.png",
				"images/block2.png", "images/block3.png" };

		public final String blockImg[] = { "images/block1.png", "images/block2.png",
				"images/block3.png", "images/block4.png", "images/block5.png",
				"images/block6.png", "images/block1.png", "images/block2.png",
				"images/block3.png"};
		
		JRadioButton blockRadioBtns[] = new JRadioButton[9]; 

		ImagePanel() {
			setLayout(null);
			BlockCheckListener listener = new BlockCheckListener();  //왜있냐

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
				blockRadioBtns[i] = new JRadioButton(icon);
				blockRadioBtns[i].setBorderPainted(true);
				blockRadioBtns[i].setSelectedIcon(selectedIcon);
				
				//툴팁 달기
				
				blockRadioBtns[i].setBounds(20 + 100 * (i % 4), h + 30, 65, 39);
				g.add(blockRadioBtns[i]);
				add(blockRadioBtns[i]);
			}

			// setVisible(true);
		}
	}

	class ItemPanel extends JPanel {
		public final String itemSelectedImg[] = { "images/block1.png",
				"images/block2.png", "images/block3.png", "images/block4.png",
				"images/block5.png", "images/block6.png", "images/block1.png",
				"images/block2.png", "images/block3.png" };

		public final String itemImg[] = { "images/item1.png",
				"images/item2.png", "images/item3.png", "images/item4.png",
				"images/item5.png", "images/item6.png", "images/item7.png",
				"images/item8.png", "images/item9.png", "images/item10.png" };

		public final String itemName[] = { "생명줄 생성", "미사일", "자석", "관통 볼",
				"공속도 조절", "바속도 조절", "공크기 조절", "공 분열", "바길이 조절" };
		
		JCheckBox itemCheckBoxs[] = new JCheckBox[9];

		ItemPanel() {
			setLayout(null);
			
			for (int i = 0, h = 0; i < 9; i++) {
				if (i > 7)
					h = 140;
				else if (i > 3)
					h = 70;
				
				ImageIcon icon = new ImageIcon(itemImg[i]);
				ImageIcon selectedIcon = new ImageIcon(itemSelectedImg[i]);
				//JCheckBox itemCheckBox = new JCheckBox(itemName[i], icon);
				
				itemCheckBoxs[i] = new JCheckBox(icon);
				itemCheckBoxs[i].setBorderPainted(true);
				itemCheckBoxs[i].setSelectedIcon(selectedIcon);
				
				//툴팁달기
				
				System.out.println((20 + 100 * (i % 4)) + ", " + (h + 50));
				
				itemCheckBoxs[i].setBounds(20 + 100 * (i % 4), h + 30, 65, 39);
				add(itemCheckBoxs[i]);
			}
		}
	}

	class BlockCheckListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("block Click");
			block = (Block) e.getSource();

			if (blockCheck == 0) {
				System.out.println("check true");
				switch (block.getType()) {
				case 1:
					blockCheck = 1;
					break;
				case 2:
					blockCheck = 2;
					break;
				case 3:
					blockCheck = 3;
					break;
				case 4:
					blockCheck = 4;
					break;
				case 5:
					blockCheck = 5;
					break;
				case 6:
					blockCheck = 6;
					break;
				case 7:
					blockCheck = 7;
					break;
				case 8:
					blockCheck = 8;
					break;
				case 9:
					blockCheck = 9;
					break;
				}
				block.clickChangeImg();
				repaint();
			} else {
				block.clickChangeImg();
				repaint();
				blockCheck = 0;
			}
		}
	}

	public static void main(String[] args) {
		new BlockEditDialogFrame();
	}
}
