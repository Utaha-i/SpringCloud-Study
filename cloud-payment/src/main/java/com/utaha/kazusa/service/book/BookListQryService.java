package com.utaha.kazusa.service.book;

import com.utaha.kazusa.service.book.bo.BookListQryRequestBo;
import com.utaha.kazusa.service.book.bo.BookListQryResponseBo;

public interface BookListQryService {
    BookListQryResponseBo bookListQry(BookListQryRequestBo reqBo);
}
