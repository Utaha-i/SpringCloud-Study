package com.utaha.kazusa.service.book.impl;

import com.utaha.kazusa.common.protocol.out.unseen.message.CloudRequestHead;
import com.utaha.kazusa.convert.book.OrderBookQryConverter;
import com.utaha.kazusa.out.PaymentOut;
import com.utaha.kazusa.out.message.book.CloudBookQryRequest;
import com.utaha.kazusa.out.message.book.CloudBookQryRequestBody;
import com.utaha.kazusa.out.message.book.CloudBookQryResponse;
import com.utaha.kazusa.service.book.OrderBookQryService;
import com.utaha.kazusa.service.book.bo.OrderBookQryRequestBo;
import com.utaha.kazusa.service.book.bo.OrderBookQryResponseBo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderBookQryServiceImpl implements OrderBookQryService {

    @Resource
    private PaymentOut paymentOut;

    @Override
    public OrderBookQryResponseBo orderBookQry(OrderBookQryRequestBo reqBo) {
        CloudBookQryRequestBody requestBody = new CloudBookQryRequestBody();
        requestBody.setBeginPage(reqBo.getBeginPage());
        requestBody.setPageRecordsNo(reqBo.getPageRecordsNo());
        CloudRequestHead cloudRequestHead = new CloudRequestHead();
        cloudRequestHead.setSerialNo("1234567890123456");
        cloudRequestHead.init();
        CloudBookQryRequest cloudBookQryRequest = new CloudBookQryRequest(cloudRequestHead, requestBody);
        CloudBookQryResponse outResponse = paymentOut.bookQry(cloudBookQryRequest);
        OrderBookQryResponseBo resBo = OrderBookQryConverter.Instance.convert(outResponse);
        return resBo;
    }

}
