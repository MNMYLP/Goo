package com.example.springsecurity.enitiy;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "course_sheet")
public class CourseSheet {
    private String id;
    private String course;
    @TableField("mon_number_classes")
    private String MonNumberClasses;
    @TableField("mon_people_class")
    private String MonpeopleClass;
    @TableField("tues_number_classes")
    private String TuesNumberClasses;
    @TableField("tues_people_class")
    private String TuespeopleClass;
    @TableField("wed_number_classes")
    private String WedNumberClasses;
    @TableField("wed_people_class")
    private String WedpeopleClass;
    @TableField("thur_number_classes")
    private String ThurNumberClasses;
    @TableField("thur_people_class")
    private String ThurpeopleClass;
    @TableField("fri_number_classes")
    private String FriNumberClasses;
    @TableField("fri_people_class")
    private String FripeopleClass;
    @TableField("sat_number_classes")
    private String SatNumberClasses;
    @TableField("sat_people_class")
    private String SatpeopleClass;
    @TableField("sun_number_classes")
    private String SunNumberClasses;
    @TableField("sun_people_class")
    private String SunpeopleClass;
}
