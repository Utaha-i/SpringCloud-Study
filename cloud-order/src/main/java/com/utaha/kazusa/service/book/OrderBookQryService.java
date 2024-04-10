package com.utaha.kazusa.service.book;

import com.utaha.kazusa.service.book.bo.OrderBookQryRequestBo;
import com.utaha.kazusa.service.book.bo.OrderBookQryResponseBo;

public interface OrderBookQryService {

    OrderBookQryResponseBo orderBookQry(OrderBookQryRequestBo reqBo);
}
