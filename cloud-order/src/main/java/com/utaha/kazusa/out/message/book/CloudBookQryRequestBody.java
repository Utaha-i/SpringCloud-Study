package com.utaha.kazusa.out.message.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utaha.kazusa.common.protocol.out.unseen.message.AbstractCloudRequestBody;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public final class CloudBookQryRequestBody extends AbstractCloudRequestBody<CloudBookQryResponseBody> {

    @JsonProperty("Price")
    @Min(value = 0)
    @Max(value = 10)
    private BigDecimal price;

    @JsonProperty("BeginPubDate")
    private LocalDate beginPubDate;

    @JsonProperty("EndPubDate")
    private LocalDate endPubDate;

    @JsonProperty("BeginPage")
    @NotNull
    private Integer beginPage;

    @JsonProperty("PageRecordsNo")
    @NotNull
    @Max(value = 500)
    private Integer pageRecordsNo;
}
