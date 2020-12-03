package com.trista.mapper;
import com.trista.pojo.Course;
import com.trista.pojo.PagingVO;
import com.trista.pojo.SelectedCourse;

import java.util.List;
import java.util.Map;

public interface SelectedMapper {

    //增加选择的课程
    int addSelectedCourse(SelectedCourse selectedCourse);

    //根据id删除课程
    int deleteSelectedCourseById(int courseID);

    //修改已选课程
    int updateSelectedCourse(SelectedCourse selectedCourse);

    //根据id查询选课学生
    List<SelectedCourse> querySelectedCourseById(int courseID);

    //根绝课程id和选课学生id查询
    SelectedCourse querySelectedCourse(Map<String,Object> map);

    //根剧学生id查询课程
    List<SelectedCourse> querySelectedCourseByStuId(int studentID);

    //分页显示选课学生
    List<SelectedCourse> querySelectedCourseByLimit(Map<String,Object> map);

    //查询全部已选课程
    List<SelectedCourse> queryAllSelectedCourses();
}
