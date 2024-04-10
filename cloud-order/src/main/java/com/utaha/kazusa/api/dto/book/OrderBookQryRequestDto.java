package com.utaha.kazusa.api.dto.book;

import com.utaha.kazusa.common.protocol.vo.CommonRequestVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderBookQryRequestDto extends CommonRequestVo<OrderBookQryRequestBody> implements Serializable {
}
