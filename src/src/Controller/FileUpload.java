package src.Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultiPart;
    private String filePath = null;
    private int maxFileSize = 1000 * 1024;
    private int memSize = 4 * 1024;
    private File file;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
      
        super();
//      
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  filePath = getServletContext().getInitParameter("file-data"); 
	  HttpSession session = request.getSession();
	  isMultiPart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      
      if (!isMultiPart)
      {
        session.setAttribute("Upload_status", "Failed");
        response.sendRedirect("index.jsp");
      }
      
      else
      {
      
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxFileSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );
 
        try
        { 
          // Parse the request to get file items.
          List fileItems = upload.parseRequest(request);
    
          // Process the uploaded file items
          Iterator i = fileItems.iterator();
        
          while ( i.hasNext () ) 
          {
            FileItem fi = (FileItem)i.next();
           
            if ( !fi.isFormField () )  
            {
              // Get the uploaded file parameters
              String fieldName = fi.getFieldName();
              String fileName = fi.getName();
              String contentType = fi.getContentType();
              boolean isInMemory = fi.isInMemory();
              long sizeInBytes = fi.getSize();
              // Write the file
              if( fileName.lastIndexOf("\\") >= 0 )
              {
                file = new File( filePath + 
                fileName.substring( fileName.lastIndexOf("\\"))) ;
              }
              
              else
              {
                file = new File( filePath + 
                fileName.substring(fileName.lastIndexOf("\\")+1)) ;
              }
              
              try
              {
                fi.write( file ) ;
              }
              
              catch (Exception e)
              {
                
              }
            }
          }
        
//          response.sendRedirect("index.jsp");
	    }
      
        catch (FileUploadException fue)
        {
          fue.printStackTrace();
        }

	  }
	}
}
