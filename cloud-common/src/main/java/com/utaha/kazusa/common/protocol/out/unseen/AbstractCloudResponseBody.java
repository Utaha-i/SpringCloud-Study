package com.utaha.kazusa.common.protocol.out.unseen;

import com.utaha.kazusa.common.protocol.out.unseen.message.AbstractCommonBusinessMessageResponse;

public abstract class AbstractCloudResponseBody<T extends AbstractCloudRequestBody<?>>
        extends AbstractCommonBusinessMessageResponse<T> {
}