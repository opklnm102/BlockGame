import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

import makingTool.EditPanel;

public class Test extends JFrame  {
	Container c;
	JLabel la = new JLabel();

	Test() {	
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		c = getContentPane();
		
		c.add(new EditPanel(1100, 900));		

		setSize(1100, 900);

		setVisible(true);
	}	

	public static void main(String[] args) {
		new Test();
	}
}



