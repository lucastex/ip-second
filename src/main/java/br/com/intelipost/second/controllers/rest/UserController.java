package br.com.intelipost.second.controllers.rest;

import br.com.intelipost.second.domain.Profile;
import br.com.intelipost.second.domain.User;
import br.com.intelipost.second.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lucastex on 30/06/17.
 */
@RestController
@RequestMapping(value = "/api/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @RequestMapping(value = "/{id}/profiles", method = RequestMethod.GET)
    public List<Profile> getUserProfiles(@PathVariable Long id) {
        return userService.getProfilesByUser(id);
    }

}
