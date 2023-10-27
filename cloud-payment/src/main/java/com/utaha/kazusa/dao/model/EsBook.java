package com.utaha.kazusa.dao.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("es_book")
public class EsBook {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "price")
    private BigDecimal price;

    @TableField(value = "author")
    private String author;

    @TableField(value = "publisher")
    private String publisher;

    @TableField(value = "pubDateTime")
    private LocalDateTime pubDateTime;

    @TableField(value = "pubDate")
    private LocalDate pubDate;

    @TableField(value = "description")
    private String description;

    @TableField(value = "category_id")
    private String categoryId;

    @TableField(value = "image")
    private String image;
}
