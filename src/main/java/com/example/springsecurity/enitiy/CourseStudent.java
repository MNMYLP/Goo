package com.example.springsecurity.enitiy;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "course_student")
public class CourseStudent {
    private String id;
    @TableField("term_name")
    private String termName;
    @TableField("course_name")
    private String courseName;
    @TableField("instructor_name")
    private String instructorName;
    @TableField("class_time")
    private String classTime;
    @TableField("enrollment_year")
    private String enrollmentYear;
    @TableField("faculty_name")
    private String facultyName;
    @TableField("student_id")
    private String studentId;
    @TableField("student_name")
    private String studentName;
    private String gender;
    @TableField("registration_time")
    private String registrationTime;
}
