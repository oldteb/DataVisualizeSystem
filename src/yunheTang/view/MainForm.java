package yunheTang.view;

import controller.*;
import yunheTang.model.*;
import yunheTang.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class MainForm extends JPanel {
	JTable table;
	JPanel panel;
	
	Model model;
	private JTextField textFieldX;
	private JTextField textFieldY;
	
	JButton deleteDataButton;
	JButton editButton;
	JButton showTrendLineButton;
	JButton showEquationButton;
	JButton cartisanButton;
	JButton saveDataButton;
	JButton loadDataButton;
	JButton showHorizontalLineButton;
	JButton showXYAxesButton;
	private JButton columnButton;
	private JButton horizontalBarButton;
	private JButton multipleLinesButton;
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("deprecation")
	public MainForm(Model model) {
		
		this.model = model;
		
		String[] columnNames = { "X","Y" }; 
		Object[][] data = null;
		
		
		this.addMouseListener( new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				table.getSelectionModel().clearSelection();
				MainForm.this.editButton.setEnabled(false);
				MainForm.this.deleteDataButton.setEnabled(false);
				textFieldX.setText("");
				textFieldY.setText("");
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		
		this.table = new JTable( new DefaultTableModel(data,columnNames));
		this.table.addMouseListener( new MouseListener(){
			public void mouseClicked(MouseEvent e){
				int row = MainForm.this.table.getSelectedRow();
				if(row != -1){
					textFieldX.setText(Double.toString(MainForm.this.model.getXOfIndex(row)));
					textFieldY.setText(Double.toString(MainForm.this.model.getYOfIndex(row)));
					MainForm.this.deleteDataButton.setEnabled(true);
					MainForm.this.editButton.setEnabled(true);
				}
			}
			public void mouseReleased(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
		});
		this.table.addMouseMotionListener( new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0) {	
				MainForm.this.deleteDataButton.setEnabled(true);
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {				
			}
		});
		
		
		JButton addDataButton = new JButton("Add Data");
		// have controller handle this.
		addDataButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AddDataController(MainForm.this.model).act(MainForm.this);				
			}
					
		});
				
		JButton editDataButton = new JButton("Edit Data");
		editButton = editDataButton;
		editDataButton.setEnabled(false);
		// have controller handle this.
		editDataButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new EditDataController(MainForm.this.model).act(MainForm.this);				
			}
					
		});
		
		deleteDataButton = new JButton("Delete Data");
		deleteDataButton.setEnabled(false);
		// have controller handle this.
		deleteDataButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DeleteDataController(MainForm.this.model).act(MainForm.this);				
			}
					
		});
		
		textFieldX = new JTextField();
		textFieldX.setColumns(10);
		
		textFieldY = new JTextField();
		textFieldY.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		panel = new Panel(model);
		//JPanel panel = new JPanel();
		//JPanel CartesianGraph = new JPanel();
		
		cartisanButton = new JButton("CartisanGraph");
		cartisanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CartesianGraphController(MainForm.this.model).act(MainForm.this);				
			}		
		});
		
		columnButton = new JButton("ColumnGraph");
		columnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ColumnGraphController(MainForm.this.model).act(MainForm.this);
			}
		});
		
		horizontalBarButton = new JButton("HorizontalBarGraph");
		horizontalBarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new HorizontalBarGraphController(MainForm.this.model).act(MainForm.this);
			}
		});
		
		multipleLinesButton = new JButton("MultipleLinesGraph");
		multipleLinesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MultipleLinesGraphController(MainForm.this.model).act(MainForm.this);
			}
		});
		
		showHorizontalLineButton = new JButton("ShowHorizontalLine");
		showHorizontalLineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ShowHorizontalLineController(MainForm.this.model).act(MainForm.this);	
			}
		});
		
		showXYAxesButton = new JButton("ShowXYAxes");
		showXYAxesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ShowXYAxesController(MainForm.this.model).act(MainForm.this);				
			}
					
		});
		
		showTrendLineButton = new JButton("ShowTrendLine");
		showTrendLineButton.setEnabled(false);
		// have controller handle this.
		showTrendLineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ShowTrendLineController(MainForm.this.model).act(MainForm.this);				
			}
					
		});
		
		showEquationButton = new JButton("ShowLinearEquation");
		showEquationButton.setEnabled(false);
		// have controller handle this.
		showEquationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ShowEquationController(MainForm.this.model).act(MainForm.this);				
			}
					
		});
		
		JLabel lblNewLabel = new JLabel("X:");
		
		JLabel lblNewLabel_1 = new JLabel("Y:");
		
		saveDataButton = new JButton("Save");
		saveDataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new SaveToFileController(MainForm.this.model).act(MainForm.this);				
			}	
		});
		
		loadDataButton = new JButton("Load");
		loadDataButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoadFromFileController(MainForm.this.model).act(MainForm.this);				
			}	
		});
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
							.addGap(75)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel)
										.addGap(6)
										.addComponent(textFieldX, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
										.addGap(15)
										.addComponent(lblNewLabel_1)
										.addGap(6)
										.addComponent(textFieldY, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(addDataButton)
										.addGap(9)
										.addComponent(editDataButton, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
										.addGap(9)
										.addComponent(deleteDataButton)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(saveDataButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(loadDataButton)))
							.addGap(75)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(multipleLinesButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(horizontalBarButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cartisanButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(columnButton, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(showEquationButton, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(showTrendLineButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(showXYAxesButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(showHorizontalLineButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(62))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel))
						.addComponent(textFieldX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_1))
						.addComponent(textFieldY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(cartisanButton)
							.addComponent(showHorizontalLineButton)))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(addDataButton)
								.addComponent(editDataButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(saveDataButton)
								.addComponent(loadDataButton)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(deleteDataButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(columnButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(horizontalBarButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(multipleLinesButton))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(showXYAxesButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(showTrendLineButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(showEquationButton)))))
					.addGap(666))
		);
		setLayout(groupLayout);

	}

	public JTextField getJTextFieldX(){
		return this.textFieldX;
	}
	public JTextField getJTextFieldY(){
		return this.textFieldY;
	}
	public JTable getJTable(){
		return this.table;
	}
	public JPanel getJPanel(){
		return this.panel;
	}
	public JTable getTable(){
		return this.table;
	}
	public JButton getDeleteButton(){
		return this.deleteDataButton;
	}
	public JButton getEditButton(){
		return this.editButton;
	}
	public JButton getTrendLineButton(){
		return this.showTrendLineButton;
	}
	public JButton getEquationButton(){
		return this.showEquationButton;
	}
	public JButton getHorizontalLineButton(){
		return this.showHorizontalLineButton;
	}
	public JButton getXYAxesButton(){
		return this.showXYAxesButton;
	}
	
	private void setTableEditable(){
		
	}
}
