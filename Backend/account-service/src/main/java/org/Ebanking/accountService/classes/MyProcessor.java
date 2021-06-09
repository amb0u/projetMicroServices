package org.Ebanking.accountService.classes;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyProcessor {

    String INPUT1 = "myInput1";
    String INPUT2 = "myInput2";
    String OUTPUT1 = "TreatedRech";
    String OUTPUT2 = "TreatedVir";

    @Input
    SubscribableChannel myInput1();

    @Input
    SubscribableChannel myInput2();


    @Output(OUTPUT1)
    MessageChannel myOutput1();

    @Output(OUTPUT2)
    MessageChannel myOutput2();

}
