package com.example.springbootjdbc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception e) {
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("errorPage");
        errorPage.addObject("errorMessage", e.getMessage());
        return errorPage;
    }
}
