package han.aim.se.noyoumaynot.movie.repository;

import java.util.UUID;

public class UserToken {
    private final int TOKENVALIDTIME = 60*10;
    private String token;
    private String username;
    private long expiresIn;

    public UserToken(String username){
        this.username = username;
        this.expiresIn = TOKENVALIDTIME;
        this.token = UUID.randomUUID().toString();
        System.out.println(token);
//        this.token = "1";
    }


    public String getToken() {
        return token;
    }

    public int getTOKENVALIDTIME() {
        return TOKENVALIDTIME;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUsername() {
        return username;
    }
}
