package br.com.intelipost.second.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lucastex on 29/06/17.
 */

@Configuration
public class InterceptorsConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/dashboard");
    }
}
