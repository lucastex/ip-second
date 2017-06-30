package br.com.intelipost.second.util.requestValidator;

import br.com.intelipost.second.domain.UserToken;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lucastex on 29/06/17.
 */
public class CookieRequestValidator implements RequestValidator {

    @Override
    public String isValid(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie ck : cookies) {
            if (ck.getName().equals(UserToken.TOKEN)) {
                String token = ck.getValue();
                return token;
            }
        }

        return null;
    }

    @Override
    public void validate(UserToken userToken, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

        Cookie cookie = new Cookie(UserToken.TOKEN, userToken.getHash());
        httpResponse.addCookie(cookie);
    }

    @Override
    public String invalidate(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {

        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie ck : cookies) {
            if (ck.getName().equals(UserToken.TOKEN)) {

                String oldTokenValue = ck.getValue();

                ck.setValue("");
                ck.setMaxAge(0);
                httpResponse.addCookie(ck);

                return oldTokenValue;
            }
        }

        return null;
    }
}
