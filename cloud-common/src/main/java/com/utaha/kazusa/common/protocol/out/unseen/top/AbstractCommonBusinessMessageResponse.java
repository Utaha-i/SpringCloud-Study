package com.utaha.kazusa.common.protocol.out.unseen.top;

public abstract class AbstractCommonBusinessMessageResponse<REQ extends AbstractCommonBusinessMessageRequest<?>>
        implements CommonBusinessMessageResponse<REQ> {
    @Override
    public REQ createRequest() {
        return CommonBusinessMessageUtils.create(this.getClass(), AbstractCommonBusinessMessageRequest.class);
    }
}
