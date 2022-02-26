package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TeacherQuery
 * @Description TODO
 * @Author 24732
 * @Date 2022/2/26 16:43
 * @Return
 **/
@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
@Data
public class TeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2特级讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2022-02-26 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2022-02-26 10:10:10")
    private String end;
}
