package com.utaha.kazusa.common.protocol.out.unseen.top;

public interface CommonBusinessMessageRequest<RES extends CommonBusinessMessageResponse<?>> {
    RES createResponse();
}
