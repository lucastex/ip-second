package br.com.intelipost.second.interceptors;

import br.com.intelipost.second.services.UserTokenService;
import br.com.intelipost.second.util.requestValidator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lucastex on 29/06/17.
 */
@Component
public class WebTokenInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("webRequestValidator")
    private RequestValidator requestValidator;

    @Autowired
    private UserTokenService userTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String token = requestValidator.isValid(request, response);
        if (token == null) {
            response.sendRedirect("/");
            return false;
        }

        return userTokenService.checkIfTokenIsValid(token);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
