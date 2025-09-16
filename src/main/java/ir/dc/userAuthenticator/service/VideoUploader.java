package ir.dc.userAuthenticator.service;

import ir.dc.userAuthenticator.dto.SabtAhvalTokenResponse;
import ir.dc.userAuthenticator.dto.VideoResponse;
import ir.dc.userAuthenticator.dto.VideoResponseData;
import ir.dc.userAuthenticator.exceptions.CustomException;
import ir.dc.userAuthenticator.exceptions.ErrorCode;
import ir.dc.userAuthenticator.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


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

    @Value("${video.format}")
    private String videoFormat;

    String imagePath = "/images/profile-img/";
    String videoPath = "/video/selfie/";

    @Value("${itsaaz.token.url}")
    private String tokenUrl;

    @Value("${itsaaz.vision.url}")
    private String API_URL;


    public VideoUploader(RestTemplate restTemplate, UploadUtil uploadUtil) {
        this.restTemplate = restTemplate;
        this.uploadUtil = uploadUtil;
    }




    public VideoResponseData upload(String selfieAddress, String customerImageAddress, String filename) throws IOException {
         File customerImage= new File(customerImageAddress);
         File selfie= new File(selfieAddress);
         //File frame=getFrame(selfieAddress,filename);


        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        // Add files to the body
//        body.add("Image",frame);
//        body.add("ReferenceImage", frame);
//        body.add("Video", selfie);
        body.add("ImageThreshold", 1);

        ByteArrayResource contentsAsResource = new ByteArrayResource(Files.readAllBytes(Path.of(customerImage.getPath()))) {
            @Override
            public String getFilename() {
                return "Image.jpg";
            }
        };

        ByteArrayResource ref = new ByteArrayResource(Files.readAllBytes(Path.of(customerImage.getPath()))) {
            @Override
            public String getFilename() {
                return "ref.jpg";
            }
        };
        body.add("Image", contentsAsResource);
        body.add("ReferenceImage", ref);

        ByteArrayResource video = new ByteArrayResource(Files.readAllBytes(Path.of(selfie.getPath()))) {
            @Override
            public String getFilename() {
                return "selfie." + videoFormat;
            }
        };
        body.add("Video", video);

//
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(getToken());


        HttpEntity requestEntity = new HttpEntity<>(body, headers);

///////////////////////

        // Send the request
        try {


            ResponseEntity<VideoResponse> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, VideoResponse.class);
            if(response.getStatusCode()==HttpStatus.OK){
                return response.getBody().getData();
            }else {
                System.out.println("Response from server: " + response);
                if(response.getBody()!= null && response.getBody().toString().contains("1073")){

                    throw new CustomException(ErrorCode.SABTAHVAL_NOT_MATCH, response.getBody()!= null?response.getBody().toString(): response.getStatusCode().toString());
                }else {
                    throw new CustomException(ErrorCode.SABTAHVAL_MATCHING_ISSUE, response.getBody()!= null?response.getBody().toString(): response.getStatusCode().toString());

                }
            }

        }catch (Exception e){
            if(e instanceof CustomException){
                throw e;
            }
            if(e.getMessage().contains("1073")){
                throw new CustomException(ErrorCode.SABTAHVAL_NOT_MATCH);
            }
            System.out.println("Response from server: " + e.getMessage());
            throw new CustomException(ErrorCode.SABTAHVALERROR,e.getMessage());


        }
    }

    private String getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(sabtAhvalUsername, sabtAhvalPass);

        HttpEntity req = new HttpEntity(headers);

        try {

            ResponseEntity<SabtAhvalTokenResponse> response =
                    restTemplate.exchange(tokenUrl, HttpMethod.POST, req, SabtAhvalTokenResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody().getAccess_token();

            } else {
                log.error("sabt ahval get token error: " + response);
                throw new CustomException(ErrorCode.SABTAHVAL_TOKEN_ERROR);
            }
        } catch (Exception e) {
            log.error("sabt ahval get token error: get time out " + e);
            throw new CustomException(ErrorCode.SABTAHVAL_TOKEN_ERROR);
        }

    }

    public File getFrame(String videoAddress, String filename) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        File file = new File(uploadAddress + imagePath + filename + "_frame.jpg");

        File videoFile = new File(videoAddress);
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoFile);

        frameGrabber.start();
        Java2DFrameConverter c = new Java2DFrameConverter();
        int frameCount = frameGrabber.getLengthInFrames();
        int frameNumber = frameCount / 2;
        System.out.println("Extracting " + String.format("%04d", frameNumber) + " of " + String.format("%04d", frameCount) + " frames");
        frameGrabber.setFrameNumber(frameNumber);
        Frame f = frameGrabber.grab();
        BufferedImage bi = c.convert(f);
        var a = ImageIO.write(bi, "jpg", file);

        frameGrabber.stop();
        return file;
    }

}