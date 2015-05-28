package yunheTang.view;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JApplet;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import yunheTang.model.*;

public class Main extends JApplet {

	Model model = new Model();
	JPanel panel1;

	
	/**
	 * Create the applet.
	 */
	public Main() {
		
		this.setSize(5000, 3000);
		
		JPanel panel1 = new MainForm(model);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 930, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(945, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(648, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
