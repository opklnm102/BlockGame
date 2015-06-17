package makingTool;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.Node;

import common.Map;

public class BlockGameFrame extends JFrame {
	static Container c;
	String w, h;
	JMenuItem editMapItem;
	MapEditDialog mapEditDoalog;
	static StartPanel startpanel;
	EditPanel editPanel;
	Map map;
	static JFrame frame;

	GamePanel gamePanel;

	static int mode;

	public BlockGameFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(null);

		createMenu();

		map = new Map();
		frame = this;
		
		setSize(1100, 900);
		editPanel = new EditPanel(1100, 900);
		c.add(editPanel);

		// setSize(800,900);
		// startpanel = new StartPanel(this,800, 900);
		// c.add(startpanel);

		// defaultFileOpen();

		// setResizable(false);
		setVisible(true);
	}

	public static void removeStartPanel(int select) {
		c.remove(startpanel);
		mode = select;
		if (mode == 1) { // 게임모드
			frame.add(new GamePanel(800, 900));
			frame.setSize(800, 900);
		} else if(mode == 2) { // 편집모드
			frame.add(new EditPanel(1100, 900));
			frame.setSize(1100, 900);
		}
	}

	public void defaultFileOpen() {
		XMLReader xml = new XMLReader("default_screen.xml");
		Node blockGameNode = xml.getBlockGameElement();
		Node sizeNode = XMLReader.getNode(blockGameNode, XMLReader.E_SIZE);
		w = XMLReader.getAttr(sizeNode, "w");
		h = XMLReader.getAttr(sizeNode, "h");
		setSize(Integer.parseInt(w), Integer.parseInt(h));

		c.add(new GamePanel(xml.getGamePanelElement(), Integer.parseInt(w),
				Integer.parseInt(h)));

		repaint();
		// setContentPane(new GamePanel(xml.getGamePanelElement()));
	}

	public void FileOpen(String filePath) {
		// defaultFileOpen();

		XMLReader xml = new XMLReader(filePath);
		Node blockGameNode = xml.getBlockGameElement();
		Node sizeNode = XMLReader.getNode(blockGameNode, XMLReader.E_SIZE);
		w = XMLReader.getAttr(sizeNode, "w");
		h = XMLReader.getAttr(sizeNode, "h");
		// setSize(Integer.parseInt(w), Integer.parseInt(h));

		c.add(new GamePanel(xml.getGamePanelElement(), Integer.parseInt(w),
				Integer.parseInt(h)));

		repaint();
		// setContentPane(new GamePanel(xml.getGamePanelElement()));
	}

	public void FileSave(String filePath) {
		XMLWriter xml = new XMLWriter(filePath, editPanel.getBlockList(), map);

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

		editMapItem = new JMenuItem("Map");

		FileActionListener fileMennuListener = new FileActionListener();

		openItem.addActionListener(fileMennuListener);
		saveItem.addActionListener(fileMennuListener);
		exitItem.addActionListener(fileMennuListener);

		EditActionListener editActionListener = new EditActionListener();

		editMapItem.addActionListener(editActionListener);

		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		editMenu.add(editMapItem);

		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(runMenu);
		setJMenuBar(mb);
	}

	class EditActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("edit");

			// 다이얼로그 삽입
			createMapDialog();
			mapEditDoalog.setVisible(true);
		}
	}

	public void createMapDialog() {
		mapEditDoalog = new MapEditDialog(this, "Map Edit");
	}

	public void destroyMapDialog() {
		mapEditDoalog = null;
	}

	class FileActionListener implements ActionListener {
		JFileChooser chooser;
		FileNameExtensionFilter filter;

		FileActionListener() {
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("."));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem selectItem = (JMenuItem) e.getSource();
			String name = selectItem.getText();

			System.out.println(name);

			if (name.equals("New")) { // default 화면 보여주기
				defaultFileOpen();
			} else if (name.equals("Open")) {
				filter = new FileNameExtensionFilter("XML Files", "xml");

				chooser.setFileFilter(filter);
				int ret = chooser.showOpenDialog(null);

				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.",
							"경고", JOptionPane.WARNING_MESSAGE);

					return;
				} else {
					String filePath = chooser.getSelectedFile().getPath();
					// System.out.println(filePath);

					FileOpen(filePath);
				}
			} else if (name.equals("Save")) {
				filter = new FileNameExtensionFilter("XML Files", "xml");

				chooser.setFileFilter(filter);

				int ret = chooser.showSaveDialog(null);

				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "파일을 저장하지 않았습니다.",
							"경고", JOptionPane.WARNING_MESSAGE);

					return;
				} else {
					String filePath = chooser.getSelectedFile().getPath();
					FileSave(filePath);
				}
			} else if (name.equals("Exit")) {
				System.exit(1);
			}
		}
	}

	class MapEditDialog extends JDialog {
		JButton okBtn;
		JButton closeBtn;
		JTextField bgTf;
		JTextField bgmTf;

		MapEditDialog(JFrame frame, String title) {
			super(frame, title);
			setLayout(null);
			setSize(450, 300);

			okBtn = new JButton("Ok");
			closeBtn = new JButton("Close");

			// pane.setBounds(10, 10, 410, 450);
			okBtn.setBounds(280, 224, 66, 25);
			closeBtn.setBounds(352, 224, 66, 25);

			add(new MapEditPanel());
			add(okBtn);
			add(closeBtn); // 리스너 만들기

			okBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String bgFilePath = bgTf.getText().toString();
					String bgmFilePath = bgmTf.getText().toString();

					System.out.println(bgFilePath + ", " + bgmFilePath);
					map.setBgImg(bgFilePath);
					map.setBgSound(bgmFilePath);

					editPanel.mapPanel.setMap(map);

					setVisible(false);
				}
			});

			closeBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}

		class MapEditPanel extends JPanel {

			MapEditPanel() {
				setBounds(10, 10, 410, 210);
				setLayout(null);
				setBorder(BorderFactory.createEtchedBorder());

				JLabel bgLa = new JLabel("BG Image");
				JLabel bgmLa = new JLabel("BGM");
				bgTf = new JTextField(10);
				bgmTf = new JTextField(10);
				JButton bgBtn = new JButton("BG Image");
				JButton bgmBtn = new JButton("BGM");

				bgLa.setBounds(20, 50, 70, 20);
				bgmLa.setBounds(20, 110, 70, 20);

				bgTf.setBounds(100, 50, 200, 20);
				bgmTf.setBounds(100, 110, 200, 20);

				bgBtn.setBounds(310, 50, 40, 20);
				bgmBtn.setBounds(310, 110, 40, 20);

				bgBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new File("."));

						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"Image Files", "jpg", "png");

						chooser.setFileFilter(filter);

						int ret = chooser.showSaveDialog(null);

						if (ret != JFileChooser.APPROVE_OPTION) {
							JOptionPane.showMessageDialog(null,
									"파일을 저장하지 않았습니다.", "경고",
									JOptionPane.WARNING_MESSAGE);

							return;
						} else {
							String filePath = chooser.getSelectedFile()
									.getPath();
							bgTf.setText(filePath);
						}
					}
				});

				bgmBtn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser();
						chooser.setCurrentDirectory(new File("."));

						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"sound Files", "mp3", "wma");

						chooser.setFileFilter(filter);

						int ret = chooser.showSaveDialog(null);

						if (ret != JFileChooser.APPROVE_OPTION) {
							JOptionPane.showMessageDialog(null,
									"파일을 저장하지 않았습니다.", "경고",
									JOptionPane.WARNING_MESSAGE);

							return;
						} else {
							String filePath = chooser.getSelectedFile()
									.getPath();
							bgmTf.setText(filePath);
						}
					}
				});

				add(bgLa);
				add(bgmLa);
				add(bgTf);
				add(bgmTf);
				add(bgBtn);
				add(bgmBtn);
			}
		}
	}

	public static void main(String[] args) {
		new BlockGameFrame("Block Game");
	}
}
