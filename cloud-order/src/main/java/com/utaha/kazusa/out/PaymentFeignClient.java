package com.utaha.kazusa.out;

import com.utaha.kazusa.out.message.book.CloudBookQryRequest;
import com.utaha.kazusa.out.message.book.CloudBookQryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cloud-payment")
public interface PaymentFeignClient {

    @PostMapping(path = "/cloud-payment/bookListQry", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CloudBookQryResponse bookQry(@RequestBody CloudBookQryRequest request);

}
