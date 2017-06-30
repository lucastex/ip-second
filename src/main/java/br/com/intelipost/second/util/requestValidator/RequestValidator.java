package br.com.intelipost.second.util.requestValidator;

import br.com.intelipost.second.domain.UserToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lucastex on 29/06/17.
 */
public interface RequestValidator {

    String isValid(HttpServletRequest httpRequest, HttpServletResponse response) throws IOException;
    String invalidate(HttpServletRequest httpRequest, HttpServletResponse httpResponse);
    void validate(UserToken token, HttpServletRequest httpRequest, HttpServletResponse httpResponse);

}
