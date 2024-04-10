package com.utaha.kazusa.api.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderBookQryRequestBody {

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
