package br.com.intelipost.second.exception;

/**
 * Created by lucastex on 28/06/17.
 */
public class LoginNotFoundException extends Exception {

    private String username;

    public LoginNotFoundException(String username) {
        super("Usuário [" + username + "] não encontrado");
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}
