package com.utaha.kazusa.service;

import com.utaha.kazusa.service.bo.BookListCacheRequestBo;
import com.utaha.kazusa.service.bo.BookListCacheResponseBo;

public interface BookListCacheService {

    /**
     * 设置书籍列表缓存
     *
     * @param requestBo 请求
     * @return Res
     */
    BookListCacheResponseBo bookListCacheSet(BookListCacheRequestBo requestBo);

    /**
     * 获取书籍列表缓存
     *
     * @param requestBo 请求
     * @return Res
     */
    BookListCacheResponseBo bookListCacheGet(BookListCacheRequestBo requestBo);

    /**
     * 删除数据列表缓存
     */
    void bookListCacheDelete();
}
