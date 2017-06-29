package br.com.intelipost.second.util;

import org.springframework.stereotype.Component;

/**
 * Created by lucastex on 28/06/17.
 */

@Component
public class PasswordEncryptor {

    //Choose the preferred password encryption
    public String encrypt(String original) {
        return new StringBuilder(original).reverse().toString();
    }

}
