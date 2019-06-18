package com.example.TelemetryService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@Component
public class RestResource {

    @Autowired
    LoadBalancerClient loadBalancer;

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(initialDelay = 5000, fixedRate = 10000)
    @HystrixCommand(fallbackMethod = "fallBackClient",
            commandKey = "client", groupKey = "client")
    @RequestMapping(path = "/client/", method = RequestMethod.GET)
    public String invokeRoverClient() {

        //wrong
        if (RandomUtils.nextBoolean()){
            throw new RuntimeException("Failed!");
        }

        ServiceInstance instance = loadBalancer.choose("backserver");
        String resp = null;
        for (int i = 1; i < 41; i++) {
            URI backserverUri = URI.create(String.format("http://backserver/rover/rover1/command-control", instance.getHost(), instance.getPort()));
            resp = restTemplate.getForObject(backserverUri, String.class);
            System.out.println("call "+i);
            System.out.println("message sent to "+instance.getPort());

        }
        return resp;
    }

    public String fallBackClient(){
        return "Fall Back Hello initiator";
    }
}
