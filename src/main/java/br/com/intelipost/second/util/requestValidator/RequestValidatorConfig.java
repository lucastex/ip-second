package br.com.intelipost.second.util.requestValidator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lucastex on 29/06/17.
 */
@Configuration
public class RequestValidatorConfig {

    @Value("${ip.session.strategy}")
    private String sessionStategy;

    @Bean("webRequestValidator")
    public RequestValidator getCookieRequestValidator() throws IllegalAccessException, InstantiationException {
        return SessionStrategy.valueOf(sessionStategy).getClazz().newInstance();
    }

    @Bean("restRequestValidator")
    public RequestValidator getRestRequestValidator() {
        return new HeaderRequestValidator();
    }

    private enum SessionStrategy {

        cookie("cookie", CookieRequestValidator.class),
        http("http", SessionRequestValidator.class);

        private String value;
        private Class clazz;

        SessionStrategy(String value, Class requestValidatorClass) {
            this.value = value;
            this.clazz = requestValidatorClass;
        }

        public String getValue() {
            return value;
        }

        public Class<RequestValidator> getClazz() {
            return clazz;
        }
    }
}
