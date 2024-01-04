package com.utaha.kazusa.common.protocol.out.unseen;

import com.utaha.kazusa.common.protocol.out.unseen.message.AbstractCommonBusinessMessageResponse;

public abstract class AbstractCloudResponseHead<T extends AbstractCloudRequestHead<?>>
        extends AbstractCommonBusinessMessageResponse<T> {
}
