package com.utaha.kazusa.service.book.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.utaha.kazusa.convert.book.BookLIstQryConverter;
import com.utaha.kazusa.dao.mapper.EsBookMapper;
import com.utaha.kazusa.dao.model.EsBook;
import com.utaha.kazusa.api.dto.book.BookListQryResponseBody;
import com.utaha.kazusa.service.book.BookListQryService;
import com.utaha.kazusa.service.book.bo.BookListQryRequestBo;
import com.utaha.kazusa.service.book.bo.BookListQryResponseBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询书籍列表
 */
@Service
@Slf4j
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
            log.error("*****" + esBook.getPrice());
            BigDecimal bigDecimal = esBook.getPrice().setScale(0, RoundingMode.HALF_UP);
            log.error("*****" + bigDecimal);
            bookMsgArray.add(bookMsg);
        }
        resBo.setBookMsgArray(bookMsgArray);
        resBo.setTotalNo(Math.toIntExact(esBookPage.getTotal()));
        return resBo;
    }
}
