package com.utaha.kazusa.common.protocol.out.unseen;

import com.utaha.kazusa.common.protocol.out.unseen.message.AbstractCommonBusinessMessageRequest;

public abstract class AbstractCloudRequest<RES extends AbstractCloudResponse<?, ?, ?>, HEAD extends AbstractCloudRequestHead<?>, BODY extends AbstractCloudRequestBody<?>>
        extends AbstractCommonBusinessMessageRequest<RES> {
}