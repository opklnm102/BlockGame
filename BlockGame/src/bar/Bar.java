package bar;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bar extends JLabel{
	Image img;
	int type;
	
	public Bar(int x, int y, int w, int h, ImageIcon icon, int type){
		this.setBounds(x, y, w, h);
		img = icon.getImage();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}