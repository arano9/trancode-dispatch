package com.arano.controller;

import com.arano.annotation.TranCode;
import org.springframework.web.bind.annotation.*;

/**
 * @author arano
 * @since 2021/7/23 14:53
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    @TranCode("tran-hello")
    public String hello(String input) {
        return "hello:" + input;
    }

    @PostMapping("/helloPost")
    @TranCode("tran-hello-post")
    public String helloPost(@RequestBody String input) {
        return "hello-post:" + input;
    }
}
