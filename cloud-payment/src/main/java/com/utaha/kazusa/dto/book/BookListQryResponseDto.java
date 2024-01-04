package com.utaha.kazusa.dto.book;

import com.utaha.kazusa.common.protocol.vo.CommonResponseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookListQryResponseDto extends CommonResponseVo<BookListQryResponseBody> implements Serializable {
}
