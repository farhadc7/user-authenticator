package ir.dc.userAuthenticator.controller;

import ir.dc.userAuthenticator.dto.CustomerInfoRequestDto;
import ir.dc.userAuthenticator.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/validate-info")
    public String validation(@RequestBody CustomerInfoRequestDto dto) throws IOException {
        return customerService.saveRecord(dto);

    }

    @PostMapping("/validate-video")
    public String validateVideo(@RequestParam("video") MultipartFile video,@RequestParam String userCode) throws IOException {
        return customerService.validateVideo(video,userCode);
    }
}
