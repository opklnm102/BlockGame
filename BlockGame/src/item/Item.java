package item;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Item extends JLabel{
	Image img;
	int type;
	
	public Item(int x, int y, int w, int h, String src, int type){
		this.setBounds(x, y, w, h);
		this.type = type;
		ImageIcon icon = new ImageIcon(src);
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
