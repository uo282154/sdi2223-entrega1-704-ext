package com.uniovi.sdi.sdi2223entrega171;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.services.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

@Configuration
public class CustomConfiguration implements WebMvcConfigurer {



    @Value("${spring.data.web.pageable.page-parameter}")
    private int page;

    @Value("${spring.data.web.pageable.default-page-size}")
    private int size;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //int page = 0;
        //int size = 2;
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
        resolver.setFallbackPageable(PageRequest.of(page, size));
        argumentResolvers.add(resolver);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("es", "ES"));
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(handlerInterceptor());
    }

    @Autowired
    private LogService logService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public HandlerInterceptor handlerInterceptor(){

        return new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                List<String> endPoints = Arrays.asList("/user/list", "/user/add", "/user/delete/{id}", "/user/details/{id}",
                        "/user/list/update", "/user/edit/{id}", "/signup", "/login", "/home",
                        "offer/my", "/offer/add" , "/offer/delete/{id}"  , "offer/listAll",
                        "/chat/list",
                        "/log/list", "/log/list/update", "/log/deleteAll"
                        );

                for (String endPoint: endPoints){
                    if (request.getRequestURI().equals(endPoint)){
                        String description = "Mapping: " + request.getRequestURI()
                                + " Method: " + request.getMethod()
                                + " Params: " + getParametersOfRequest(request);
                        logService.addLog(Log.LogItemType.PET, description);
                        logger.info("New request");

                        return true;
                    }
                }
                for (String endPoint: endPoints){
                    if (request.getRequestURI().contains(endPoint)){
                        String[] params = request.getRequestURI().split("/");
                        String description = "Mapping: " + request.getRequestURI()
                                + " Method: " + request.getMethod()
                                + " Params: id=" + params[params.length - 1];
                        logService.addLog(Log.LogItemType.PET, description);
                        logger.info("New request");

                        return true;
                    }
                }
                return true;
            }
        };
    }

    private String getParametersOfRequest(HttpServletRequest request) {
        StringBuilder s = new StringBuilder();
        Enumeration<?> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            if (s.length() > 1) {
                s.append("&");
            }
            String curr = (String) e.nextElement();
            s.append(curr).append("=");
            if (curr.contains("password")
                    || curr.contains("pass")
                    || curr.contains("pwd")) {
                s.append("***");
            } else {
                s.append(request.getParameter(curr));
            }
        }
        return s.toString();
    }
}
