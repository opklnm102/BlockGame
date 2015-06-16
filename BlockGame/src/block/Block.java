package block;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block extends JLabel {
	Image img;
	ImageIcon normalIcon;
	ImageIcon clickIcon;
	int type;
	boolean isClick;

	public Block(int x, int y, int w, int h, String src, String clickSrc,
			int type) {
		this.setBounds(x, y, w, h);
		this.type = type;
		normalIcon = new ImageIcon(src);
		clickIcon = new ImageIcon(clickSrc);
		this.img = normalIcon.getImage();
		isClick = false;
	}

	public Block(int x, int y, int w, int h, String src, int type) {
		this.setBounds(x, y, w, h);
		this.type = type;
		normalIcon = new ImageIcon(src);
		clickIcon = null;
		this.img = normalIcon.getImage();
		isClick = false;
	}

	public void setImg(String src) {
		normalIcon = new ImageIcon(src);
		this.img = normalIcon.getImage();
	}

	public void clickChangeImg() {
		if (clickIcon != null) {
			if (!isClick) {
				isClick = true;
				this.img = clickIcon.getImage();
			} else {
				isClick = false;
				this.img = normalIcon.getImage();
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public int getType() {
		return type;
	}
}