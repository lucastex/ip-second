package br.com.intelipost.second.util.requestValidator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lucastex on 29/06/17.
 */
@Configuration
public class RequestValidatorConfig {

    @Bean("webRequestValidator")
    public RequestValidator getSessionRequestValidator() {
        return new SessionRequestValidator();
    }

    //@Bean("webRequestValidator")
    public RequestValidator getCookieRequestValidator() {
        return new CookieRequestValidator();
    }

    @Bean("restRequestValidator")
    public RequestValidator getRestRequestValidator() {
        return new HeaderRequestValidator();
    }
}
