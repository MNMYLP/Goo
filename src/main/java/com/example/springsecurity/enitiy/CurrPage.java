package com.example.springsecurity.enitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrPage {
    private Long curr;
    private Long size;
}
