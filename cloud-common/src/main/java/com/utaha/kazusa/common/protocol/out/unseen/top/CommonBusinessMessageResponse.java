package com.utaha.kazusa.common.protocol.out.unseen.top;

public interface CommonBusinessMessageResponse<REQ extends CommonBusinessMessageRequest<?>> {
    REQ createRequest();
}
