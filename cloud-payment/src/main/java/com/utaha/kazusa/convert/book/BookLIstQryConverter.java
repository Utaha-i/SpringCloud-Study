package com.utaha.kazusa.convert.book;

import com.utaha.kazusa.dao.model.EsBook;
import com.utaha.kazusa.dto.BookListQryRequestBody;
import com.utaha.kazusa.dto.BookListQryResponseBody;
import com.utaha.kazusa.service.book.bo.BookListQryRequestBo;
import com.utaha.kazusa.service.book.bo.BookListQryResponseBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookLIstQryConverter {

    BookLIstQryConverter Instance = Mappers.getMapper(BookLIstQryConverter.class);

    BookListQryRequestBo convert(BookListQryRequestBody body);

    BookListQryResponseBody convert(BookListQryResponseBo responseBo);

    BookListQryResponseBody.BookMsg convert(EsBook esBook);
}
