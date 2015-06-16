package makingTool;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import block.Block;

public class BlockEditDialogFrame extends JFrame {
	BlockEditDialog blockEditDialog;

	BlockEditDialogFrame() {
		super("Dialog Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton btn = new JButton("show dialog");

		blockEditDialog = new BlockEditDialog(this, "블록 편집");

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				blockEditDialog.setVisible(true);
			}
		});

		getContentPane().add(btn);
		setSize(250, 200);
		setVisible(true);
	}

	class BlockEditDialog extends JDialog {
		JButton okBtn;
		JButton closeBtn;

		BlockEditDialog(JFrame frame, String title) {
			super(frame, title);
			setLayout(null);
			setSize(450, 550);

			JTabbedPane pane = createTabbedPane();
			okBtn = new JButton("Ok");
			closeBtn = new JButton("Close");
			
			pane.setBounds(10, 10, 410, 450);
			okBtn.setBounds(280 ,474 ,66,25);
			closeBtn.setBounds(352,474,66,25);
			
			add(pane);
			add(okBtn);
			add(closeBtn);  //리스너 만들기

			okBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}

		JTabbedPane createTabbedPane() {
			JTabbedPane pane = new JTabbedPane();
			pane.addTab("Image", new ImagePanel());
			pane.addTab("Item", new ItemPanel());

			return pane;
		}
	}

	class ImagePanel extends JPanel {
		Block blocks[];
		
		ImagePanel() {
			setLayout(null);
			blocks = new Block[10];
			
			for(int i=0, h=0; i<blocks.length; i++){
				if(i>7)
					h = 140;
				else if(i>3)
					h =70;							
				System.out.println((20+100*(i%4)) +", " + (h+50));
				blocks[i] = new Block(20+100*(i%4), h+30, 65, 39, EditPanel.imgSources[i],
						"images/item1.png", i+1);
				add(blocks[i]);
			}		
			//setVisible(true);
		}
	}
	

	class ItemPanel extends JPanel {
		
		ItemPanel() {			
			
		}
	}

	public static void main(String[] args) {
		new BlockEditDialogFrame();
	}
}
