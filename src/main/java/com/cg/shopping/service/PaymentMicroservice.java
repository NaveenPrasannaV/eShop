package com.cg.shopping.service;

import org.springframework.http.ResponseEntity;

import com.cg.shopping.model.PaymentStatusDTO;

public interface PaymentMicroservice {

	ResponseEntity<PaymentStatusDTO> DemoMicroServicePaymentCall();

}
