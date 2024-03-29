package br.com.intelipost.second.services;

import br.com.intelipost.second.exception.LoginNotFoundException;
import br.com.intelipost.second.repositories.UserRepository;
import br.com.intelipost.second.domain.User;
import br.com.intelipost.second.domain.UserToken;
import br.com.intelipost.second.repositories.UserTokenRepository;
import br.com.intelipost.second.util.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lucastex on 28/06/17.
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    public UserToken login(String username, String password) throws LoginNotFoundException {

        String hashedPassword = passwordEncryptor.encrypt(password);

        User user = userRepository.findUserByCredentials(username, hashedPassword);
        if (user == null) {
            throw new LoginNotFoundException(username);
        }

        return createUserToken(user);
    }

    private UserToken createUserToken(User user) {
        return userTokenRepository.createNewToken(user);
    }

    public void logout(String token) {
        userTokenRepository.invalidateToken(token);
    }
}
