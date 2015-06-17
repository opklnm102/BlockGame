package block;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block extends JLabel {
	Image img;
	Image normalImg;
	Image clickImg;
	ImageIcon icon;	
	String src;
	int type;
	boolean isClick;

	public Block(int x, int y, int w, int h, String src, String clickSrc,
			int type) {
		this.setBounds(x, y, w, h);
		this.type = type;
		this.src = src;
		icon = new ImageIcon(src);
		this.normalImg = icon.getImage();
		icon = new ImageIcon(clickSrc);
		this.clickImg = icon.getImage();
		
		isClick = false;
		this.img = normalImg;
	}

	public Block(int x, int y, int w, int h, String src, int type) {
		this.setBounds(x, y, w, h);
		this.type = type;
		this.src = src;
		icon = new ImageIcon(src);
		this.normalImg = icon.getImage();
		clickImg = null;
		
		isClick = false;
		this.img = normalImg;
	}

	public void setImg(String src) {
		icon = new ImageIcon(src);
		this.img = icon.getImage();
	}

	public void clickChangeImg() {
			if (!isClick) {
				isClick = true;
				this.img =clickImg;
			} else {
				isClick = false;
				this.img = normalImg;
			}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public int getType() {
		return type;
	}
	
	public String getSrc(){
		return src;
	}
}