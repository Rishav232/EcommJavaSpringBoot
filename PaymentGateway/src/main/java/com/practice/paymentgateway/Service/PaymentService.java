package com.practice.paymentgateway.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService{

    @Autowired
    private PaymentChooser paymentChooser;

    @Override
    public String getPaymentLink() {
        IPaymentGateway iPaymentGateway= paymentChooser.getPaymentGateway();

        return iPaymentGateway.getPaymentLink();
    }
}
