package com.practice.paymentgateway.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentChooser {

    @Autowired
    StripePaymentGateway stripePaymentGateway;

    public IPaymentGateway getPaymentGateway()
    {
        return stripePaymentGateway;
    }
}
