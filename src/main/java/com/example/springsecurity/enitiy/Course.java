package com.example.springsecurity.enitiy;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "course_registration")
public class Course {
    private String id;
    @TableField("semester")
    private String semester;
    @TableField("course_name")
    private String courseName;
    private String teacher;
    @TableField("class_time")
    private String classTime;
    private String admin;
    @TableField("registration_time")
    private String registrationTime;
    private String participants;
    @TableField("course_capacity")
    private String courseCapacity;
    @TableField("registration_num")
    private String registrationNum;
    @TableField("course_status")
    private String courseStatus;
    @TableField("created_time")
    private String createdTime;

}
