package br.com.intelipost.second.util.requestValidator;

import br.com.intelipost.second.domain.UserToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lucastex on 30/06/17.
 */
public class HeaderRequestValidator implements RequestValidator {

    @Override
    public String isValid(HttpServletRequest httpRequest, HttpServletResponse response) throws IOException {
        return httpRequest.getHeader(UserToken.TOKEN);
    }

    @Override
    public String invalidate(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        //Not implemented since rest requests won't create server affinity on
        //client server relationship and won't be used
        return null;
    }

    @Override
    public void validate(UserToken token, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        //Not implemented since rest requests won't create server affinity on
        //client server relationship and won't be used
    }
}
