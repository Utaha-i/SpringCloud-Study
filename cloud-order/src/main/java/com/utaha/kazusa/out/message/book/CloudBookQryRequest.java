package com.utaha.kazusa.out.message.book;

import com.utaha.kazusa.common.protocol.out.unseen.message.CloudRequest;
import com.utaha.kazusa.common.protocol.out.unseen.message.CloudRequestHead;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class CloudBookQryRequest extends CloudRequest<CloudBookQryResponse, CloudBookQryRequestBody> {

    public CloudBookQryRequest(CloudRequestHead head , CloudBookQryRequestBody body){
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
