package yunheTang.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Properties;

import javax.swing.JPanel;

import dataset.ICommonProperties;
import dataset.IDataSet;
import dataset.IGraph;

public class MultipleLinesGraph implements IGraph{
	IDataSet model;
	public Properties properties = new Properties();
	
	double max;
	double min;
	double k;
	int size;
	int interval;
	int currNum;
	int currPosition;
	int columnWidth;
	String[] RGB;
	Integer R;
	Integer G;
	Integer B;
	
	int baseX = 85;
	
	public MultipleLinesGraph(){}
	
	boolean setDomain(){
		max = Math.max(model.getMaxX(), model.getMaxY());
		min = Math.min(model.getMinX(), model.getMinY());
		return true;
	}
	
	void drawXYAxes(Graphics g){
	//60~300---240
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2.0F));
		
		
		if(max < 0){
			baseX = 20;
			k = ((double)130)/Math.abs(min);
		}
		else if(min > 0){
			baseX = 150;
			k = ((double)130)/Math.abs(max);
		}
		else{
			baseX = 85;
			k = ((double)65)/Math.max(max, Math.abs(min));
		}
		
		g2d.drawLine(0, baseX, 340, baseX);
		g2d.drawLine(340, baseX, 330, baseX-5);
		g2d.drawLine(340, baseX, 330, baseX+5);
		
		g2d.drawLine(40, 0, 40, 170);
		g2d.drawLine(40, 0, 30, 5);
		g2d.drawLine(40, 0, 50, 5);
	}
	
	
	void drawSegment(Graphics g, int i, double xb, double yb, double xe, double ye){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2.0F));

		g.fillOval(currPosition+interval-2, baseX-(int)(xe*k)-2, 4, 4);
		g.fillOval(currPosition+interval-2, baseX-(int)(ye*k)-2, 4, 4);
		
		/*String s = properties.getProperty("color1");
		RGB = s.split(",");
		R = Integer.valueOf(RGB[0]);
		G = Integer.valueOf(RGB[1]);
		B = Integer.valueOf(RGB[2]);
		*/
		g2d.setColor(new Color(255,0,0));
		g2d.drawLine(currPosition-2*columnWidth, baseX-(int)(xb*k), currPosition+interval, baseX-(int)(xe*k));
		/*s = properties.getProperty("color2");
		RGB = s.split(",");
		R = Integer.valueOf(RGB[0]);
		G = Integer.valueOf(RGB[1]);
		B = Integer.valueOf(RGB[2]);*/

		g2d.setColor(new Color(128,0,255));
		g2d.drawLine(currPosition-2*columnWidth, baseX-(int)(yb*k), currPosition+interval, baseX-(int)(ye*k));
		g2d.setColor(new Color(0,0,0));
		drawXYAxesMark(g,i);
		
		currPosition = currPosition+interval+2*columnWidth;
		
	}
	
	void drawXYAxesMark(Graphics g, int i){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(2.0F));
		if(properties.getProperty(ICommonProperties.xAxisLabel,"invalid").equals("true")){	//..............
			g2d.drawLine(currPosition+interval, baseX, currPosition+interval, baseX+6);
			if(properties.getProperty("hasXYAxesNumber","invalid").equals("true")){
				g2d.drawString(Integer.toString(i), currPosition+interval, baseX+15);
			}
		}
	}
	
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
	
	
	@Override
	public void draw(Graphics g, JPanel panel){
		
		setDomain();

		// draw frame.
		g.drawLine(0,0,0,170);
		g.drawLine(0,0,340,0);
		g.drawLine(0,170,340,170);
		g.drawLine(340,0,340,170);
		
		//draw x/y axes.
		drawXYAxes(g); 
		
		if(model.size() == -1){
			return;
		}
		else{
			size = model.size();
		}
		
		if(size > 15){
			properties.setProperty("hasXYAxesNumber", "false");
		}
		else{
			properties.setProperty("hasXYAxesNumber", "true");
		}
		
		columnWidth = (int)(-size+20);
		if(columnWidth < 1){
			columnWidth = 1;
		}
		
		interval = (240-size*2*columnWidth)/(size+1);
		if(interval < 1){
			interval = 1;
		}

		
		currNum = 0;
		currPosition = 60;
		if(model.size() == 1){
			g.fillOval(currPosition+interval-2, baseX-(int)(model.getCoordinate(0, 0)*k)-2, 4, 4);
			g.fillOval(currPosition+interval-2, baseX-(int)(model.getCoordinate(0, 1)*k)-2, 4, 4);
			drawXYAxesMark(g,1);
		}
		else if( model.size() > 0){
			g.fillOval(currPosition+interval-2, baseX-(int)(model.getCoordinate(0, 0)*k)-2, 4, 4);
			g.fillOval(currPosition+interval-2, baseX-(int)(model.getCoordinate(0, 1)*k)-2, 4, 4);
			drawXYAxesMark(g,1);
			currPosition = currPosition+interval+2*columnWidth;
			
			for(int i = 1; i < size ; i++){
				drawSegment(g,i+1,model.getCoordinate(i-1, 0),model.getCoordinate(i-1, 1), model.getCoordinate(i, 0),model.getCoordinate(i, 1));
			}
		}
		if(properties.getProperty(ICommonProperties.horizontalLines,"invalid").equals("true")){//.............
	        drawHorizontalLine(g,baseX);
		}

	}
	
	
	@Override
	public void setDataSet(IDataSet ds) {
		// TODO Auto-generated method stub
		this.model = ds;
	}

	@Override
	public void setProperties(Properties p) {
		// TODO Auto-generated method stub
		properties = p;
	}

}
