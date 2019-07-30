package controller;

import dao.UserDAO;
import dao.WantDAO;
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User usr = (User) session.getAttribute("user");
        String fullName = request.getParameter("fullName");
        System.out.println("XXXX"+fullName);
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String about = request.getParameter("about");
        int ageBegin = Integer.parseInt(request.getParameter("ageBegin"));
        int ageEnd = Integer.parseInt(request.getParameter("ageEnd"));
        String wantList[] = request.getParameterValues("wantGender");
        int wantAge=1;
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(wantList));
        System.out.println("ARRAY" + arrayList);
        for(String i:arrayList){
            if(i.equals("wantMale")) wantAge*=2;
            else if(i.equals("wantFemale")) wantAge*=3;
            else wantAge*=5;
        }
        // Xử lí upload file  
        Part photo = request.getPart("myImage");

        String avatarPath;
        try {
            avatarPath = ImageSaver.saveImage(photo, String.valueOf(usr.getUid()));
        }catch (Exception e){
            avatarPath = usr.getAvatar().substring(usr.getAvatar().indexOf("ava"));
        }
        // Cập Nhật thông tin User Avatar vv...
        UserDAO.updateUserInfo(fullName, age, gender, avatarPath, about, usr.getUid(), ageBegin, ageEnd, wantAge);
        User user = UserDAO.getUser(usr.getUid());
        session.setAttribute("user", user);
        response.sendRedirect("/");
    }
}
