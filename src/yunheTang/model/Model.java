package yunheTang.model;
import dataset.ICommonProperties;
import dataset.IDataSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import javax.swing.JOptionPane;

public class Model implements IDataSet{
	
	ArrayList<Point> data = new ArrayList<Point>();
	Range range = new Range();
	Properties properties = new Properties();
	

	public int addPoint(Point point){
		int index = data.size();
		data.add(point);
		
		return index;
	}

	public boolean deletePoint(int idx) {
		//System.out.println(this.size());
		if (idx < 0 || idx > data.size()-1){
			return false;
		}
		else{
			data.remove(idx);
			return true;
		}
	}
	
	public boolean editPoint(int idx, Point point){
		try {
			data.set(idx, point);
		} catch (Exception e) {
			//System.out.println("illegal index.");
			return false;
		}
		return true;
	}
	
	public double getXOfIndex(int idx){
		return data.get(idx).getXofPoint();
	}
	
	public double getYOfIndex(int idx){
		return data.get(idx).getYofPoint();
	}
	
	@Override
	public int size() { return data.size(); }
	
	public Range getRange(){
		return this.range;
	}
	
	public Boolean refreshRange(){
		int size = data.size();
		
		if(size == 0){
			range = new Range();
			return true;
		}
		
		double max_X, max_Y, min_X, min_Y;

			max_X = -(Double.MAX_VALUE);
			max_Y = -(Double.MAX_VALUE);
			min_X = Double.MAX_VALUE;
			min_Y = Double.MAX_VALUE;
			
			
		for(int i = 0; i < size; i++){
			if( max_X < data.get(i).getXofPoint()){
				max_X = data.get(i).getXofPoint();
			}
			if( max_Y < data.get(i).getYofPoint()){
				max_Y = data.get(i).getYofPoint();
			}
			if( min_X > data.get(i).getXofPoint()){
				min_X = data.get(i).getXofPoint();
			}
			if( min_Y > data.get(i).getYofPoint()){
				min_Y = data.get(i).getYofPoint();
			}
		}
		
		range.setRangeValues(max_X,max_Y,min_X,min_Y);
		
		return true;
	}

	public boolean saveToFile(File file) {
		BufferedWriter bw = null;
		
		try {
			bw = new BufferedWriter(new FileWriter(file, true), 'w');
			
			for ( int i=0; i<size(); i++) {
				bw.write(String.valueOf(data.get(i).getXofPoint()) + 
						"," + String.valueOf(data.get(i).getYofPoint()));
				bw.newLine();
			}			
		} catch(Exception e) {
			//e.printStackTrace();
			return false;
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		return true;
	}
	
	public boolean loadFromFile(File file) {
		BufferedReader br = null;
		int errorCounter = 0;
		
		try {
			br = new BufferedReader(new FileReader(file), 'r');
			
			String line = null;
			String[] values = null;
			int size = 2048 - size();
			int count = 0;
			while ((line = br.readLine()) != null && count++<size) {
				try {
					values = line.split(",");
					addPoint(new Point(Double.parseDouble(values[0]), Double.parseDouble(values[1])));
					//addPoint(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
				} catch(Exception e) {
					errorCounter++;
				}
			}
			if (errorCounter>0) {
				JOptionPane.showMessageDialog(null, errorCounter+" invalid points have been skipped.", "Note", JOptionPane.ERROR_MESSAGE);	
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		} finally {
			try {
				br.close();
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return true;
	}
	
	public void resetProperties(){
		this.properties = new Properties();
	}
	
	public Properties getProperties(){
		return this.properties;
	}

	@Override
	public double getMinX() {
		// TODO Auto-generated method stub
		return this.range.getSmallestX();
	}

	@Override
	public double getMaxX() {
		// TODO Auto-generated method stub
		return this.range.getLargestX();
	}

	@Override
	public double getMinY() {
		// TODO Auto-generated method stub
		return this.range.getSmallestY();
	}

	@Override
	public double getMaxY() {
		// TODO Auto-generated method stub
		return this.range.getLargestY();
	}

	@Override
	public double getCoordinate(int index, int dimension) {
		// TODO Auto-generated method stub
		if(index < 0 || index > this.size()){
			throw new ArrayIndexOutOfBoundsException("Invalid Index.");
		}
		if(dimension != 0 && dimension != 1){
			throw new IllegalArgumentException("Invalid dimension.");
		}
		if(dimension == 0){
			// return X vale
			return this.getXOfIndex(index);
		}
		else{
			// return Y vale
			return this.getYOfIndex(index);
		}
	}
}
