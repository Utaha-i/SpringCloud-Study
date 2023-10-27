package com.utaha.kazusa.service.book.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.utaha.kazusa.convert.book.BookLIstQryConverter;
import com.utaha.kazusa.dao.mapper.EsBookMapper;
import com.utaha.kazusa.dao.model.EsBook;
import com.utaha.kazusa.dto.BookListQryResponseBody;
import com.utaha.kazusa.service.book.BookListQryService;
import com.utaha.kazusa.service.book.bo.BookListQryRequestBo;
import com.utaha.kazusa.service.book.bo.BookListQryResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class BookListQryServiceImpl implements BookListQryService {

    @Autowired
    private EsBookMapper esBookMapper;

    @Override
    public BookListQryResponseBo bookListQry(BookListQryRequestBo reqBo) {
        BookListQryResponseBo resBo = new BookListQryResponseBo();
        LambdaQueryWrapper<EsBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(EsBook::getPubDate, reqBo.getBeginPubDate())
                .le(EsBook::getPubDate, reqBo.getEndPubDate())
                .orderByDesc(EsBook::getPubDateTime);
        Page<EsBook> page = new Page<>(reqBo.getBeginPage(), reqBo.getPageRecordsNo());
        Page<EsBook> esBookPage = esBookMapper.selectPage(page, queryWrapper);
        List<EsBook> esBooks = esBookPage.getRecords();
        ArrayList<BookListQryResponseBody.BookMsg> bookMsgArray = new ArrayList<>();
        for (EsBook esBook : esBooks) {
            BookListQryResponseBody.BookMsg bookMsg = BookLIstQryConverter.Instance.convert(esBook);
            bookMsgArray.add(bookMsg);
        }
        EsBook esBook = new EsBook();
        esBook.setName("测试123");
        esBook.setPrice(new BigDecimal("1222.30"));
        esBook.setAuthor("测试作者");
        resBo.setBookMsgArray(bookMsgArray);
        resBo.setTotalNo(Math.toIntExact(esBookPage.getTotal()));
        return resBo;
    }
}
