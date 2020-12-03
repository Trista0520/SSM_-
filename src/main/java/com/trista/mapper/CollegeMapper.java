package com.trista.mapper;

import com.trista.pojo.College;

import java.util.List;

public interface CollegeMapper {

    //增加学院信息
    int addCollege(College college);

    //根据id删除学院
    int deleteCollegeById(int collegeID);

    //更新学院
    int updateCollege(College college);

    //根据id查询
    College queryCollegeById(int collegeID);

    //查询所有学院，返回list集合
    List<College> queryAllColleges();

}
