package com.example.springsecurity.enitiy;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stu_table")
public class Stu_Table {
    private String id;
    @TableField("school_name")
    private String schoolName;
    private String department;
    private String addyear;
    @TableField("stu_number")
    private String stuNumber;
    private String name;
    private String sex;
    private String phone;
    private String state;
    private String stuclass;
    private String time;
}
