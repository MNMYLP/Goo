package com.example.springsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springsecurity.enitiy.*;
import com.example.springsecurity.mapper.CourseMapper;
import com.example.springsecurity.mapper.CourseSheetMapper;
import com.example.springsecurity.mapper.CourseStudentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseStudentMapper courseStudentMapper;
    @Autowired
    private CourseSheetMapper courseSheetMapper;
    //courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper
    @RequestMapping("/course/table")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Course>> table(@RequestBody CurrPage currPage){
        Page<Course> page = courseMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()),null);
        System.out.println(page.getTotal());
        List<Course> records = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<Course>> responseResult = new ResponseResult<>(lo.intValue(),"stu_table_false数据获取成功",records);
        return responseResult;
    }
    @RequestMapping("/course/select")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Course>> select(){
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("semester", "teacher");
        List<Course> courses = courseMapper.selectList(queryWrapper);
        ResponseResult<List<Course>> responseResult = new ResponseResult<>(200,"Course_select数据获取成功",courses);
        return responseResult;
    }

    @RequestMapping("/course/select_one")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Course>> One_select(@RequestBody Course course){
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(course.getSemester()),"semester",course.getSemester());
        queryWrapper.eq(StringUtils.isNotBlank(course.getTeacher()),"teacher",course.getTeacher());
        queryWrapper.eq(StringUtils.isNotBlank(course.getCourseStatus()),"course_status",course.getCourseStatus());
        queryWrapper.like(StringUtils.isNotBlank(course.getCourseName()),"course_name",course.getCourseName());
        List<Course> courses = courseMapper.selectList(queryWrapper);
        ResponseResult<List<Course>> responseResult = new ResponseResult<>(200,"Course_select数据获取成功",courses);
        return responseResult;
    }

    @RequestMapping("/course/select_add")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Course>> add_select(@RequestBody Course course){
        int insert = courseMapper.insert(course);
        if (insert>0){
            return new ResponseResult<>(200,"添加成功");
        }
        return new ResponseResult<>(200,"添加失败");
    }

    @RequestMapping("/course/select_delete")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Course>> delete_select(@RequestBody String id){
        int insert = courseMapper.deleteById(id);
        if (insert>0){
            return new ResponseResult<>(200,"删除成功");
        }
        return new ResponseResult<>(200,"删除失败");
    }
    //courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper//courseMapper

    //courseStudentMapper//courseStudentMapper//courseStudentMapper//courseStudentMapper//courseStudentMapper//courseStudentMapper//courseStudentMapper//courseStudentMapper
    @RequestMapping("/course/student_table")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<CourseStudent>> student_table(@RequestBody CurrPage currPage){
        Page<CourseStudent> page = courseStudentMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()),null);
        System.out.println(page.getTotal());
        List<CourseStudent> records = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<CourseStudent>> responseResult = new ResponseResult<>(lo.intValue(),"CourseStudent数据获取成功",records);
        return responseResult;
    }
    //courseSheetMapper//courseSheetMapper//courseSheetMapper//courseSheetMapper//courseSheetMapper//courseSheetMapper//courseSheetMapper//courseSheetMapper//courseSheetMapper
    @RequestMapping("/course/sheet_table")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<CourseSheet>> sheet_table(@RequestBody CurrPage currPage){
        Page<CourseSheet> page = courseSheetMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()),null);
        System.out.println(page.getTotal());
        List<CourseSheet> records = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<CourseSheet>> responseResult = new ResponseResult<>(lo.intValue(),"sheet_table数据获取成功",records);
        return responseResult;
    }
}