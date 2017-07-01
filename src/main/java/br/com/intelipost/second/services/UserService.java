package br.com.intelipost.second.services;

import br.com.intelipost.second.domain.Profile;
import br.com.intelipost.second.repositories.ProfileRepository;
import br.com.intelipost.second.repositories.UserRepository;
import br.com.intelipost.second.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lucastex on 30/06/17.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<User> listUsers() {
        return userRepository.listUsers();
    }

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<Profile> getProfilesByUser(Long userId) {
        return profileRepository.getProfilesByUser(userId);
    }
}
