package br.com.intelipost.second;

/**
 * Created by lucastex on 28/06/17.
 */
public class LoginNotFoundException extends Exception {

    private String username;

    public LoginNotFoundException(String username) {
        super("O usuário [" + username + "] não foi encontrado");
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}
