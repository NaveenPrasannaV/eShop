package com.cg.shopping.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.cg.shopping.entity.Cart;
import com.cg.shopping.model.PaymentStatusDTO;
import com.cg.shopping.service.PaymentMicroservice;

import reactor.core.publisher.Mono;

@Service
public class PaymentMicroserviceImpl implements PaymentMicroservice {

	/*
	 * This is an on going Microservice logic, Need to change the Mono object.
	 */
	private final WebClient webClient;

	public PaymentMicroserviceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:7000").build();
	}

	@Override
	public ResponseEntity<PaymentStatusDTO> DemoMicroServicePaymentCall() {
		PaymentStatusDTO paymentStatusDTO = webClient.get().uri("/demopay").retrieve()
				.bodyToMono(PaymentStatusDTO.class).block();

		return new ResponseEntity<>(paymentStatusDTO, HttpStatus.OK);
	}

}
