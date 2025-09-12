package ir.dc.userAuthenticator.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@Slf4j
public class UploadUtil {


    @Value("${files.directory}")
    private String uploadAddress;
    String path = "/images/profile-img/";
    String videoPath = "/video/selfie/";

    public String uploadSelfie(MultipartFile file, String fileName) throws IOException {


        String uploadDir = uploadAddress + videoPath;

        String filePath = FileUploadUtil.saveFile(uploadDir, fileName, file);

        return uploadDir + fileName;
    }


    public String saveBase64Image(String base64Image, String fileName) throws IOException {
        String path = "/images/profile-img/";

        // Remove the prefix if present (e.g., "data:image/png;base64,")
        String[] parts = base64Image.split(",");
        String imageData = parts.length > 1 ? parts[1] : parts[0];


        // Decode the base64 string
        byte[] imageBytes = Base64.getDecoder().decode(imageData);

        String filePath = uploadAddress + path + fileName + ".jpg";
        // Write the bytes to a file
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            fos.write(imageBytes);
            fos.flush();
            System.out.println("Image saved successfully to " + filePath);
            return filePath;

        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
            throw e;
        }
    }

    public   byte[] readFileToByteArray(String filePath) throws IOException {
        if(filePath.charAt(0) == '/'){
            filePath= filePath.substring(1);
        }

        return Files.readAllBytes(Paths.get(filePath));
    }


}
