<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.io.*" %>
<%@ page import = "src.Model.*" %>
<jsp:useBean id="photo" class="src.Model.Image" scope="session" />
<%
 
  int iNumPhoto ;
  
  if ( request.getParameter("imgID") != null )
  {
   
    iNumPhoto = Integer.parseInt(request.getParameter("imgID")) ;   
  
    try
    {  
  
       // get the image from the database
       byte[] imgData = photo.getPhoto(9) ;   
       // display the image
       //out.clear();
	String filename = "shit";
		
		// Write a image byte array into file system  
		out.clear();
		response.setContentType("image/gif");
       OutputStream o = response.getOutputStream();
       o.write(imgData);
       o.flush(); 
       o.close();
		
		FileOutputStream imageOutFile = new FileOutputStream(System.getProperty("user.dir")+"/src/"+filename.concat(".gif"),true);
		imageOutFile.write(imgData);
		imageOutFile.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw e;
    }
    finally
    {
    }  
  }
%>