package br.com.intelipost.second.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lucastex on 29/06/17.
 */

@Configuration
public class InterceptorsConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private WebTokenInterceptor webTokenInterceptor;

    @Autowired
    private RestTokenInterceptor restTokenInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(webTokenInterceptor).addPathPatterns("/dashboard");
        registry.addInterceptor(restTokenInterceptor).addPathPatterns("/api/**");
    }
}
