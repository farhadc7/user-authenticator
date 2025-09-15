package ir.dc.userAuthenticator.controller;

import ir.dc.userAuthenticator.dto.CustomerInfoRequestDto;
import ir.dc.userAuthenticator.dto.PaginationResponseDto;
import ir.dc.userAuthenticator.entity.CustomerEntity;
import ir.dc.userAuthenticator.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public String validateVideo(@RequestParam("video") MultipartFile video,@RequestParam("userCode") String userCode) throws IOException {
        return customerService.validateVideo(video,userCode);
    }
    @PostMapping("/accept-conditions")
    public CustomerEntity acceptConditions(@RequestParam("userCode") String userCode)  {
        return customerService.acceptConditions(userCode);
    }

    @GetMapping("/find-by-code")
    public CustomerEntity getByCode(@RequestParam("userCode") String userCode)  {
       return customerService.getByCode(userCode);
    }

    @PostMapping("/get-all")
    public PaginationResponseDto<CustomerEntity> getAll(@RequestParam("page") int page , @RequestParam("pageSize") int pageSize){
        return customerService.getAllCustomers(page,pageSize,null);
    }

    @PostMapping("/add-machine-code")
    public CustomerEntity acceptConditions(@RequestParam("userCode") String userCode ,@RequestParam("machineCode") String machineCode,@RequestParam("desc") String desc)  {
        return customerService.addMachineCode(userCode,machineCode,desc);
    }
}
