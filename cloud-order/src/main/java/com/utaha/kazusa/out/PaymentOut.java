package com.utaha.kazusa.out;

import com.utaha.kazusa.out.message.book.CloudBookQryRequest;
import com.utaha.kazusa.out.message.book.CloudBookQryResponse;

public interface PaymentOut {

    CloudBookQryResponse bookQry(CloudBookQryRequest request);

}
