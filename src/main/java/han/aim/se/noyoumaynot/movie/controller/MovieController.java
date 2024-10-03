package han.aim.se.noyoumaynot.movie.controller;

import han.aim.se.noyoumaynot.movie.domain.Movie;
import han.aim.se.noyoumaynot.movie.domain.MovieUser;
import han.aim.se.noyoumaynot.movie.repository.UserToken;
import han.aim.se.noyoumaynot.movie.service.AuthenticationService;
import han.aim.se.noyoumaynot.movie.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.ArrayList;

@RestController
@RequestMapping("/movies")
public class MovieController {

//    static final String username = "Chris";
//    static final String wachtwoord = "test";
    private final MovieService movieService;
    private final AuthenticationService authenticationService;
//    static public String token = new UserToken(username).toString(); // hier zit het probleem, hier wordt nog een token gemaakt. (tf was ik aan 't doen man???)
    private String token; // Gewoon een normale declaratie, zodat authenticate faalt zonder dat er ingelogd is.
                          // Wanneer er wel ingelogd is, heeft deze een waarde die ook een token is in de arrayList van tokens

    @Autowired
    public MovieController(MovieService movieService, AuthenticationService authenticationService) {
        this.movieService = movieService;
        this.authenticationService = authenticationService;
    }


    @GetMapping
    public ArrayList<Movie> getAllMovies(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) throws Exception {
        authenticate(authorization);
        return movieService.getMovieList();
    }

    @GetMapping("/show")
    public Movie getMovieById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestParam("id") String id) throws Exception {
        authenticate(authorization);
        Movie movie = movieService.getMovieById(id);
        return movie;
    }

    @PostMapping("/add")
    public Movie addMovie(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @RequestBody Movie movie) throws Exception {
        authenticate(authorization);
        movieService.insertMovie(movie);
        return movie;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable("id") String id) throws Exception {
        authenticate(authorization);
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    private String authenticate(String token) throws Exception {
        if (authenticationService.isValidToken(token)){
            return authenticationService.getUsername(token);
        } else {
            throw new AuthenticationException("Invalid token");
        }
    }

    @PostMapping("/login")
    public void login(@RequestBody MovieUser user) throws AuthenticationException {
        String username = user.getUsername();
        String wachtwoord = user.getWachtwoord();

        UserToken userToken = authenticationService.login(username, wachtwoord);
        token = userToken.getToken(); // Initialiseer de token zodat hij gebruikt kan worden in authenticate
    }

}
