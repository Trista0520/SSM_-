package com.trista.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectedCourse {

    private int courseID;
    private int studentID;
    private String studentName;
    private Integer mark;
    private boolean over;
}
