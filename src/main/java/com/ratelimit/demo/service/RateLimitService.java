package com.ratelimit.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Service
@EnableScheduling
public class RateLimitService {
    private static long timeLimit=20;
    private static HashMap<String,Integer> hm=new HashMap<String, Integer>();
    /*
    inserting mobile number in hashmap for quickl retrival and incrementing value for each time
     */

    public int  Ratelimit(String number){
        if(!hm.containsKey(number)){
            hm.put(number,1);
            return 20-hm.get(number);
        }
        else{
            if(20-hm.get(number)==0)return 0;
            else {
                hm.put(number, hm.get(number)+1);
                return 20 - hm.get(number);
            }
            }
        }
        //return new ResponseEntity<>("",HttpStatus.BAD_GATEWAY);
/*
used a cron job for clearing hashmap for every hour
 */

    @Scheduled(fixedRate = 3600000)
    public void scheduleFixedRateTask() {
        System.out.println("enaabled scheduling");
        hm.clear();
    }
}
