package controller;

import yunheTang.model.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.filechooser.FileSystemView;
import org.junit.Test;
import yunheTang.view.MainForm;

public class TestLoadFromFileController {

	@Test
	public void testAct() {
		
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File homeDir = fsv.getHomeDirectory();
		File f = new File(homeDir.getPath()+"\\test.csv");
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(f, true), 'w');
			
			for ( int i=0; i<10; i++) {
				bw.write("illegal,point");
				bw.newLine();
			}
		} catch(Exception e) {
		}
		
		Model model = new Model();
		MainForm mainForm = new MainForm(model);
		LoadFromFileController controller = new LoadFromFileController(model);
	}
}
