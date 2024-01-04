package com.utaha.kazusa.controller;

import com.utaha.kazusa.api.BookQryApi;
import com.utaha.kazusa.convert.book.BookLIstQryConverter;
import com.utaha.kazusa.dto.book.BookListQryRequestDto;
import com.utaha.kazusa.dto.book.BookListQryResponseBody;
import com.utaha.kazusa.dto.book.BookListQryResponseDto;
import com.utaha.kazusa.service.book.BookListQryService;
import com.utaha.kazusa.service.book.bo.BookListQryRequestBo;
import com.utaha.kazusa.service.book.bo.BookListQryResponseBo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BookListQryController implements BookQryApi {

    @Resource
    private BookListQryService bookListQryService;

    public BookListQryResponseDto query(@Valid @RequestBody BookListQryRequestDto reqDto) {
        BookListQryRequestBo requestBo = BookLIstQryConverter.Instance.convert(reqDto.getBody());
        BookListQryResponseBo responseBo = bookListQryService.bookListQry(requestBo);
        BookListQryResponseDto responseDto = new BookListQryResponseDto();
        BookListQryResponseBody responseBody = BookLIstQryConverter.Instance.convert(responseBo);
        responseDto.setBody(responseBody);
        return responseDto;
    }

}
