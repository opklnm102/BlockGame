package block;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Block extends JLabel {
	Image img;
	ImageIcon icon;
	int type;

	public Block(int x, int y, int w, int h, String src, int type) {
		this.setBounds(x, y, w, h);
		this.type = type;
		icon = new ImageIcon(src);
		this.img = icon.getImage();
	}
	
	public void setImg(String src){
		icon = new ImageIcon(src);
		this.img = icon.getImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public int getType() {
		return type;
	}
}