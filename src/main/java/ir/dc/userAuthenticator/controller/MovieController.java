package ir.dc.userAuthenticator.controller;

import ir.dc.userAuthenticator.dto.MovieRequestDto;
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
    public void disable(@RequestParam long movieId){
        movieService.disableMovie(movieId);
    }

    @PutMapping("/enable")
    public void enable(@RequestParam long movieId){
        movieService.enableMovie(movieId);
    }
}
