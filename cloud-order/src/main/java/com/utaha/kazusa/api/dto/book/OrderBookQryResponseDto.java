package com.utaha.kazusa.api.dto.book;

import com.utaha.kazusa.common.protocol.vo.CommonResponseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderBookQryResponseDto extends CommonResponseVo<OrderBookQryResponseBody> implements Serializable {
}
