package com.utaha.kazusa.common.protocol.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class CommonRequestVo<Body> implements Serializable {

    @Valid
    @NotNull
    @JsonProperty("Head")
    private CommonRequestHead head;

    @Valid
    @NotNull
    @JsonProperty("Body")
    private Body body;

}
