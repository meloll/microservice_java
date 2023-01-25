package com.github.meloll.hrpayroll.resources;

import com.github.meloll.hrpayroll.entities.Payment;
import com.github.meloll.hrpayroll.services.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payments")
@AllArgsConstructor
public class PaymentResource {

    private PaymentService service;

    @GetMapping(value = "/{workerId}/days/{days}")
    @ResponseBody
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days ){

        Payment payment = service.getPayment(workerId,days);

        return ResponseEntity.ok(payment);
    }



}
