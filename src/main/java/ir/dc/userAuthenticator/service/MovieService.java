package ir.dc.userAuthenticator.service;

import ir.dc.userAuthenticator.dto.MovieDto;
import ir.dc.userAuthenticator.dto.MovieRequestDto;
import ir.dc.userAuthenticator.dto.MovieStatus;
import ir.dc.userAuthenticator.dto.PaginationResponseDto;
import ir.dc.userAuthenticator.entity.Movie;
import ir.dc.userAuthenticator.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    private List<Movie> getAll(){
        return repository.findAll();
    }

    private void addMovie(MovieDto dto){
        Movie movie= new Movie(dto.getId(),dto.getName(),MovieStatus.ACTIVE);
        repository.save(movie);
    }
    @Transactional
    public void disableMovie(Long id){
        var movie=repository.findById(id);
        if(movie.isPresent()){
            var m= movie.get();
            m.setStatus(MovieStatus.NOT_ACTIVE);
            repository.save(m);
        }
    }
    @Transactional
    public void enableMovie(Long id){
        var movie=repository.findById(id);
        if(movie.isPresent()){
            var m= movie.get();
            m.setStatus(MovieStatus.ACTIVE);
            repository.save(m);
        }
    }

    public Movie findById(long movieId) {
        return repository.findById(movieId).get();
    }

    public void save(MovieRequestDto dto) {
        repository.save(mapper(dto));
    }
    private Movie mapper(MovieRequestDto dto) {
        return new Movie(dto.getMovieName(),MovieStatus.ACTIVE);
    }

    public PaginationResponseDto<Movie> getAllEnable(int page, int pageSize, MovieStatus status) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Movie> result= null;
        if(status==MovieStatus.ALL){
             result =repository.findAll(pageable);
        }else {
             result =repository.findAllByStatusIs(status,pageable);
        }

        return new PaginationResponseDto<Movie>(page,pageSize,result.getTotalPages(),
                result.getTotalElements(),result.getContent());
    }
}
