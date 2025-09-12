package ir.dc.userAuthenticator.service;

import ir.dc.userAuthenticator.dto.CustomerInfoRequestDto;
import ir.dc.userAuthenticator.dto.SabtAhvalInfoResponseWrapper;
import ir.dc.userAuthenticator.dto.SabtAhvalTokenResponse;
import ir.dc.userAuthenticator.exceptions.CustomException;
import ir.dc.userAuthenticator.exceptions.ErrorCode;
import ir.dc.userAuthenticator.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class VideoUploader {

    private final RestTemplate restTemplate;
    private final UploadUtil uploadUtil;

    @Value("${files.directory}")
    private String uploadAddress;

    @Value("${sabt.ahval.auth.user}")
    private String sabtAhvalUsername;

    @Value("${sabt.ahval.auth.pass}")
    private String sabtAhvalPass;

    String imagePath = "/images/profile-img/";
    String videoPath = "/video/selfie/";

    @Value("${itsaaz.token.url}")
    private String tokenUrl;

    @Value("${itsaaz.vision.url}")
    private   String API_URL ;



    public VideoUploader(RestTemplate restTemplate, UploadUtil uploadUtil) {
        this.restTemplate = restTemplate;
        this.uploadUtil = uploadUtil;
    }



    public void upload(String selfieAddress,String customerImageAddress) throws IOException {
         File customerImage= new File(customerImageAddress);
         File selfie= new File(selfieAddress);
         byte[] frame=getFrame(selfieAddress);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        // Add files to the body
        body.add("Image",customerImage);
        body.add("ReferenceImage", selfie);
        body.add("Video", frame);
        body.add("ImageThreshold", 1);
//        body.add("VideoThreshold", frame);
//        body.add("Preprocess", false);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setBearerAuth(getToken());

        // Create request entity with the image byte array
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Send the request
        ResponseEntity<Object> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, Object.class);
        System.out.println("Response from server: " + response);
    }

    private String getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(sabtAhvalUsername, sabtAhvalPass);

        HttpEntity req = new HttpEntity(headers);

        try{

            ResponseEntity<SabtAhvalTokenResponse> response =
                    restTemplate.exchange(tokenUrl, HttpMethod.POST, req, SabtAhvalTokenResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody().getAccess_token();

            } else {
                log.error("sabt ahval get token error: " + response);
                throw new CustomException(ErrorCode.SABTAHVAL_TOKEN_ERROR);
            }
        }catch (Exception e){
            log.error("sabt ahval get token error: get time out " +e);
            throw new CustomException(ErrorCode.SABTAHVAL_TOKEN_ERROR);
        }

    }

    public byte[] getFrame(String videoAddress) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        File videoFile = new File(videoAddress);
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoFile);

        frameGrabber.start();
        Java2DFrameConverter c = new Java2DFrameConverter();
        int frameCount = frameGrabber.getLengthInFrames();
        int frameNumber=frameCount/2;
            System.out.println("Extracting " + String.format("%04d", frameNumber) + " of " + String.format("%04d", frameCount) + " frames");
            frameGrabber.setFrameNumber(frameNumber);
            Frame f = frameGrabber.grab();
            BufferedImage bi = c.convert(f);
           var a= ImageIO.write(bi,"jpg",baos);

        frameGrabber.stop();
        return baos.toByteArray();
    }

}