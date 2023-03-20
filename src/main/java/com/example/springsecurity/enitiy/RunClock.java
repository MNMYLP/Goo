package com.example.springsecurity.enitiy;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "run_clock")
public class RunClock {
    private String id;
    @TableField("department_name")
    private String departmentName;
    @TableField("enrollment_year")
    private String enrollmentYear;
    @TableField("student_id")
    private String studentId;
    @TableField("student_name")
    private String studentName;
    private String gender;
    private String state;
    private String miles;
    @TableField("punch_number")
    private String punchNumber;
    @TableField("average_pace")
    private String averagePace;
    @TableField("start_time")
    private String startTime;
    @TableField("end_time")
    private String endTime;
    @TableField("total_validity")
    private String totalValidity;
    @TableField("total_miles")
    private String totalMiles;
    @TableField("total_number")
    private String totalNumber;
    @TableField("total_capital")
    private String totalCapital;
    @TableField("total_miles_capital")
    private String totalMilesCapital;
}
