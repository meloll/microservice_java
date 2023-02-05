package com.github.meloll.hrpayroll.resources;

import com.github.meloll.hrpayroll.entities.Payment;
import com.github.meloll.hrpayroll.services.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/payments")
@AllArgsConstructor
public class PaymentResource {

    private PaymentService service;

    @CircuitBreaker(name = "fallBackMethodPayment",fallbackMethod = "getPaymentFallBack")
    @GetMapping(value = "/{workerId}/days/{days}")
    @ResponseBody
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days ){

        Payment payment = service.getPayment(workerId,days);

        return ResponseEntity.ok(payment);
    }

    public  ResponseEntity<Payment> getPaymentFallBack(Long workerId, Integer days, Exception e){

        Payment payment = new Payment("Fall Back",100.0,days);
        return ResponseEntity.ok(payment) ;
    }


}
