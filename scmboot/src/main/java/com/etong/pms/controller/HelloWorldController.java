package com.etong.pms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping(value = "/ETong", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HelloWorldController {

    @RequestMapping("/print")
    public String sayHelloWorld() {
        log.info("sayHelloWorld begin");
        return "hello world";
    }

}
