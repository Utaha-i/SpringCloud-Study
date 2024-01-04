package com.utaha.kazusa.common.protocol.out.unseen.message;

public interface CommonBusinessMessageRequest<RES extends CommonBusinessMessageResponse<?>> {
    RES createResponse();
}
