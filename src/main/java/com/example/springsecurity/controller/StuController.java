package com.example.springsecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springsecurity.enitiy.CurrPage;
import com.example.springsecurity.enitiy.ResponseResult;
import com.example.springsecurity.enitiy.Stu_Table;
import com.example.springsecurity.enitiy.UserList;
import com.example.springsecurity.mapper.StuMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StuController {
    @Autowired
    private StuMapper stuMapper;
    @RequestMapping("/student/table")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Stu_Table>> select(@RequestBody CurrPage currPage){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state","0");
        Page<Stu_Table> page = stuMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()), queryWrapper);
        System.out.println(page.getTotal());
        List<Stu_Table> list = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<Stu_Table>> responseResult = new ResponseResult<>(lo.intValue(),"stu_table数据获取成功",list);
        return responseResult;
    }

    @RequestMapping("/student/table_false")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Stu_Table>> select_f(@RequestBody CurrPage currPage){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("state","1");
        Page<Stu_Table> page = stuMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()), queryWrapper);
        System.out.println(page.getTotal());
        List<Stu_Table> list = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<Stu_Table>> responseResult = new ResponseResult<>(lo.intValue(),"stu_table_false数据获取成功",list);
        return responseResult;
    }

    @RequestMapping("/student/table_select")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Stu_Table>> table_select(@RequestBody Stu_Table stuTable){
        QueryWrapper queryWrapper = new QueryWrapper();
//        LambdaQueryWrapper<Stu_Table> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        System.out.println(name);
//        String name = "";
        System.out.println(stuTable);
        queryWrapper.like(StringUtils.isNotBlank(stuTable.getName()),"name",stuTable.getName());
        queryWrapper.eq(StringUtils.isNotBlank(stuTable.getSchoolName()),"school_name",stuTable.getSchoolName());
        queryWrapper.eq(StringUtils.isNotBlank(stuTable.getSex()),"sex",stuTable.getSex());
        queryWrapper.eq(StringUtils.isNotBlank(stuTable.getAddyear()),"addyear",stuTable.getAddyear());
        queryWrapper.eq(StringUtils.isNotBlank(stuTable.getTime()),"time",stuTable.getTime());
        List<Stu_Table> list = stuMapper.selectList(queryWrapper);
        ResponseResult<List<Stu_Table>> responseResult = new ResponseResult<>(200,"stu_table数据获取成功",list);
        return responseResult;
    }

    @RequestMapping("/student/table_del")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public String deleteIds(@RequestBody UserList userList){
        List<Long> ids = userList.getIds();
        if (!ids.isEmpty()&&ids.size()==0) {
            return "没有数据";
        }else{
            QueryWrapper<Stu_Table> wrapper = new QueryWrapper<>();
            wrapper.in("id",ids);
            stuMapper.delete(wrapper);
        }
        return "删除成功";
    }
    @RequestMapping("/student/table_update")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public String UpdateIds(@RequestBody String id){
        System.out.println(id);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",id);
        UpdateWrapper<Stu_Table> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        Stu_Table stu_table = stuMapper.selectOne(queryWrapper);
        stu_table.setState(stu_table.getState().equals("1") ? "0" : "1");
        int update = stuMapper.update(stu_table, updateWrapper);
        if (update>0){
            return "修改成功";
        }
        return "修改失败";
    }

    @RequestMapping("/student/table_insert")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public String UpdateIds(@RequestBody List<Stu_Table> list){
        list.forEach(item -> stuMapper.insert(item));
        return "添加成功";
    }
}
