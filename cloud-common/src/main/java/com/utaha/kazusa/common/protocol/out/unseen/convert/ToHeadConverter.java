package com.utaha.kazusa.common.protocol.out.unseen.convert;


import com.utaha.kazusa.common.protocol.out.unseen.message.CloudRequestHead;
import com.utaha.kazusa.common.protocol.out.unseen.message.CloudResponseHead;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ToHeadConverter {

    ToHeadConverter Instance = Mappers.getMapper(ToHeadConverter.class);

    /**
     * 请求头转响应头
     * @param requestHead 请求头
     * @return 响应头
     */
    CloudResponseHead converter(CloudRequestHead requestHead);

    /**
     * 响应头转请求头
     * @param responseHead 响应头
     * @return 请求头
     */
    CloudRequestHead converter(CloudResponseHead responseHead);
}
