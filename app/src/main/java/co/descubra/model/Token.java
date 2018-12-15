package co.descubra.model;

public class Token {
    private long id;
    private String username;
    private String accessToken;

    public Token(long id, String accessToken, String username) {
        this.accessToken = accessToken;
    }

    public Token(){

    }

    public String getToken() {
        return accessToken;
    }

    public void setToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
