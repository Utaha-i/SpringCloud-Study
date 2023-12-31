package com.utaha.kazusa.controller;

import com.utaha.kazusa.convert.book.BookLIstQryConverter;
import com.utaha.kazusa.dto.BookListQryRequestDto;
import com.utaha.kazusa.dto.BookListQryResponseBody;
import com.utaha.kazusa.dto.BookListQryResponseDto;
import com.utaha.kazusa.service.book.BookListQryService;
import com.utaha.kazusa.service.book.bo.BookListQryRequestBo;
import com.utaha.kazusa.service.book.bo.BookListQryResponseBo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@Slf4j
public class BookListQryController {

    @Resource
    private BookListQryService bookListQryService;

    @GetMapping("/listQry")
    public BookListQryResponseDto query(@RequestBody BookListQryRequestDto reqDto) {
        BookListQryRequestBo requestBo = BookLIstQryConverter.Instance.convert(reqDto.getBody());
        BookListQryResponseBo responseBo = bookListQryService.bookListQry(requestBo);
        BookListQryResponseDto responseDto = new BookListQryResponseDto();
        BookListQryResponseBody responseBody = BookLIstQryConverter.Instance.convert(responseBo);
        responseDto.setBody(responseBody);
        return responseDto;
    }

}
