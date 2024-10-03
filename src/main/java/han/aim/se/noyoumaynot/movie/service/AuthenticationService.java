package han.aim.se.noyoumaynot.movie.service;

import han.aim.se.noyoumaynot.movie.domain.Rol;
import han.aim.se.noyoumaynot.movie.repository.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Service
public class AuthenticationService {
  ArrayList<UserToken> userTokens = new ArrayList<>();
  private final Rol rol;

  @Autowired
  public AuthenticationService(Rol rol) {
    this.rol = rol;
  }

  public UserToken login(String username, String password) throws AuthenticationException {
    String correctUser = "Chris";
    String correctPassword = "test";

    if (Objects.equals(username, correctUser) && Objects.equals(password, correctPassword)) { // Als de hardcoded waarden overeenkomen met de parameters

      // Genereer een UserToken met de gegeven username
      UserToken userToken = new UserToken(username);

      // voeg toe aan arraylist
      userTokens.add(userToken);
      System.out.println(userToken.getToken()); // Print de token om te checken of deze klopt. Gebruikt bij stap 5 debuggen.

      return userToken; // Maak token en zet in lijst
    }
    else
    {
      throw new AuthenticationException("invalid username or password");
    }
  }

  public boolean isValidToken(String existingToken) {
    // kijk of er ooit een token is geweest in de lijst usertokens
    System.out.println(existingToken);

    return userTokens.stream().map(UserToken::getToken).anyMatch(token -> token.equals(existingToken));
//    return true;
  }

  public String getUsername(String token) {

    return userTokens.stream().filter(userToken -> userToken.getToken().equals(token)).map(UserToken::getUsername).findFirst().orElse(null);
  }
}
