package controller;

import dao.UserDAO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
        String about = request.getParameter("about");
        int ageBegin = Integer.parseInt(request.getParameter("ageBegin"));
        int ageEnd = Integer.parseInt(request.getParameter("ageEnd"));
        String wantList[] = request.getParameterValues("wantGender");
        int male = 2, female = 3, other = 5;
        int wantAge=1;
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(wantList));
        if (arrayList.contains("male") && arrayList.contains("female") && arrayList.contains("other")) {
            wantAge = male * female * other;
        } else if (arrayList.contains("male") && arrayList.contains("female")) {
            wantAge = male * female;
        } else if (arrayList.contains("male") && arrayList.contains("other")) {
            wantAge = male * other;
        } else if (arrayList.contains("female") && arrayList.contains("other")) {
            wantAge = female * other;
        } else if (arrayList.contains("male")) {
            wantAge = male;
        } else if (arrayList.contains("female")) {
            wantAge = female;
        } else if (arrayList.contains("other")) {
            wantAge = other;
        }
        // Xử lí upload file  
        Part photo = request.getPart("myImage");
        String avatarPath = ImageSaver.saveImage(photo, String.valueOf(usr.getUid()));
        // Cập Nhật thông tin User Avatar vv...
        System.out.println(avatarPath);
        UserDAO.updateUserInfo(fullName, age, gender, avatarPath, about, usr.getUid(), ageBegin, ageEnd, wantAge);
        getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
