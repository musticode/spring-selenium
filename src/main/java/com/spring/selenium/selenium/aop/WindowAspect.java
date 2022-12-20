package com.spring.selenium.selenium.aop;

import com.spring.selenium.selenium.annotation.Window;
import com.spring.selenium.selenium.service.WindowSwitchService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WindowAspect {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private WindowSwitchService windowSwitchService;

    @Before("@target(window) && within(com.example.mathserv2.selenium..*)")
    public void before(Window window){
        this.windowSwitchService.switchByTitle(window.value());
    }

    @After("@target(window) && within(com.example.mathserv2.selenium..*)")
    public void after(Window window){
        this.windowSwitchService.switchByIndex(0);
    }


}
