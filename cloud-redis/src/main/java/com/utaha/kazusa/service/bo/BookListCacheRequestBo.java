package com.utaha.kazusa.service.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BookListCacheRequestBo {

    private java.util.List<BookMsg> bookMsgArray;

    @Data
    @NoArgsConstructor
    public static final class BookMsg {
        private Long id;

        private String name;

        private BigDecimal price;

        private String author;

        private String publisher;

        private LocalDateTime pubDateTime;

        private LocalDate pubDate;

        private String description;

        private String categoryId;

        private String image;
    }

}
