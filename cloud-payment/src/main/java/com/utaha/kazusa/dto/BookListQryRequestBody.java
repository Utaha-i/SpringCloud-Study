package com.utaha.kazusa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookListQryRequestBody {

    @JsonProperty("BeginPubDate")
    @Size(min = 10, max = 10)
    private LocalDate beginPubDate;

    @JsonProperty("EndPubDate")
    @Size(min = 10, max = 10)
    private LocalDate endPubDate;

    @JsonProperty("BeginPage")
    @NotNull
    private Integer beginPage;

    @JsonProperty("PageRecordsNo")
    @NotNull
    @Max(value = 500)
    private Integer pageRecordsNo;
}
