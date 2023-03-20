package com.example.springsecurity.enitiy;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "run_table")
public class RunTable {
    private String id;
    @TableField("department_name")
    private String departmentName;
    @TableField("enrollment_year")
    private String enrollmentYear;
    @TableField("term_name")
    private String termName;
    @TableField("student_id")
    private String studentId;
    @TableField("student_name")
    private String studentName;
    private String gender;
    @TableField("valid_times")
    private String validTimes;
    @TableField("valid_mileage")
    private String validMileage;
}
