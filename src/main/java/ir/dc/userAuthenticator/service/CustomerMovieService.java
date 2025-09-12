//package ir.dc.userAuthenticator.service;
//
//import ir.dc.userAuthenticator.dto.CustomerInfoRequestDto;
//import ir.dc.userAuthenticator.dto.CustomerMovieRequestDto;
//import ir.dc.userAuthenticator.entity.CustomerEntity;
//import ir.dc.userAuthenticator.entity.Movie;
//import ir.dc.userAuthenticator.repository.CustomerMovieRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CustomerMovieService {
//    private final MovieService movieService;
//    private final CustomerService customerService;
//    private final CustomerMovieRepository repository;
//
//    public CustomerMovieService(MovieService movieService, CustomerService customerService, CustomerMovieRepository repository) {
//        this.movieService = movieService;
//        this.customerService = customerService;
//        this.repository = repository;
//    }
//
//    public String registerNewRecord(CustomerMovieRequestDto req){
//        CustomerEntity customer= getCustomer(req.getCustomer());
//        Movie movie= movieService.findById(req.getMovie().getMovieId());
//
//    }
//
//    private CustomerEntity getCustomer(CustomerInfoRequestDto customerDto) {
//        CustomerEntity customer= null;
//        Optional<CustomerEntity> initCustomer = customerService.getCustomer(customerDto);
//        if(initCustomer.isEmpty()){
//            customer= customerService.saveRecord(customerDto);
//        }else{
//            customer= initCustomer.get();
//        }
//        return customer;
//
//    }
//}
