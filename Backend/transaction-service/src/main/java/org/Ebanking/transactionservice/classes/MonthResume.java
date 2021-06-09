package org.Ebanking.transactionservice.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Month;
import java.util.concurrent.atomic.AtomicReference;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class MonthResume {


    private Double income;

    private Double outcome;

    private Double Balance;

    private int month;

    private Long accountNum;


}
