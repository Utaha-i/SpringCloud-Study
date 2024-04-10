package com.utaha.kazusa.common.protocol.out.unseen.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utaha.kazusa.common.protocol.out.unseen.top.CommonBusinessMessageResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class CloudResponse<REQ extends CloudRequest<?, ?>, BODY extends AbstractCloudResponseBody<?>>
        extends AbstractCloudResponse<REQ, CloudResponseHead, BODY>
        implements CommonBusinessMessageResponse<REQ> {

    @Override
    public REQ createRequest() {
        //尝试调用这个方法是不支持的
        throw new UnsupportedOperationException();
    }

    @JsonProperty("Head")
    @NotNull
    @Valid
    private CloudResponseHead Head;

    @JsonProperty("Head")
    @Valid
    private BODY Body;
}
