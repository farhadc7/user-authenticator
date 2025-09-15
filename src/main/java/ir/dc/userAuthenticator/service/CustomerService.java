package ir.dc.userAuthenticator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.dc.userAuthenticator.dto.*;
import ir.dc.userAuthenticator.entity.CustomerEntity;
import ir.dc.userAuthenticator.entity.Movie;
import ir.dc.userAuthenticator.exceptions.CustomException;
import ir.dc.userAuthenticator.exceptions.ErrorCode;
import ir.dc.userAuthenticator.repository.CustomerRepository;
import ir.dc.userAuthenticator.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final MovieService movieService;
    private final UploadUtil uploadUtil;

    private final VideoUploader videoUploader;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${sabt.ahval.info}")
    private String sabtahvalInfoUrl;
    @Value("${sabt.ahval.image}")
    private String sabtahvalImageUrl;

    @Value("${sabt.ahval.auth.user}")
    private String sabtAhvalUsername;

    @Value("${sabt.ahval.auth.pass}")
    private String sabtAhvalPass;
    @Value("${video.format}")
    private String videoFormat;

    public CustomerService(CustomerRepository customerRepository, MovieService movieService, UploadUtil uploadUtil, VideoUploader videoUploader) {
        this.customerRepository = customerRepository;
        this.movieService = movieService;
        this.uploadUtil = uploadUtil;
        this.videoUploader = videoUploader;
    }

    public Optional<CustomerEntity> getCustomer(CustomerInfoRequestDto customer) {
        Optional<CustomerEntity> customerEntity = findByNationalCode(customer.getNationalCode());
        return customerEntity;

    }


    public String saveRecord(CustomerInfoRequestDto dto) throws IOException {
        CustomerDto customerDto;
        customerDto = mapToDto(dto);

        SabtAhvalResponse infoResp = getInfo(dto);
        setSabtAhvalInfoToCustomerDto(customerDto, infoResp);

        String code =getAlphaNumericString(10);

        String imagePath = getPicture(dto,code);
        customerDto.setImagePath(imagePath);

        customerDto.setUniqueCode(code);

        customerRepository.save(mapToEntity(customerDto));
        return code;
    }

    private String getPicture(CustomerInfoRequestDto customerDto,String code) throws IOException {
        String image= getImage(customerDto).getImage();
        String path=uploadProfileImage(image,code);
        return path;


    }


    public String uploadProfileImage(String file,String code) throws IOException {
        String filePath = null;
        
        filePath = uploadUtil.saveBase64Image(file, code);
        return filePath;
    }

    private void setSabtAhvalInfoToCustomerDto(CustomerDto customerDto, SabtAhvalResponse infoResp) {
        customerDto.sabtAhvalSetter(infoResp);
    }

    private SabtAhvalResponse getInfo(CustomerInfoRequestDto customerDto) {
        Object result=callSabtAhval(customerDto,sabtahvalInfoUrl);
        var successResult = objectMapper.convertValue(result, SabtAhvalResponse.class);
        return successResult;
    }
    private SabtAhvalImageResponse getImage(CustomerInfoRequestDto customerDto) {
        Object result=callSabtAhval(customerDto,sabtahvalImageUrl);
        var successResult = objectMapper.convertValue(result, SabtAhvalImageResponse.class);
        return successResult;
    }

    private Object callSabtAhval(CustomerInfoRequestDto customerDto,String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(sabtAhvalUsername, sabtAhvalPass);
        Map<String, String> body = new HashMap<>();
        body.put("nationalId", customerDto.getNationalCode());
        body.put("birthDate", customerDto.getBirthDate());
        HttpEntity<Map<String, String>> req = new HttpEntity(body, headers);

        ResponseEntity<SabtAhvalInfoResponseWrapper> response =
                restTemplate.exchange(url, HttpMethod.POST, req, SabtAhvalInfoResponseWrapper.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            if (response.getBody().getResult().getStatus().getStatusCode() == 200) {

                return response.getBody().getResult().getData();

            } else {
                log.error("sabt haval info error: " + response);
                throw new CustomException(ErrorCode.SABTAHVALERROR);
            }
        } else {
            log.error("sabt haval error: " + response);
            throw new CustomException(ErrorCode.SABTAHVALERROR);
        }
    }


    private CustomerDto mapToDto(CustomerInfoRequestDto dto) {
        var c = new CustomerDto();
        c.setNationalCode(dto.getNationalCode());
        c.setBirthDate(dto.getBirthDate());
        c.setMobileNumber(dto.getMobileNumber());
        c.setMovieId(dto.getMovieId());
        return c;
    }

    public PaginationResponseDto<CustomerEntity> getAllCustomers(int page, int pageSize,CustomerEntity c) {
        Pageable p=PageRequest.of(page,pageSize, Sort.by(Sort.Direction.ASC,"id"));
//        Specification<CustomerEntity> sp= Specification.allOf();
//        if(c.getUniqueCode()!= null){
//            sp.and((root,query,cb)->
//                cb.equal(root.get("uniqueCode"),c.getUniqueCode())
//            );
//        }
//        if(c.getNationalCode()!= null && !c.getNationalCode().isBlank()){
//            sp.and((root,query,cb)->
//                    cb.equal(root.get("nationalCode"),c.getNationalCode())
//            );
//        }
//        if(c.getUniqueCode()!= null && !c.getUniqueCode().isBlank()){
//            sp.and((root,query,cb)->
//                    cb.equal(root.get("uniqueCode"),c.getUniqueCode())
//            );
//        }
//        if(c.getMovie()!= null){
//            sp.and((root,query,cb)->
//                    cb.equal(root.get("movie.name"),c.getMovie().getName())
//            );
//        }
//



        Page<CustomerEntity> result =customerRepository.findAll(p);
        return new PaginationResponseDto<CustomerEntity>(page,pageSize,result.getTotalPages(),
                result.getTotalElements(),result.getContent());
    }

    private CustomerEntity mapToEntity(CustomerDto dto) {
        Movie movie = movieService.findById(dto.getMovieId());
        var ent = new CustomerEntity(dto.getMobileNumber(),dto.getUniqueCode(),
                dto.getNationalCode(), dto.getBirthDate(), movie);

        ent.sabtAhvalSetter(dto);

        ent.setImagePath(dto.getImagePath());
        return ent;
    }

    public String validateVideo(MultipartFile video, String uniqueCode) throws IOException {
         Optional<CustomerEntity> customerEntity = customerRepository.findByUniqueCode(uniqueCode);
         if(customerEntity.isEmpty()){
             log.error("validateVideo : user not found with unique code : "+uniqueCode);
             throw new CustomException(ErrorCode.USER_NOT_FOUND);
         }
         String videoAddress =uploadUtil.uploadSelfie(video,uniqueCode+"."+videoFormat);

        videoUploader.upload(videoAddress,customerEntity.get().getImagePath(),uniqueCode);
        return null;

    }

    static String getAlphaNumericString(int n) {

        // choose a Character random from this String
        String AlphaNumericString =  "0123456789"+"ABCDEFGHIJKLMNOPQRSTUVWXYZ"+ "0123456789"
                + "0123456789"+ "0123456789"+ "0123456789"
                + "abcdefghijklmnopqrstuvxyz"+ "0123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }


    private Optional<CustomerEntity> findByNationalCode(String nationalCode) {
        return customerRepository.findByNationalCode(nationalCode);
    }

    public CustomerEntity acceptConditions(String userCode) {
        var opt= customerRepository.findByUniqueCode(userCode);
        if(opt.isPresent()){
            var ent= opt.get();
            ent.setConditionAccepted(true);
            ent.setIssuedDate(LocalDateTime.now());
            customerRepository.save(ent);
            return ent;
        }
        return null;
    }

    public CustomerEntity getByCode(String userCode) {
        var opt= customerRepository.findByUniqueCode(userCode);
        if(opt.isPresent()){
            return opt.get();
        }
        return null;
    }

    public CustomerEntity addMachineCode(String userCode,String machineCode, String desc) {
        var opt= customerRepository.findByUniqueCode(userCode);
        if(opt.isPresent()){
            var ent= opt.get();
            ent.setMachineCode(machineCode);
            ent.setDesc(desc);
            customerRepository.save(ent);
            return ent;
        }
        return null;

    }
}
