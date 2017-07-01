package br.com.intelipost.second.interceptors;

import br.com.intelipost.second.domain.Link;
import br.com.intelipost.second.services.LinkService;
import br.com.intelipost.second.util.requestValidator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lucastex on 30/06/17.
 */
@Component
public class LinkInterceptor implements HandlerInterceptor {

    @Autowired
    private LinkService linkService;

    @Autowired
    @Qualifier("webRequestValidator")
    private RequestValidator requestValidator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<Link> links = linkService.getLinksForToken(requestValidator.isValid(request, response));
        modelAndView.getModelMap().addAttribute("navLinks", links);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
