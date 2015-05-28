package propertie;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import dataset.*;


public class ApplicationPropertie extends Properties{ 
      
	public String cartesian;
	public String column;
	public String horizontalBarGraph;
	public String multipleLines;
	
    //Instance  
    private static ApplicationPropertie instance;  
    //private constructor 
    private ApplicationPropertie(){  
        //load from configure file    
        //File ff = new File(System.getProperty("user.dir"));
        //String path = ff.getParent() + File.separator + "application.properties";
        //System.out.println(path);
        InputStream is = getClass().getResourceAsStream("application.properties");
        try {  
        	load(is);
        	cartesian = this.getProperty("cartesian");
        	column = getProperty("column");
        	horizontalBarGraph = getProperty("horizontalBarGraph");
        	multipleLines = getProperty("multipleLines");
        	
        } catch (Exception e) {  
            //Throw error  
        	e.printStackTrace();
            throw new IllegalStateException("Failed to load from configure file.");  
        }  
    }  
    //Get instance  
    public static ApplicationPropertie getInstance(){  
        if(instance==null){  
            instance=new ApplicationPropertie();  
           return instance;  
        }  
        return instance;  
    }  
  
 
}
