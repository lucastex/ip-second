package br.com.intelipost.second.controllers.web;

import br.com.intelipost.second.exception.LoginNotFoundException;
import br.com.intelipost.second.domain.UserToken;
import br.com.intelipost.second.services.LoginService;
import br.com.intelipost.second.util.requestValidator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lucastex on 28/06/17.
 */

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    @Qualifier("webRequestValidator")
    private RequestValidator requestValidator;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String form() {
        return "login/form";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String process(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        try {
            UserToken userToken = loginService.login(username, password);
            requestValidator.validate(userToken, request, response);
            return "redirect:/dashboard";
        } catch (LoginNotFoundException loginNotFoundException) {
            redirectAttributes.addFlashAttribute("message", loginNotFoundException.getMessage());
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard() {

        return "login/dashboard";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {

        String token = requestValidator.invalidate(request, response);
        loginService.logout(token);
        redirectAttributes.addFlashAttribute("message", "Você foi deslogado");
        return "redirect:/";
    }
}