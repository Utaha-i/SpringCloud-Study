package com.utaha.kazusa.common.protocol.out.unseen;

import com.utaha.kazusa.common.protocol.out.unseen.message.AbstractCommonBusinessMessageRequest;

public abstract class AbstractCloudRequestHead<T extends AbstractCloudResponseBody<?>>
        extends AbstractCommonBusinessMessageRequest<T> {
}
