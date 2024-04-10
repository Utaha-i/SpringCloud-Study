package com.utaha.kazusa.controller;

import com.utaha.kazusa.api.OrderBookQryApi;
import com.utaha.kazusa.api.dto.book.OrderBookQryRequestDto;
import com.utaha.kazusa.api.dto.book.OrderBookQryResponseBody;
import com.utaha.kazusa.api.dto.book.OrderBookQryResponseDto;
import com.utaha.kazusa.convert.book.OrderBookQryConverter;
import com.utaha.kazusa.service.book.OrderBookQryService;
import com.utaha.kazusa.service.book.bo.OrderBookQryRequestBo;
import com.utaha.kazusa.service.book.bo.OrderBookQryResponseBo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderBookQryController implements OrderBookQryApi {

    @Resource
    private OrderBookQryService orderBookQryService;

    @Override
    public OrderBookQryResponseDto orderBookQry(@Valid @RequestBody OrderBookQryRequestDto reqDto) {
        OrderBookQryRequestBo requestBo = OrderBookQryConverter.Instance.convert(reqDto.getBody());
        OrderBookQryResponseBo responseBo = orderBookQryService.orderBookQry(requestBo);
        OrderBookQryResponseDto responseDto = new OrderBookQryResponseDto();
        OrderBookQryResponseBody responseBody = OrderBookQryConverter.Instance.convert(responseBo);
        responseDto.setBody(responseBody);
        return responseDto;
    }

}
