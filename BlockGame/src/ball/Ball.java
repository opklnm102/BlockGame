package ball;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ball extends JLabel implements Runnable{
	Image img;
	int type;
	
	public Ball(int x, int y, int w, int h, ImageIcon icon, int type){
		this.setBounds(x, y, w, h);
		img = icon.getImage();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	@Override
	public void run() {
		try{
			
		}catch(Exception e){
			
		}
	}
}