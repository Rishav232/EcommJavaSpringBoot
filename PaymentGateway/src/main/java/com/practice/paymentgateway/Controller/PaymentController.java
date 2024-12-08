package com.practice.paymentgateway.Controller;

import com.practice.paymentgateway.Dto.PaymentDto;
import com.practice.paymentgateway.Service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    IPaymentService iPaymentService;

    @PostMapping
    public String getPaymentLink(PaymentDto paymentDto)
    {
        String result = iPaymentService.getPaymentLink();

        return result;
    }
}
