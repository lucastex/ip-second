package br.com.intelipost.second.util.requestValidator;

import br.com.intelipost.second.domain.UserToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lucastex on 29/06/17.
 */
public interface RequestValidator {

    String isValid(HttpServletRequest httpRequest);
    String invalidate(HttpServletRequest httpRequest, HttpServletResponse httpResponse);
    void validate(UserToken token, HttpServletRequest httpRequest, HttpServletResponse httpResponse);

}
