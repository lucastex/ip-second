package br.com.intelipost.second.util.requestValidator;

import br.com.intelipost.second.domain.UserToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by lucastex on 29/06/17.
 */
public class SessionRequestValidator implements RequestValidator {

    @Override
    public String isValid(HttpServletRequest httpRequest) {

        HttpSession session = httpRequest.getSession();
        String token = (String) session.getAttribute(UserToken.TOKEN);
        return token;
    }

    @Override
    public void validate(UserToken userToken, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

        HttpSession session = httpRequest.getSession();
        session.setAttribute(UserToken.TOKEN, userToken.getHash());
    }

    @Override
    public String invalidate(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

        HttpSession session = httpRequest.getSession();
        String oldTokenValue = (String) session.getAttribute(UserToken.TOKEN);
        session.removeAttribute(UserToken.TOKEN);
        session.invalidate();
        return oldTokenValue;
    }
}
