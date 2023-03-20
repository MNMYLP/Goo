package com.example.springsecurity.enitiy;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserList{
     private List<Long> ids;
}