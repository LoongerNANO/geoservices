package com.esrichina.geoservices.transfer;

import com.esrichina.geoservices.entity.TSysLogEntity;
import com.esrichina.geoservices.model.example.LogModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 日志管理 - 对象转换
 *
 * @author: LOONGER CHEN
 * @version: 1.0.0
 */
@Mapper(componentModel = "spring")
public interface ExampleTransfer {

    @IterableMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    List<LogModel> tSysLogEntity2LoggerModelListTransfer(List<TSysLogEntity> list);

    @Mappings({
            @Mapping(target = "ask", dateFormat = "yyyy-MM-dd HH:mm:ss"),
    })
    LogModel tSysLogEntity2LoggerModelTransfer(TSysLogEntity tSysLogEntity);
}