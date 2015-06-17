package common;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import makingTool.BlockGameFrame;

public class StartPanel extends JPanel {
	ImageIcon bgImg;
	JLabel titleLa;
	JLabel gameLa;
	JLabel editLa;
	JLabel exitLa;

	public StartPanel(JFrame frame, int width, int height) {
		setLayout(null);
		setSize(width, height);

		Font f = new Font("Jokerman", Font.ITALIC, 60);
		Font titleF = new Font("Jokerman", Font.ITALIC, 100);

		titleLa = new JLabel("Maplenoid");
		gameLa = new JLabel("Game");
		editLa = new JLabel("Edit");
		exitLa = new JLabel("Exit");

		titleLa.setFont(titleF);
		gameLa.setFont(f);
		editLa.setFont(f);
		exitLa.setFont(f);

		bgImg = new ImageIcon("images/main.png");

		titleLa.setBounds(this.getWidth() / 2 - 280, 100, 600, 150);
		gameLa.setBounds(this.getWidth() / 2 - 100, 450, 200, 70);
		editLa.setBounds(this.getWidth() / 2 - 100, 550, 200, 70);
		exitLa.setBounds(this.getWidth() / 2 - 100, 650, 200, 70);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				if (300 <= x && x <= 450) {
					if (450 <= y && y <= 500) {
						BlockGameFrame.removeStartPanel(1);
					} else if (550 <= y && y <= 600) {
						BlockGameFrame.removeStartPanel(2);
					} else if (650 <= y && y <= 700) {
						System.exit(1);
					}
				}
			}
		});

		add(titleLa);
		add(gameLa);
		add(editLa);
		add(exitLa);

		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(bgImg.getImage(), 0, 0, this.getWidth(), this.getHeight(),
				this);

	}
}
