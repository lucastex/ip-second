package br.com.intelipost.second.services;

import org.springframework.stereotype.Service;

/**
 * Created by lucastex on 30/06/17.
 */
@Service
public class UserTokenService {

    public boolean checkIfTokenIsValid(String token) {

        //this method should check if some token is valid with the user business logic
        //probably checking its age, last interaction or other rule
        //returning true for POC purpose
        return true;
    }
}
