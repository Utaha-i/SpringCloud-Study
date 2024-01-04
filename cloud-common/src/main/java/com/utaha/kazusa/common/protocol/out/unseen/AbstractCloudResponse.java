package com.utaha.kazusa.common.protocol.out.unseen;

import com.utaha.kazusa.common.protocol.out.unseen.message.AbstractCommonBusinessMessageResponse;

public abstract class AbstractCloudResponse<REQ extends AbstractCloudRequest<?, ?, ?>, HEAD extends AbstractCloudResponseHead<?>, BODY extends AbstractCloudResponseBody<?>>
        extends AbstractCommonBusinessMessageResponse<REQ> {
}