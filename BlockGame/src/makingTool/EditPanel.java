package makingTool;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import block.Block;

public class EditPanel extends JPanel {
	Block block1, block2, block3, block4, block5, block6, block7, block8,
			block9, block10;
	MyMouseListener listener;

	public EditPanel(int width, int height) {
		setLayout(null);
		setSize(width, height);

		BlockPanel blockPanel = new BlockPanel();
		add(blockPanel);

		setVisible(true);
	}

	class BlockPanel extends JPanel {

		public BlockPanel() {
			setLayout(null);
			setBackground(new Color(153, 204, 230));

			// 블럭 생성
			block1 = new Block(20, 20, 50, 30, "images/block1.png", 1);
			block2 = new Block(20, 70, 50, 30, "images/block2.png", 1);
			block3 = new Block(20, 120, 50, 30, "images/block3.png", 1);
			block4 = new Block(20, 170, 50, 30, "images/block4.png", 1);
			block5 = new Block(20, 220, 50, 30, "images/block5.png", 1);
			block6 = new Block(20, 270, 50, 30, "images/block6.png", 1);
			block7 = new Block(20, 320, 50, 30, "images/block1.png", 1);
			block8 = new Block(20, 370, 50, 30, "images/block2.png", 1);
			block9 = new Block(20, 420, 50, 30, "images/block3.png", 1);
			block10 = new Block(20, 470, 50, 30, "images/block4.png", 1);

			listener = new MyMouseListener();

			block1.addMouseListener(listener);
			block1.addMouseMotionListener(listener);

			add(block1);
			add(block2);
			add(block3);
			add(block4);
			add(block5);
			add(block6);
			add(block7);
			add(block8);
			add(block9);
			add(block10);

			setBounds(800, 0, 300, 900);
			setVisible(true);
		}
	}

	class MyMouseListener implements MouseListener, MouseMotionListener {
		int x, y;
		Block block;

		@Override
		public void mouseDragged(MouseEvent e) {
			x = e.getX();
			y = e.getY();

			block.changXY(x, y);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			block = (Block) e.getSource();
			block.setImg("images/block6.png");

			x = e.getX();
			y = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
