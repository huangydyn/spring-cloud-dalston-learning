package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DcController {

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DcClient dcClient;

    @Autowired
    ConsumerService consumerService;

    @GetMapping("/loadBalancerClient")
    public String loadBalancerClient() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client-a");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        System.out.println(url);
        return new RestTemplate().getForObject(url, String.class);
    }

    @GetMapping("/ribbon")
    public String ribbon() {
        return restTemplate.getForObject("http://eureka-client-a/dc", String.class);
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