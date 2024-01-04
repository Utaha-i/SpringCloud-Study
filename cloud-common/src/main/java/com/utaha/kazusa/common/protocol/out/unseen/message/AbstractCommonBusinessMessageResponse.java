package com.utaha.kazusa.common.protocol.out.unseen.message;

public abstract class AbstractCommonBusinessMessageResponse<REQ extends AbstractCommonBusinessMessageRequest<?>>
        implements CommonBusinessMessageResponse<REQ> {
    @Override
    public REQ createRequest() {
        return null;
    }
}
