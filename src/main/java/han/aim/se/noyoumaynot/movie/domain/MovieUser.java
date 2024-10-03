package han.aim.se.noyoumaynot.movie.domain;

public class MovieUser {
    String username;
    String wachtwoord;

    public MovieUser() {}

    public MovieUser(String username, String wachtwoord) {
        this.username = username;
        this.wachtwoord = wachtwoord;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }
}
