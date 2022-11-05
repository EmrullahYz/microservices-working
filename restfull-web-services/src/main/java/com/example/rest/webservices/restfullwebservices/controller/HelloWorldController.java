package com.example.rest.webservices.restfullwebservices.controller;

import com.example.rest.webservices.restfullwebservices.bean.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;
    public HelloWorldController(MessageSource messageSource){
        this.messageSource= messageSource;
    }

    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "Hello world";
    }

    @GetMapping(path = "/hello-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping(path = "/hello-bean/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello , %s",name));
    }

    @GetMapping(path = "/hello-Internationalized")
    public String helloWorldInternationalized(){
       Locale locale = LocaleContextHolder.getLocale();
       return  messageSource.getMessage("good.morning.message", null, "Default Message", locale);
       // return "Hello world";
    }
}
