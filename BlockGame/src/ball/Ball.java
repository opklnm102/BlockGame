package ball;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ball extends JLabel {
	Image img;
	int type, BallX, BallY, Vec=6;
	int BX = 0, BY=0, check=1;
	public void setVec(int vec) {
		Vec = vec;
	}
	public int getVec() {
		return Vec;
	}

	public void Loca() {

		if (Vec==1) {
			BX=2;
			BY=-4;
		}
		if (Vec==2) {
			BX=4;
			BY=-2;
		}
		if (Vec==3) {
			BX=6;
			BY=0;
		}
		if (Vec==4) {
			BX=4;
			BY=2;
		}
		if (Vec==5) {
			BX=2;
			BY=4;
		}
		if (Vec==6) {
			BX=0;
			BY=6;
		}
		if (Vec==7) {
			BX=-2;
			BY=4;
		}
		if (Vec==8) {
			BX=-4;
			BY=2;
		}
		if (Vec==9) {
			BX=-6;
			BY=0;
		}
		if (Vec==10) {
			BX=-4;
			BY=-2;
		}
		if (Vec==11) {
			BX=-2;
			BY=-4;
		}
		if (Vec==12) {
			BX=0;
			BY=-6;
		}
	}
	public Ball(int x, int y, int w, int h, ImageIcon icon){
		this.setBounds(x, y, w, h);
		img = icon.getImage();
		
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(getWidth());
				while(true){
					BallX = getX();
					BallY = getY();
					Loca();
					setLocation(BallX+BX, BallY+BY);
					repaint();
					if (BallX+60>800) {
						if (Vec==1) Vec=11;
						if (Vec==2) Vec=10;
						if (Vec==3) Vec=9;
						if (Vec==4) Vec=8;
						if (Vec==5) Vec=7;
					}
					else if (BallX<=0) {
						if (Vec==11) Vec=1;
						if (Vec==10) Vec=2;
						if (Vec==9) Vec=3;
						if (Vec==8) Vec=4;
						if (Vec==7) Vec=5;
					}
					else if (BallY+60>900) {
						setLocation(400, 710);
					}
					else if (BallY<=0){
						if (Vec==10) Vec=8;
						if (Vec==11) Vec=7;
						if (Vec==12) Vec=6;
						if (Vec==1) Vec=5;
						if (Vec==2) Vec=4;
					}
					//System.out.println(VecX+", "+VecY);
					//System.out.println(BallX+", "+BallY);
					
					try {
						Thread.sleep(6);
					} catch (Exception e) {
					}
				}
				
			}
		});		
		th.start();
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}