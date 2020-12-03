package com.trista.service;

import com.trista.mapper.CourseMapper;
import com.trista.pojo.Course;
import com.trista.pojo.PagingVO;

import java.util.List;
import java.util.Map;

public class CourseServiceImpl implements CourseService {

    private CourseMapper courseMapper;

    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    public int deleteCourseById(int courseID) {
        return courseMapper.deleteCourseById(courseID);
    }

    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    public List<Course> queryCourseByName(String courseName) {
        return courseMapper.queryCourseByName(courseName);
    }

    public Course queryCourseById(int courseID) {
        return courseMapper.queryCourseById(courseID);
    }

    public List<Course> queryCourseByTeacherID(int teacherID) {
        return courseMapper.queryCourseByTeacherID(teacherID);
    }

    public List<Course> queryAllCourses() {
        return courseMapper.queryAllCourses();
    }

    public List<Course> queryCourseByLimit(PagingVO pagingVO) {
        return courseMapper.queryCourseByLimit(pagingVO);
    }
}
