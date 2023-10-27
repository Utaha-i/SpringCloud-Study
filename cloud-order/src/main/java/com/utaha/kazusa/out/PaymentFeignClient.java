package com.utaha.kazusa.out;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "CLOUD-PAYMENT")
public interface PaymentFeignClient {

}
