package com.utaha.kazusa.common.protocol.out.unseen.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utaha.kazusa.common.protocol.out.unseen.top.CommonBusinessMessageRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CloudRequest<RES extends CloudResponse<?, ?>, BODY extends AbstractCloudRequestBody<?>>
        extends AbstractCloudRequest<RES, CloudRequestHead, BODY>
        implements CommonBusinessMessageRequest<RES> {

    @Override
    public RES createResponse() {
        RES response = super.createResponse();
        response.setHead(this.getHead().createResponse());
        return response;
    }

    @JsonProperty("Head")
    @NotNull
    @Valid
    private CloudRequestHead Head;

    @JsonProperty("Head")
    @Valid
    private BODY Body;

}
