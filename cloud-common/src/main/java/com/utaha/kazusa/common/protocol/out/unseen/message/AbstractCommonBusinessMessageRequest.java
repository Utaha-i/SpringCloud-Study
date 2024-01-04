package com.utaha.kazusa.common.protocol.out.unseen.message;

public abstract class AbstractCommonBusinessMessageRequest<RES extends AbstractCommonBusinessMessageResponse<?>>
        implements CommonBusinessMessageRequest<RES> {
    @Override
    public RES createResponse() {
        return null;
    }
}
