package com.tienda;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Bean
    public LocaleResolver localResolver(){
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale (new Locale("es"));
                return slr;
    }
    
    
    
    @Bean
    public LocaleChangeInterceptor localChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
                return lci;
    }
    
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localChangeInterceptor());
    }
    @Override
    public void addViewControllers(ViewControllerRegistry ver){
        ver.addViewController("/").setViewName("index");
        ver.addViewController("/login");
        ver.addViewController("/errores/403").setViewName("/errores/403");
    }
}



