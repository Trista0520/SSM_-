package com.trista.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {

    private int userID;
    private String userName;
    private String password;
    private int role;
}
