package controller;

import yunheTang.model.*;
import yunheTang.view.*;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

public class SaveToFileController {
	
	Model model;
	
	public SaveToFileController(Model model) {
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
		//fc.addChoosableFileFilter(ff);
		fc.setMultiSelectionEnabled(false);
		int result = fc.showOpenDialog(new JFrame());
		
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			File saveFile = null;
			if (!file.getName().toLowerCase().endsWith(".csv")) {
				saveFile = new File(file.getPath()+".csv");
			} else {
				saveFile = new File(file.getPath());
			}
			model.saveToFile(saveFile);
			
			return true;
		} else {
			return false;
		}
	}
}
