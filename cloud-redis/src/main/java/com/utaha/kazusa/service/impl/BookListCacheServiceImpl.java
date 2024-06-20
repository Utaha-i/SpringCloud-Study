package com.utaha.kazusa.service.impl;

import com.utaha.kazusa.service.BookListCacheService;
import com.utaha.kazusa.service.bo.BookListCacheRequestBo;
import com.utaha.kazusa.service.bo.BookListCacheResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.utaha.kazusa.constant.RedisKey.BOOK_LIST_KEY;

@Service
public class BookListCacheServiceImpl implements BookListCacheService {

//    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public BookListCacheResponseBo bookListCacheSet(BookListCacheRequestBo requestBo) {
        // 存储到Redis中
        redisTemplate.opsForValue().set(BOOK_LIST_KEY, requestBo.getBookMsgArray());
        // 设置过期时间
        redisTemplate.expire(BOOK_LIST_KEY, 30, TimeUnit.MINUTES);
        return null;
    }

    @Override
    public BookListCacheResponseBo bookListCacheGet(BookListCacheRequestBo requestBo) {
        BookListCacheResponseBo responseBo = new BookListCacheResponseBo();
        // 获取书籍对象
        List<BookListCacheResponseBo.BookMsg> bookMsgArray = (List<BookListCacheResponseBo.BookMsg>) redisTemplate.opsForValue().get(BOOK_LIST_KEY);
        responseBo.setBookMsgArray(bookMsgArray);
        return responseBo;
    }

    public void bookListCacheDelete() {
        // 删除书籍列表
        redisTemplate.delete(BOOK_LIST_KEY);
    }
}
