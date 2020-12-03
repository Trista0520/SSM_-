package com.trista.mapper;

import com.trista.pojo.PagingVO;
import com.trista.pojo.Student;
import com.trista.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {
    //增加老师
    int addTeacher(Teacher teacher);

    //根据id删除老师
    int deleteTeacherById(int userID);

    //修改老师信息
    int updateTeacher(Teacher teacher);

    //根据名称查询老师
   List<Teacher> queryTeacherByName(String userName);

    //根据id查询老师
    Teacher queryTeacherById(int userID);

    //查询全部老师
    List<Teacher> queryAllTeachers();

    //分页查询
    List<Teacher> queryTeacherByLimit(PagingVO pagingVO);
}
