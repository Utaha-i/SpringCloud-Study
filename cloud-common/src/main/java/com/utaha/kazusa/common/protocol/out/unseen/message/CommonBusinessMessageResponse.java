package com.utaha.kazusa.common.protocol.out.unseen.message;

public interface CommonBusinessMessageResponse<REQ extends CommonBusinessMessageRequest<?>> {
    REQ createRequest();
}
