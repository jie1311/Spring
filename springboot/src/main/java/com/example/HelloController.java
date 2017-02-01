package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiezhang on 1/2/17.
 */

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }
}
