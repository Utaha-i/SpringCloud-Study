package com.utaha.kazusa.common.protocol.out.unseen.top;

public abstract class AbstractCommonBusinessMessageRequest<RES extends AbstractCommonBusinessMessageResponse<?>>
        implements CommonBusinessMessageRequest<RES> {
    @Override
    public RES createResponse() {
        return CommonBusinessMessageUtils.create(this.getClass(), AbstractCommonBusinessMessageResponse.class);
    }
}
