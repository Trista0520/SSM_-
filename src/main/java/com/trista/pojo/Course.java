package com.trista.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int courseID;
    private String courseName;
    private int teacherID;
    private String courseTime;
    private String classRoom;
    private String courseWeek;
    private String courseType;
    private int collegeID;
    private int score;
    private String collegeName;
}
