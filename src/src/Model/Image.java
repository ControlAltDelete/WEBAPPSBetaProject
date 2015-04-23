package src.Model;

import java.sql.Blob;

import service.Database;

public class Image {
    Blob img;
    byte[] bytes;
    // Query
    
    public byte[] getPhoto(int id)
    {
    	try
    	{
    		Database store = new Database();
    		store.initialize();
        	bytes = store.getPhoto(id);
    	}catch(Exception e)
    	{
    		
    	}
    	return bytes;
    }
}
