package controller;

import dao.UserDAO;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.User;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProcessUpdate extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username = user.getUserName();
        String fullName = request.getParameter("fullName");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        // Xử lí upload file  
        Part photo = request.getPart("myImage");
        String fileName = extractFileName(photo);
        fileName = new File(fileName).getName();
        String path = this.getFolderUpload().getAbsolutePath() + File.separator + fileName;
        photo.write(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
        // Cập Nhật thông tin User Avatar vv...
        UserDAO.updateUserInfo(fullName, age, gender, username, path); 
        getServletContext().getRequestDispatcher("/chat.jsp").forward(request, response);
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload() {
        File folderUpload = new File("D:/Uploads");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}
