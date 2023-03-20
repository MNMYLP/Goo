package com.example.springsecurity.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springsecurity.enitiy.*;
import com.example.springsecurity.mapper.TeacherMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherMapper teacherMapper;
    @RequestMapping("/teacher/table")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<TeacherTable>> select(@RequestBody CurrPage currPage){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state","0");
        Page<TeacherTable> page = teacherMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()), queryWrapper);
        System.out.println(page.getTotal());
        List<TeacherTable> list = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<TeacherTable>> responseResult = new ResponseResult<>(lo.intValue(),"TeacherTable数据获取成功",list);
        return responseResult;
    }

    @RequestMapping("/teacher/table_false")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<TeacherTable>> select_f(@RequestBody CurrPage currPage){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state","1");
        Page<TeacherTable> page = teacherMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()), queryWrapper);
        System.out.println(page.getTotal());
        List<TeacherTable> list = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<TeacherTable>> responseResult = new ResponseResult<>(lo.intValue(),"TeacherTable_false数据获取成功",list);
        return responseResult;
    }

    @RequestMapping("/teacher/table_select")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<TeacherTable>> table_select(@RequestBody TeacherTable stuTable){
        QueryWrapper queryWrapper = new QueryWrapper();
//        LambdaQueryWrapper<TeacherTable> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        System.out.println(name);
//        String name = "";
        System.out.println(stuTable);
        queryWrapper.like(StringUtils.isNotBlank(stuTable.getName()),"name",stuTable.getName());
        queryWrapper.eq(StringUtils.isNotBlank(stuTable.getSchoolName()),"school_name",stuTable.getSchoolName());
        queryWrapper.eq(StringUtils.isNotBlank(stuTable.getSex()),"sex",stuTable.getSex());
        queryWrapper.eq(StringUtils.isNotBlank(stuTable.getAddyear()),"addyear",stuTable.getAddyear());
        queryWrapper.eq(StringUtils.isNotBlank(stuTable.getTime()),"time",stuTable.getTime());
        List<TeacherTable> list = teacherMapper.selectList(queryWrapper);
        ResponseResult<List<TeacherTable>> responseResult = new ResponseResult<>(200,"TeacherTable数据获取成功",list);
        return responseResult;
    }

    @RequestMapping("/teacher/table_del")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public String deleteIds(@RequestBody UserList userList){
        List<Long> ids = userList.getIds();
        if (!ids.isEmpty()&&ids.size()==0) {
            return "没有数据";
        }else{
            QueryWrapper<TeacherTable> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            teacherMapper.delete(wrapper);
        }
        return "删除成功";
    }
    @RequestMapping("/teacher/table_update")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public String UpdateIds(@RequestBody String id){
        System.out.println(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        UpdateWrapper<TeacherTable> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        TeacherTable TeacherTable = teacherMapper.selectOne(queryWrapper);
        TeacherTable.setState(TeacherTable.getState().equals("1") ? "0" : "1");
        int update = teacherMapper.update(TeacherTable, updateWrapper);
        if (update>0){
            return "修改成功";
        }
        return "修改失败";
    }

    @RequestMapping("/teacher/table_insert")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public String UpdateIds(@RequestBody List<TeacherTable> list){
        list.forEach(item -> teacherMapper.insert(item));
        return "添加成功";
    }
}
