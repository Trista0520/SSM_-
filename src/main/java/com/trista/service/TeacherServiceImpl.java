package com.trista.service;

import com.trista.mapper.TeacherMapper;
import com.trista.pojo.PagingVO;
import com.trista.pojo.Teacher;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private TeacherMapper teacherMapper;

    public void setTeacherMapper(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    public int deleteTeacherById(int userID) {
        return teacherMapper.deleteTeacherById(userID);
    }

    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    public List<Teacher> queryTeacherByName(String userName) {
        return teacherMapper.queryTeacherByName(userName);
    }

    public Teacher queryTeacherById(int userID) {
        return teacherMapper.queryTeacherById(userID);
    }

    public List<Teacher> queryAllTeachers() {
        return teacherMapper.queryAllTeachers();
    }

    public List<Teacher> queryTeacherByLimit(PagingVO pagingVO) {
        return teacherMapper.queryTeacherByLimit(pagingVO);
    }
}
