package com.ratelimit.demo.controller;

import com.ratelimit.demo.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@EnableScheduling
public class RateController {
    @Autowired
    private RateLimitService service;

    /*
    Callserviceprovider api returns   number of available requests for an particular caller whenever the api is used
    if all requests are used we will return bandwith limit exceeded as response
     */

    @GetMapping("/Callserviceprovider")
    public ResponseEntity<?> Ratelimit(@RequestParam("mobile_number")String mobileNumber){
        int numberOfRequests= service.Ratelimit(mobileNumber);
        if(numberOfRequests==0){

            return new ResponseEntity<>("No more requests are available all are used up", HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
        }
        else{
            return new ResponseEntity<>(20-numberOfRequests+" number of requests are remaining",HttpStatus.OK);
        }

    }
}
