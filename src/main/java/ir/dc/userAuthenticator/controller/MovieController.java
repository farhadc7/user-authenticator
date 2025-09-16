package ir.dc.userAuthenticator.controller;

import ir.dc.userAuthenticator.dto.MovieRequestDto;
import ir.dc.userAuthenticator.dto.MovieStatus;
import ir.dc.userAuthenticator.dto.PaginationResponseDto;
import ir.dc.userAuthenticator.entity.Movie;
import ir.dc.userAuthenticator.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/add")
    public void save(@RequestBody MovieRequestDto dto){
        movieService.save(dto);
    }
    @PutMapping("/disable")
    public void disable(@RequestParam("movieId") long movieId){
        movieService.disableMovie(movieId);
    }

    @GetMapping("/all")

    public PaginationResponseDto<Movie>  getAllEnable(@RequestParam(name = "pageSize",defaultValue ="0" ) int page,
                                                      @RequestParam(name = "pageSize",defaultValue = "10") int pageSize
            , @RequestParam(name = "status",defaultValue = "ACTIVE") MovieStatus status){
        return movieService.getAllEnable(page,pageSize,status);
    }

    @PutMapping("/enable")
    public void enable(@RequestParam("movieId") long movieId){
        movieService.enableMovie(movieId);
    }
}
