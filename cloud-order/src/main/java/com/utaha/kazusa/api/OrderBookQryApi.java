package com.utaha.kazusa.api;

import com.utaha.kazusa.api.dto.book.OrderBookQryRequestDto;
import com.utaha.kazusa.api.dto.book.OrderBookQryResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = ConstantsApi.SPRING_APPLICATION_NAME)
public interface OrderBookQryApi {

    @PostMapping(path = ConstantsApi.SPRING_APPLICATION_NAME + "/orderBookQry", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    OrderBookQryResponseDto orderBookQry(@Valid @RequestBody OrderBookQryRequestDto reqDto);

}
