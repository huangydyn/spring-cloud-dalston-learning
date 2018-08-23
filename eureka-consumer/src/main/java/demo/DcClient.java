package demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client-a")
public interface DcClient {

    @GetMapping("/dc")
    String consumer();

}