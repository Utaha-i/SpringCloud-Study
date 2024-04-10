package com.utaha.kazusa.common.protocol.out.unseen.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utaha.kazusa.common.protocol.out.unseen.convert.ToHeadConverter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
public final class CloudResponseHead extends AbstractCloudResponseHead<CloudRequestHead>{

    @Override
    public CloudRequestHead createRequest() {
        return ToHeadConverter.Instance.converter(this);
    }

    public void init(){
        //date和time获取要保证原子性
        this.setTxnDate(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        this.setTxnTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * 流水号
     */
    @NotNull
    @JsonProperty("SerialNo")
    @NotBlank
    @Size(min = 16, max = 32)
    private String serialNo;

    /**
     * 交易日期 yyyy-MM-dd
     */
    @NotNull
    @JsonProperty("TxnDate")
    @NotBlank
    @Size(min = 10, max = 10)
    private String txnDate;

    /**
     * 交易时间 yyyy-MM-dd HH:mm:ss
     */
    @NotNull
    @JsonProperty("TxnTime")
    @NotBlank
    @Size(min = 19, max = 19)
    private String txnTime;
}
