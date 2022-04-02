package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.ExceptionHandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-23
 */
@Api(description="讲师管理")
@RestController
@CrossOrigin //跨域
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //数据访问链接：http://localhost:8001/eduservice/teacher/findAll

    //1.把service注入
    @Autowired
    private EduTeacherService eduTeacherService;

    //2.查询讲师表的所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    //3.逻辑删除讲师表中的数据
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable String id) {
        eduTeacherService.removeById(id);
        return R.ok();
    }

    //4.分页查询功能
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @ApiParam(name = "current", value = "当前页码数", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit) {
        //创建page对象
        Page<EduTeacher> page = new Page<>(current, limit);

        //调用方法实现分页
        //调用方法时候, 底层封装, 把分页数据封装到page对象里面
        eduTeacherService.page(page, null);

        long total = page.getTotal(); //总记录数
        List<EduTeacher> records = page.getRecords(); //数据list集合
        return R.ok().data("total", total).data("row", records);
    }

    //5.带条件的分页查询功能
    @ApiOperation(value = "分页讲师列表")
    @PostMapping("pageQueryTeacher/{current}/{limit}")
    public R pageQueryListTeacher(
            @ApiParam(name = "current", value = "当前页码数", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> page = new Page<>(current, limit);

        //调用方法实现分页
        //调用方法时候, 底层封装, 把分页数据封装到page对象里面
        //排序

        eduTeacherService.pageQuery(page, teacherQuery);

        long total = page.getTotal(); //总记录数
        List<EduTeacher> records = page.getRecords(); //数据list集合
        return R.ok().data("total", total).data("row", records);

    }

    //6.增加操作
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher ) {
        eduTeacherService.save(eduTeacher);
        return R.ok();
    }

    //7.根据id查询数据
    @ApiOperation(value = "查询讲师数据")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
//        try {
//            int a = 10/0;
//        } catch (Exception e) {
//            throw new GuliException(20001, "出现自定义异常");
//        }
        EduTeacher byId = eduTeacherService.getById(id);
        return R.ok().data("teacher", byId);
    }

    //8.修改数据
    @ApiOperation(value = "根据id修改讲师数据")
    @PostMapping("updateTeacher")
    public R updateById(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

