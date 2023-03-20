package com.example.springsecurity.enitiy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "dashboard_select")
public class Dashboard_Select {
    private String id;
    private String selectName;
    private String selectValue;
}
