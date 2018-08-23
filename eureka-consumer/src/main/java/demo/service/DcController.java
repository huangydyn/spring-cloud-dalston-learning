package demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {

    private static Logger log = LoggerFactory.getLogger(DcController.class);

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DcClient dcClient;

    @Autowired
    ConsumerService consumerService;

    @Value("${print.string:default}")
    String printString;

    @GetMapping("/loadBalancerClient")
    public String loadBalancerClient() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        System.out.println(url);
        return new RestTemplate().getForObject(url, String.class);
    }

    @GetMapping("/ribbon")
    public String ribbon() {
        log.info("trace request ribbon");
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }

    @GetMapping("/value")
    public String value() {
        log.info("get value");
        return printString;
    }

    @GetMapping("/feign")
    public String feign() {
        return dcClient.consumer();
    }

    @GetMapping("/fallBack")
    public String fallBack() {
        return consumerService.consumer();
    }
}
