package com.utaha.kazusa;


import com.utaha.kazusa.service.BookListCacheService;
import com.utaha.kazusa.service.bo.BookListCacheRequestBo;
import com.utaha.kazusa.service.bo.BookListCacheResponseBo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class RedisTest {

    @Autowired
    private BookListCacheService bookListCacheService;

    @Test
    void setListCache() {
        BookListCacheRequestBo requestBo = new BookListCacheRequestBo();
        ArrayList<BookListCacheRequestBo.BookMsg> list = new ArrayList<>();
        BookListCacheRequestBo.BookMsg bookMsg = new BookListCacheRequestBo.BookMsg();
        bookMsg.setId(1L);
        bookMsg.setName("玩");
        bookMsg.setImage("/abc/123456.jpg");
        bookMsg.setAuthor("rw");
        bookMsg.setPrice(new BigDecimal("123.00"));
        list.add(bookMsg);
        BookListCacheRequestBo.BookMsg bookMsg2 = new BookListCacheRequestBo.BookMsg();
        bookMsg2.setId(2L);
        bookMsg2.setName("耍");
        bookMsg2.setImage("/abc/987654.jpg");
        bookMsg2.setAuthor("rw");
        bookMsg2.setPrice(new BigDecimal("888.00"));
        list.add(bookMsg2);
        requestBo.setBookMsgArray(list);
        bookListCacheService.bookListCacheSet(requestBo);
    }

    @Test
    void getListCache() {
        BookListCacheRequestBo requestBo = new BookListCacheRequestBo();
        BookListCacheResponseBo responseBo = bookListCacheService.bookListCacheGet(requestBo);
        List<BookListCacheResponseBo.BookMsg> bookMsgArray = responseBo.getBookMsgArray();
        System.out.println(bookMsgArray);
    }
}
