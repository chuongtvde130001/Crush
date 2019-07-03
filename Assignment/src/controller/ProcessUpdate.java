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
import utils.ImageSaver;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProcessUpdate extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        String fullName = request.getParameter("fullName");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        // Xử lí upload file  
        Part photo = request.getPart("myImage");
        String avatarPath = ImageSaver.saveImage(photo, String.valueOf(usr.getUid()));
        // Cập Nhật thông tin User Avatar vv...
        System.out.println(avatarPath);
        UserDAO.updateUserInfo(fullName, age, gender, avatarPath, usr.getUid());
        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
