package com.github.meloll.hrpayroll.services;

import com.github.meloll.hrpayroll.entities.Payment;
import com.github.meloll.hrpayroll.vo.Worker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

    private CommunicatorWorker communicatorWorker;

    public Payment getPayment(Long workerId, Integer days){

        Worker worker = communicatorWorker.getWorkerForId(workerId);

        return new Payment(worker.getName(),worker.getDailyIncome(),days);

    }
}
