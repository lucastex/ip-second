package br.com.intelipost.second.interceptors;

import br.com.intelipost.second.util.requestValidator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lucastex on 30/06/17.
 */
@Component
public class RestTokenInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("restRequestValidator")
    private RequestValidator requestValidator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = requestValidator.isValid(request, response);
        if (token == null) {
            response.sendError(HttpStatus.FORBIDDEN.value());
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
