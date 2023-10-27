package com.utaha.kazusa.common.protocol.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class CommonRequestHead implements Serializable {

    /**
     * 流水号
     */
    @NotNull
    @JsonProperty("SerialNo")
    @NotBlank
    @Size(min = 16, max = 32)
    private String serialNo;

    /**
     * 交易日期
     */
    @NotNull
    @JsonProperty("TxnDate")
    @NotBlank
    @Size(min = 10, max = 10)
    private String txnDate;

    /**
     * 交易时间
     */
    @NotNull
    @JsonProperty("TxnTime")
    @NotBlank
    @Size(min = 19, max = 19)
    private String txnTime;

}
