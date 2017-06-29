package br.com.intelipost.second.controllers;

import br.com.intelipost.second.LoginNotFoundException;
import br.com.intelipost.second.domain.UserToken;
import br.com.intelipost.second.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by lucastex on 28/06/17.
 */

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private HttpSession httpSession;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String form() {
        return "login/form";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String process(@RequestParam String username, @RequestParam String password, Model model) {

        try {
            UserToken userToken = loginService.login(username, password);
            httpSession.setAttribute("token", userToken.getHash());
            return "redirect:/dashboard";
        } catch (LoginNotFoundException loginNotFoundException) {
            model.addAttribute("username", loginNotFoundException.getUsername());
            return "login/error";
        }
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {

        String token = (String) httpSession.getAttribute("token");
        model.addAttribute("token", token);
        return "login/dashboard";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {

        String token = (String) httpSession.getAttribute("token");
        loginService.logout(token);
        httpSession.invalidate();
        return "login/logout";
    }
}