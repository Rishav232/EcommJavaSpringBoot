package com.practice.paymentgateway.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebHookController {

    @PostMapping
    public void invokingFunc(@RequestBody String event)
    {
        System.out.println("Rishav Subedar");
        System.out.println(event);
    }
}
