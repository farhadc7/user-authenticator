package ir.dc.userAuthenticator.entity;

import ir.dc.userAuthenticator.dto.MovieStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    public Movie(String name, MovieStatus status) {
        this.name = name;
        this.status = status;
    }
}
