package com.example.springsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springsecurity.enitiy.*;
import com.example.springsecurity.mapper.RunClockMapper;
import com.example.springsecurity.mapper.RunTableMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RunController {
    @Autowired
    private RunTableMapper runTableMapper;
    @Autowired
    private RunClockMapper runClockMapper;
    //渲染列表
    @RequestMapping("/run/run_table")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<RunTable>> student_table(@RequestBody CurrPage currPage){
        Page<RunTable> page = runTableMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()),null);
        System.out.println(page.getTotal());
        List<RunTable> records = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<RunTable>> responseResult = new ResponseResult<>(lo.intValue(),"CourseStudent数据获取成功",records);
        return responseResult;
    }
    //搜索
    @RequestMapping("/run/run_table_search")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<RunTable>> run_table_search(@RequestBody RunTable runTable){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotBlank(runTable.getDepartmentName()),"department_name",runTable.getDepartmentName());
        queryWrapper.eq(StringUtils.isNotBlank(runTable.getEnrollmentYear()),"enrollment_year",runTable.getEnrollmentYear());
        queryWrapper.eq(StringUtils.isNotBlank(runTable.getTermName()),"term_name",runTable.getTermName());
        queryWrapper.eq(StringUtils.isNotBlank(runTable.getStudentName()),"student_name",runTable.getStudentName());
        queryWrapper.eq(StringUtils.isNotBlank(runTable.getGender()),"gender",runTable.getGender());
        List<RunTable> list = runTableMapper.selectList(queryWrapper);
        ResponseResult<List<RunTable>> responseResult = new ResponseResult<>(200,"CourseStudent数据获取成功",list);
        return responseResult;
    }

    //删除
    @RequestMapping("/run/run_table_delete")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<RunTable>> run_table_delete(@RequestBody String id){
        int i = runTableMapper.deleteById(id);
        if (i > 0){
            return new ResponseResult<>(200,"数据删除成功");
        }
        return new ResponseResult<>(200,"数据删除失败");
    }

    //删除
    @RequestMapping("/run/run_clock")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<RunClock>> run_clock(@RequestBody CurrPage currPage){
        Page<RunClock> page = runClockMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()),null);
        System.out.println(page.getTotal());
        List<RunClock> records = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<RunClock>> responseResult = new ResponseResult<>(lo.intValue(),"CourseStudent数据获取成功",records);
        return responseResult;
    }

    //搜索
    @RequestMapping("/run/run_clock_search")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<RunClock>> run_clock_search(@RequestBody RunClock runClock){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotBlank(runClock.getDepartmentName()),"department_name",runClock.getDepartmentName());
        queryWrapper.eq(StringUtils.isNotBlank(runClock.getEnrollmentYear()),"enrollment_year",runClock.getEnrollmentYear());
        queryWrapper.eq(StringUtils.isNotBlank(runClock.getStudentName()),"student_name",runClock.getStudentName());
        queryWrapper.eq(StringUtils.isNotBlank(runClock.getGender()),"gender",runClock.getGender());
        List<RunClock> list = runClockMapper.selectList(queryWrapper);
        ResponseResult<List<RunClock>> responseResult = new ResponseResult<>(200,"数据获取成功",list);
        return responseResult;
    }

    //删除
    @RequestMapping("/run/run_clock_delete")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<RunClock>> run_clock_delete(@RequestBody String id){
        int i = runClockMapper.deleteById(id);
        if (i > 0){
            return new ResponseResult<>(200,"数据删除成功");
        }
        return new ResponseResult<>(200,"数据删除失败");
    }














}
