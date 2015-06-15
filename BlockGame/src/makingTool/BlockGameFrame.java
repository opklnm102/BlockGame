package makingTool;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.Node;

public class BlockGameFrame extends JFrame {
	Container c;
	String w, h;
	JMenuItem editOnItem;
	JMenuItem editOffItem; 

	public BlockGameFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(null);

		createMenu();

		defaultFileOpen();

		// setResizable(false);
		setVisible(true);
	}

	public void defaultFileOpen() {
		XMLReader xml = new XMLReader("default_screen.xml");
		Node blockGameNode = xml.getBlockGameElement();
		Node sizeNode = XMLReader.getNode(blockGameNode, XMLReader.E_SIZE);
		w = XMLReader.getAttr(sizeNode, "w");
		h = XMLReader.getAttr(sizeNode, "h");
		setSize(Integer.parseInt(w), Integer.parseInt(h));

		c.add(new GamePanel(xml.getGamePanelElement(),Integer.parseInt(w), Integer.parseInt(h)));

		// setContentPane(new GamePanel(xml.getGamePanelElement()));
	}

	public void FileOpen(String filePath) {
		// defaultFileOpen();

		XMLReader xml = new XMLReader(filePath);
		Node blockGameNode = xml.getBlockGameElement();
		Node sizeNode = XMLReader.getNode(blockGameNode, XMLReader.E_SIZE);
		w = XMLReader.getAttr(sizeNode, "w");
		h = XMLReader.getAttr(sizeNode, "h");
		setSize(Integer.parseInt(w), Integer.parseInt(h));

		c.add(new GamePanel(xml.getGamePanelElement(),Integer.parseInt(w), Integer.parseInt(h)));
		// setContentPane(new GamePanel(xml.getGamePanelElement()));
	}

	public void FileSave(String filePath) {
		XMLWriter xml = new XMLWriter(filePath);
	}

	public void createMenu() {
		// 메뉴
		JMenuBar mb = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu runMenu = new JMenu("Run");
		
		runMenu.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {	
				System.out.println("run");
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {				
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {				
			}
		});
		
		
		JMenuItem newItem = new JMenuItem("New");
		JMenuItem openItem = new JMenuItem("Open");
		JMenuItem saveItem = new JMenuItem("Save");
		JMenuItem exitItem = new JMenuItem("Exit");
		
		editOnItem = new JMenuItem("On");
		editOffItem = new JMenuItem("Off");
		editOffItem.setEnabled(false);

		FileActionListener fileMennuListener = new FileActionListener();

		openItem.addActionListener(fileMennuListener);
		saveItem.addActionListener(fileMennuListener);
		exitItem.addActionListener(fileMennuListener);

		EditActionListener editActionListener = new EditActionListener();
		
		editOnItem.addActionListener(editActionListener);		
		editOffItem.addActionListener(editActionListener);

		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		editMenu.add(editOnItem);
		editMenu.add(editOffItem);

		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(runMenu);
		setJMenuBar(mb);
	}
	
	class EditActionListener implements ActionListener{
		EditPanel editPanel;
		
		EditActionListener(){
			editPanel = null;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("edit");
			JMenuItem selectItem = (JMenuItem) e.getSource();
			String name = selectItem.getText();
			
			if(name.equals("On")){
				editPanel = new EditPanel(Integer.parseInt(w), Integer.parseInt(w));
				setSize(Integer.parseInt(w) + 300, Integer.parseInt(w) );
				c.add(editPanel);
				editOffItem.setEnabled(true);
				editOnItem.setEnabled(false);
			}else if(name.equals("Off")){
				c.remove(editPanel);
				setSize(Integer.parseInt(w), Integer.parseInt(h));
				editPanel = null;	
				editOffItem.setEnabled(false);
				editOnItem.setEnabled(true);
			}
		}	
	}

	class FileActionListener implements ActionListener {
		JFileChooser chooser;

		FileActionListener() {
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem selectItem = (JMenuItem) e.getSource();
			String name = selectItem.getText();

			System.out.println(name);

			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML Files", "xml");

			chooser.setFileFilter(filter);

			if (name.equals("New")) { // default 화면 보여주기
				defaultFileOpen();
			} else if (name.equals("Open")) {
				int ret = chooser.showOpenDialog(null);

				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.",
							"경고", JOptionPane.WARNING_MESSAGE);

					return;
				}else{
					String filePath = chooser.getSelectedFile().getPath();
					// System.out.println(filePath);

					FileOpen(filePath);
				}
			} else if (name.equals("Save")) {
				int ret = chooser.showSaveDialog(null);
				
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 저장하지 않았습니다.",
							"경고", JOptionPane.WARNING_MESSAGE);

					return;
				}else{
					String filePath = chooser.getSelectedFile().getPath();
					FileSave(filePath);
				}
			} else if (name.equals("Exit")) {
				System.exit(1);
			}
		}
	}

	public static void main(String[] args) {
		new BlockGameFrame("Block Game");
	}
}
