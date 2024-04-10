package com.utaha.kazusa.convert.book;

import com.utaha.kazusa.api.dto.book.OrderBookQryRequestBody;
import com.utaha.kazusa.api.dto.book.OrderBookQryResponseBody;
import com.utaha.kazusa.out.message.book.CloudBookQryResponse;
import com.utaha.kazusa.service.book.bo.OrderBookQryRequestBo;
import com.utaha.kazusa.service.book.bo.OrderBookQryResponseBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderBookQryConverter {

    OrderBookQryConverter Instance = Mappers.getMapper(OrderBookQryConverter.class);

    OrderBookQryRequestBo convert(OrderBookQryRequestBody body);

    OrderBookQryResponseBody convert(OrderBookQryResponseBo responseBo);

    /**
     * out 响应转化
     * @param outResponse outResponse
     * @return resBo
     */
    OrderBookQryResponseBo convert(CloudBookQryResponse outResponse);
}
