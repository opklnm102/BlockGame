package makingTool;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import block.Block;

public class EditPanel extends JPanel {

	public EditPanel() {
		setLayout(null);
		setBackground(new Color(153, 204, 230));

		// 블럭 생성
		Block block1 = new Block(20, 20, 50, 30, "images/block1.png", 1);
		Block block2 = new Block(20, 70, 50, 30, "images/block2.png", 1);
		Block block3 = new Block(20, 120, 50, 30, "images/block3.png", 1);
		Block block4 = new Block(20, 170, 50, 30, "images/block4.png", 1);
		Block block5 = new Block(20, 220, 50, 30, "images/block5.png", 1);
		Block block6 = new Block(20, 270, 50, 30, "images/block6.png", 1);
		Block block7 = new Block(20, 320, 50, 30, "images/block1.png", 1);
		Block block8 = new Block(20, 370, 50, 30, "images/block2.png", 1);
		Block block9 = new Block(20, 420, 50, 30, "images/block3.png", 1);
		Block block10 = new Block(20, 470, 50, 30, "images/block4.png", 1);

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

	class MyMouseListener implements MouseListener, MouseMotionListener {
		int x, y;

		@Override
		public void mouseDragged(MouseEvent e) {
			Object obj = e.getSource();
			x = e.getX();
			y = e.getY();
			repaint();
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
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
