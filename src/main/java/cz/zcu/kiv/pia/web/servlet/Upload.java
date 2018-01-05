package cz.zcu.kiv.pia.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cz.zcu.kiv.pia.manager.UserManager;

/**
 * Servlet implementation class Upload
 */

public class Upload extends HttpServlet {
	
	// uploads a file to a server
	
	private static final long serialVersionUID = 1L;
       
	private UserManager userManager;

	    private static final String DATA_DIRECTORY = "img/avatars";
	    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
	    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
	    
	    private static final String USER = "user";
	    private static final String SUCCESS_ATTRIBUTE = "suc";
	    private static final String ERROR_ATRIBUTE = "err";
	    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload(UserManager userManager) {
    	this.userManager = userManager;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// should never be called as GET - show index in that case
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            return;
        }
        
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // constructs the folder where uploaded file will be stored
        String uploadFolder = getServletContext().getRealPath("")
                + File.separator + DATA_DIRECTORY;

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);
		
		
        try {
            // Parse the request
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                	
                	// no file given
                	if (item.getName().trim().isEmpty()) {
                		request.setAttribute(ERROR_ATRIBUTE, "Obrázek nevybrán.");
                		request.getRequestDispatcher("WEB-INF/pages/welcome.jsp").forward(request, response);
                		return;
                	}
                	
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadFolder + File.separator + fileName;
                    File uploadedFile = new File(filePath);
                    System.out.println(filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);
                    
                    // update the avatar
                    userManager.updateAvatar((String) request.getSession().getAttribute(USER), File.separator +  DATA_DIRECTORY + File.separator + fileName);
                    
                }
            }
            
            request.setAttribute(SUCCESS_ATTRIBUTE, "Nový profilový obrázek byl úspìšnì nahrán.");
            request.getRequestDispatcher("WEB-INF/pages/welcome.jsp").forward(request, response);

            

        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
	}

}
