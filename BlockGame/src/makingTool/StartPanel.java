package makingTool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
	
	public StartPanel(int width, int height){
		setLayout(null);
		setSize(width, height);
		
		JButton gameBtn = new JButton("게임");
		JButton editBtn = new JButton("편집");
		JButton exitBtn = new JButton("종료");
		
		gameBtn.setBounds(this.getWidth()/2-100,50,150,50);
		editBtn.setBounds(this.getWidth()/2-100,150,150,50);
		exitBtn.setBounds(this.getWidth()/2-100,250,150,50);
		
		gameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){ 	
				
			}
		});
		
		editBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){ 	
			}
		});
		
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){ 	
				System.exit(1);
			}
		});
			
		add(gameBtn);
		add(editBtn);
		add(exitBtn);		
		
		setVisible(true);
	}
}
