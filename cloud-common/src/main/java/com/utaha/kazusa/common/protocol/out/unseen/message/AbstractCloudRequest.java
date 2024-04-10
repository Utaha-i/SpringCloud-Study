package com.utaha.kazusa.common.protocol.out.unseen.message;

import com.utaha.kazusa.common.protocol.out.unseen.top.AbstractCommonBusinessMessageRequest;

public abstract class AbstractCloudRequest<RES extends AbstractCloudResponse<?, ?, ?>, HEAD extends AbstractCloudRequestHead<?>, BODY extends AbstractCloudRequestBody<?>>
        extends AbstractCommonBusinessMessageRequest<RES> {
}