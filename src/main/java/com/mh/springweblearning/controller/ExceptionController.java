package com.mh.springweblearning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
    @RequestMapping("/gen_error")
    public void handleRequest() {
        throw new RuntimeException("test exception");
    }
}
