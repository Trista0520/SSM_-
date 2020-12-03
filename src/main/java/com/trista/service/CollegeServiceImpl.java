package com.trista.service;

import com.trista.mapper.CollegeMapper;
import com.trista.pojo.College;

import java.util.List;

public class CollegeServiceImpl implements CollegeService {


    private CollegeMapper collegeMapper;

    public void setCollegeMapper(CollegeMapper collegeMapper){
        this.collegeMapper = collegeMapper;
    }
    public int addCollege(College college) {
        return collegeMapper.addCollege(college);
    }

    public int deleteCollegeById(int collegeID) {
        return collegeMapper.deleteCollegeById(collegeID);
    }

    public int updateCollege(College college) {
        return collegeMapper.updateCollege(college);
    }

    public College queryCollegeById(int collegeID) {
        return collegeMapper.queryCollegeById(collegeID);
    }

    public List<College> queryAllColleges() {
        return collegeMapper.queryAllColleges();
    }
}
