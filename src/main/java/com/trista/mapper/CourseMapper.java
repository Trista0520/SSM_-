package com.trista.mapper;

import com.trista.pojo.College;
import com.trista.pojo.Course;
import com.trista.pojo.PagingVO;

import java.util.List;
import java.util.Map;

public interface CourseMapper {
    //增加课程
    int addCourse(Course course);

    //根据id删除课程
    int deleteCourseById(int courseID);

    //修改课程
    int updateCourse(Course course);

    //根据名称查询课程
    List<Course> queryCourseByName(String courseName);

    //根据ID查询课程
    Course queryCourseById(int courseID);

    //查询全部课程
    List<Course> queryAllCourses();

    //根据老师id查询
    List<Course> queryCourseByTeacherID(int teacherID);

    //分页查询课程
    List<Course> queryCourseByLimit(PagingVO pagingVO);
}
