
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import makingTool.EditPanel;

public class Test extends JFrame {
	Container c;
	JLabel la = new JLabel();

	Test() {
		c = getContentPane();
		
		c.add(new EditPanel(1100, 900));
		

		createMenu();
		setSize(1100, 900);
		setVisible(true);
	}

	public void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");

		openItem.addActionListener(new OpenActionListener());
		
		fileMenu.add(openItem);
		mb.add(fileMenu);
		this.setJMenuBar(mb);

	}

	class OpenActionListener implements ActionListener {
		JFileChooser chooser;

		OpenActionListener() {
			chooser = new JFileChooser();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("xml",
					"xml");

			chooser.setFileFilter(filter);

			int ret = chooser.showOpenDialog(null);

		}

	}

	public static void main(String[] args) {
		new Test();
	}
}
