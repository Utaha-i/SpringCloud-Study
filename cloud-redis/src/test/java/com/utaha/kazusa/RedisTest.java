package com.utaha.kazusa;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.utaha.kazusa.service.BookListCacheService;
import com.utaha.kazusa.service.bo.BookListCacheRequestBo;
import com.utaha.kazusa.service.bo.BookListCacheResponseBo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BookListCacheService bookListCacheService;

    /**
     *
     */
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

    @Test
    void getListCache1() {
        BookListCacheRequestBo requestBo = new BookListCacheRequestBo();
        BookListCacheResponseBo responseBo = bookListCacheService.bookListCacheGet(requestBo);
        List<BookListCacheResponseBo.BookMsg> bookMsgArray;
        bookMsgArray = responseBo.getBookMsgArray();
        boolean empty = bookMsgArray.isEmpty();
        System.out.println(empty);
        System.out.println(bookMsgArray);
    }

    @Test
    void setLocalDateTime() {
        String now = LocalDateTime.now().toString();
        redisTemplate.opsForValue().set("TestLocalDateTime", now, 360, TimeUnit.SECONDS);
    }

    @Test
    void getLocalDateTime() {
        String storedDateTimeStr = (String) redisTemplate.opsForValue().get("TestLocalDateTime");
        LocalDateTime storedDateTime = LocalDateTime.parse(storedDateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(storedDateTime);
    }
}
