package utils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class ImageSaver {

    public static final String imagePath = "http://localhost/images/";

    public static String saveImage(Part photo, String uid) throws IOException {
        String fileName = uid + extractFileFormat(photo);
        System.out.println("FILE NAME"+extractFileFormat(photo));
        fileName = new File(fileName).getName();
        String path = getFolderUpload().getAbsolutePath() + File.separator + fileName;
        photo.write(getFolderUpload().getAbsolutePath() + File.separator + fileName);
        return "avatars/" + fileName;
    }

    private static String extractFileFormat(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                return fileName.substring(fileName.indexOf("."));
            }
        }
        return "";
    }

    public static File getFolderUpload() {
        File folderUpload = new File("uploads/avatars");
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}
