package controller;

import yunheTang.model.*;
import yunheTang.view.*;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

public class LoadFromFileController {
	
	Model model;
	
	public LoadFromFileController(Model model) {
		this.model = model;
	}

	public boolean act(MainForm mainForm) {
		
		FileFilter ff = new FileFilter() {
			
			public boolean accept(File file){
				if (file.getName().toLowerCase().endsWith(".csv")) {
					return true;
				} else {
					return false;
				}
			}
			
			public String getDescription() {
				return ".csv";
			}			
		};
		
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File homeDir = fsv.getHomeDirectory();
		
		JFileChooser fc = new JFileChooser(homeDir);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.addChoosableFileFilter(ff);
		fc.setMultiSelectionEnabled(false);
		int result = fc.showOpenDialog(new JFrame());
		
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (file.getName().toLowerCase().endsWith(".csv")) {
				model.loadFromFile(file);
			} else {
				
			}

			// Refresh table.
			int s = model.size();
			DefaultTableModel table = (DefaultTableModel)mainForm.getJTable().getModel();
			for (int index = model.size() - table.getRowCount(); index > 0; index--) {
				String[] set = {Double.toString(model.getXOfIndex(s-index)),Double.toString(model.getYOfIndex(s-index))};
	            table.addRow(set);
			}
			
			// Refresh the graph.
			model.refreshRange();
			//((Panel)(mainForm.getJPanel())).getGraph().setDomain();
			mainForm.getJPanel().repaint();

			return true;
		} else {
			return false;
		}
	}
}
