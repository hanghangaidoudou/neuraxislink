package com.etong.pms.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName ActiveMQProducer
 * @Author wch
 * @DATE 2019/10/15 11:35
 * @return
 */
@Component
public class ActiveMQProducerTask {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Scheduled(cron="*/5 * * * * ?")
    private void process(){
        LocalDateTime data =  LocalDateTime.now();
        System.out.println("New Time " + data.format(formatter));
    }

}
