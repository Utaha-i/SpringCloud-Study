package com.utaha.kazusa.out.message.book;

import com.utaha.kazusa.common.protocol.out.unseen.message.CloudResponse;
import com.utaha.kazusa.common.protocol.out.unseen.message.CloudResponseHead;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class CloudBookQryResponse extends CloudResponse<CloudBookQryRequest, CloudBookQryResponseBody> {

    public CloudBookQryResponse(CloudResponseHead head, CloudBookQryResponseBody body) {
        this.setHead(head);
        this.setBody(body);
    }

    public void init(){
        if (this.getHead() == null) {
            throw new IllegalStateException("head must not be null");
        }
        this.getHead().init();
    }

}
