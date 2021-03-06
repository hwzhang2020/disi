package com.hywx.userservice.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @program: gw-cloud
 * @description:
 * @author: tangjing
 * @create: 2020-03-12 17:34
 **/
@Data
public class OrganizationSearchVo {
    /**
     * 单位名称
     */
    private String orgName;


    //时间
    //开始时间
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    //结束时间
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String stopTime;
}
