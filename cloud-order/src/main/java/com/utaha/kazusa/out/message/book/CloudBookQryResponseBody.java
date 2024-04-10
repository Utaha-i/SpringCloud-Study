package com.utaha.kazusa.out.message.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.utaha.kazusa.common.protocol.out.unseen.message.AbstractCloudResponseBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public final class CloudBookQryResponseBody extends AbstractCloudResponseBody<CloudBookQryRequestBody> {

    @JsonProperty("TotalNo")
    private Integer totalNo;

    @JsonProperty("BookMsgArray")
    private java.util.List<BookMsg> bookMsgArray;

    @Data
    @NoArgsConstructor
    @Valid
    public static final class BookMsg {
        @JsonProperty("id")
        @NotNull
        private Long id;

        @JsonProperty("name")
        @NotNull
        @NotBlank
        @Size(max = 128)
        private String name;

        @JsonProperty("price")
        @NotNull
        @NotBlank
        @Size(max = 26)
        private BigDecimal price;

        @JsonProperty("author")
        @NotNull
        @NotBlank
        @Size(max = 64)
        private String author;

        @JsonProperty("publisher")
        @NotNull
        @NotBlank
        @Size(max = 128)
        private String publisher;

        @JsonProperty("pubDateTime")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime pubDateTime;

        @JsonProperty("pubDate")
        private LocalDate pubDate;

        @JsonProperty("description")
        @NotNull
        @NotBlank
        @Size(max = 256)
        private String description;

        @JsonProperty("categoryId")
        @NotNull
        @NotBlank
        @Size(max = 100)
        private String categoryId;

        @JsonProperty("image")
        @NotNull
        @NotBlank
        @Size(max = 256)
        private String image;
    }
}
