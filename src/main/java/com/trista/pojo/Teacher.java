package com.trista.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    private int userID;
    private String userName;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthYear;
    private String degree;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date grade;
    private int collegeID;
    private String collegeName;
}
