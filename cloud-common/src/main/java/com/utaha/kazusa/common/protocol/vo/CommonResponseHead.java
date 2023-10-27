package com.utaha.kazusa.common.protocol.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class CommonResponseHead implements Serializable {

    /**
     * 流水号
     */

    @JsonProperty("SerialNo")
    @NotNull
    @NotBlank
    @Size(min = 16, max = 32)
    private String serialNo;

    /**
     * 交易日期
     */
    @JsonProperty("TxnDate")
    @NotNull
    @NotBlank
    @Size(min = 10, max = 10)
    private String txnDate;

    /**
     * 交易时间
     */
    @JsonProperty("TxnTime")
    @NotNull
    @NotBlank
    @Size(min = 19, max = 19)
    private String txnTime;

    /**
     * 返回码
     */
    @JsonProperty("TxnTime")
    @NotNull
    @NotBlank
    @Size(min = 19, max = 19)
    private String retCode;


    /**
     * 返回信息
     */
    @JsonProperty("TxnTime")
    @NotNull
    @NotBlank
    @Size(min = 19, max = 19)
    private String retMessage;

}
