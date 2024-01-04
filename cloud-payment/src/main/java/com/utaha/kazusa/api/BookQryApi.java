package com.utaha.kazusa.api;

import com.utaha.kazusa.dto.book.BookListQryRequestDto;
import com.utaha.kazusa.dto.book.BookListQryResponseDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = ConstantsApi.SPRING_APPLICATION_NAME)
public interface BookQryApi {

    /**
     * 查询书籍列表
     */
    @PostMapping(path = ConstantsApi.SPRING_APPLICATION_NAME + "/bookListQry", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    BookListQryResponseDto query(@Valid @RequestBody BookListQryRequestDto reqDto);

}