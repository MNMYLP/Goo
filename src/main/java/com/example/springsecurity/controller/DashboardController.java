package com.example.springsecurity.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springsecurity.enitiy.*;
import com.example.springsecurity.mapper.Dashboard_SelectMapper;
import com.example.springsecurity.mapper.Dashboard_TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardController {
    //下拉框mapper
    @Autowired
    private Dashboard_SelectMapper dashboard_selectMapper;
    //表格mapper
    @Autowired
    private Dashboard_TableMapper dashboard_tableMapper;
    @RequestMapping("/dashboard/select")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Dashboard_Select>>  select(){
//        List<Dashboard_Select> dashboard_selects = dashboard_selectMapper.selectList(null);
//        dashboard_selects.forEach(System.out::println);
//        System.out.println(dashboard_selects);
        ResponseResult<List<Dashboard_Select>> responseResult = new ResponseResult<>(200,"dashboard_select数据获取成功",dashboard_selectMapper.selectList(null));
        return responseResult;
    }
    @RequestMapping("/dashboard/table")
    @PreAuthorize("hasAnyAuthority('admin','test')")
    public ResponseResult<List<Dashboard_table>>  table(@RequestBody CurrPage currPage){
        Page<Dashboard_table> page = dashboard_tableMapper.selectPage(new Page(currPage.getCurr(),currPage.getSize()),null);
        System.out.println(page.getTotal());
        List<Dashboard_table> records = page.getRecords();
        Long lo = page.getTotal();
        ResponseResult<List<Dashboard_table>> responseResult = new ResponseResult<>(lo.intValue(),"stu_table_false数据获取成功",records);
        return responseResult;
    }
}
