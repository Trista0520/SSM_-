package com.trista.service;

import com.trista.mapper.SelectedMapper;
import com.trista.pojo.PagingVO;
import com.trista.pojo.SelectedCourse;

import java.util.List;
import java.util.Map;

public class SelectedServiceImpl implements SelectedService {

    private SelectedMapper selectedMapper;

    public void setSelectedMapper(SelectedMapper selectedMapper) {
        this.selectedMapper = selectedMapper;
    }

    public int addSelectedCourse(SelectedCourse selectedCourse) {
        return selectedMapper.addSelectedCourse(selectedCourse);
    }

    public int deleteSelectedCourseById(int courseID) {
        return selectedMapper.deleteSelectedCourseById(courseID);
    }

    public int updateSelectedCourse(SelectedCourse selectedCourse) {
        return selectedMapper.updateSelectedCourse(selectedCourse);
    }

    public List<SelectedCourse> querySelectedCourseById(int courseID) {
        return selectedMapper.querySelectedCourseById(courseID);
    }

    public SelectedCourse querySelectedCourse(Map<String, Object> map) {
        return selectedMapper.querySelectedCourse(map);
    }

    public List<SelectedCourse> querySelectedCourseByLimit(Map<String, Object> map) {
        return selectedMapper.querySelectedCourseByLimit(map);
    }

    public List<SelectedCourse> querySelectedCourseByStuId(int studentID) {
        return selectedMapper.querySelectedCourseByStuId(studentID);
    }

    public List<SelectedCourse> queryAllSelectedCourses() {
        return selectedMapper.queryAllSelectedCourses();
    }
}
