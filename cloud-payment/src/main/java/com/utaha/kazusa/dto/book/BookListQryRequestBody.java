package com.utaha.kazusa.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookListQryRequestBody {


    @JsonProperty("Test")
    @Size(min = 2, max = 2, message = "长度不匹配")
    private String test;

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
