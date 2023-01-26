package com.github.meloll.hrpayroll.vo;

import lombok.Data;

@Data
public class Worker {

    private Long id;
    private String name;
    //quante recebe por dia
    private Double dailyIncome;


}
