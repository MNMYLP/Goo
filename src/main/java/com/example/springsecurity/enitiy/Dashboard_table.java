package com.example.springsecurity.enitiy;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "dashboard_table")
public class Dashboard_table {
    private String id;
    private String data1;
    private Integer sales1;
    private Integer sales2;
    private Integer sales3;
    private Integer sales11;
    private Integer sales22;
    private Integer sales33;
    private Integer sales111;
    private Integer sales222;
    private Integer sales333;
    private Integer sales1111;
    private Integer sales2222;
    private Integer sales3333;
    private Integer sales11111;
    private Integer sales22222;
    private Integer sales33333;
}
