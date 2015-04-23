package src.Model;

import java.sql.Blob;
import service.*;

public class Image {
    Blob img;
    String bytes = null;
    // Query
    
    public String getPhoto(int id)
    {
    	try
    	{
    	  ImageManipulation im = new ImageManipulation();
    	  Database store = new Database();
    	  store.initialize();
          bytes = store.getPhoto(id);
    	}
    	
    	catch(Exception e)
    	{
    		
    	}
    	return bytes;
    }
}
