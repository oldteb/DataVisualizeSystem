package yunheTang.model;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Properties;

import javax.swing.JPanel;

import dataset.ICommonProperties;
import dataset.IDataSet;
import dataset.IGraph;

public class ColumnGraph extends Graph implements IGraph{
	IDataSet model;
	
	double max;
	double min;
	double k;
	int size;
	int interval;
	int currNum;
	int currPosition;
	int columnWidth;
	
	int baseX = 85;
	
	Properties properties = new Properties();
	
	public ColumnGraph(){}
	
	
	boolean setDomain(){
		max = Math.max(model.getMaxX(), model.getMaxY());
		min = Math.min(model.getMinX(), model.getMinY());
		return true;
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
		for(int i = 0; i < size ; i++){
			drawColumn(g,i+1,model.getCoordinate(i, 0), model.getCoordinate(i, 1));
		}
		
		if(properties.getProperty(ICommonProperties.horizontalLines,"invalid").equals("true")){//.............
	        drawHorizontalLine(g,baseX);
		}

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
	
	void drawColumn(Graphics g, int i, double x, double y){
		Graphics2D g3d = (Graphics2D)g;
		g3d.setStroke(new BasicStroke(2.0F));
			
		if(x > 0){
			g.drawRect(currPosition+interval, baseX-(int)(x*k), columnWidth, (int)(x*k));
		}
		else{
			g.drawRect(currPosition+interval, baseX, columnWidth, Math.abs((int)(x*k)));
		}
		currPosition += interval;
		
		if(y > 0){
			g.drawRect(currPosition+columnWidth, baseX-(int)(y*k), columnWidth, (int)(y*k));
		}
		else{
			g.drawRect(currPosition+columnWidth, baseX, columnWidth, Math.abs((int)(y*k)));
		}
		
		if(properties.getProperty(ICommonProperties.xAxisLabel,"invalid").equals("true")){	//..............
			g3d.drawLine(currPosition+columnWidth, baseX, currPosition+columnWidth, baseX+6);
			if(properties.getProperty("hasXYAxesNumber","invalid").equals("true")){
				g3d.drawString(Integer.toString(i), currPosition+columnWidth, baseX+15);
			}
		}

		currPosition += 2*columnWidth;
	}
	
	
	@Override
	public void setDataSet(IDataSet ds) {
		// TODO Auto-generated method stub
		this.model = (Model) ds;
	}

	@Override
	public void setProperties(Properties p) {
		// TODO Auto-generated method stub
		properties = p;
	}
	
}


