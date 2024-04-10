package com.utaha.kazusa.out;

import com.utaha.kazusa.out.message.book.CloudBookQryRequest;
import com.utaha.kazusa.out.message.book.CloudBookQryResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PaymentOutImpl implements PaymentOut {

    @Resource
    private PaymentFeignClient feignClient;

    @Override
    public CloudBookQryResponse bookQry(CloudBookQryRequest request) {
        return feignClient.bookQry(request);
    }
}
