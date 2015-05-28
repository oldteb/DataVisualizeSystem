package yunheTang.model;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Properties;

import javax.swing.JPanel;

import dataset.IGraph;


public abstract class Graph implements IGraph{
	
	public void draw(Graphics g, JPanel panel){}
	
	void drawHorizontalLine(Graphics g, int x) {
		//Draw horizontal line.
		Graphics2D g3d = (Graphics2D)g;
		Stroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT,
                             BasicStroke.JOIN_BEVEL, 0,
                             new float[]{2, 6}, 0);
		g3d.setStroke(bs);
        
        int d = x/24;
        for(int i = 0; i < d; i++){
        	g3d.drawLine(0, x-(i+1)*24, 340, x-(i+1)*24);
        }
        for(int i = 0; i < 6-d; i++){
        	g3d.drawLine(0, x+(i+1)*24, 340, x+(i+1)*24);
        }
		
	}	
}
