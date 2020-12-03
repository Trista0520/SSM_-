package com.trista.mapper;

import com.trista.pojo.Course;
import com.trista.pojo.PagingVO;
import com.trista.pojo.Student;

import java.util.List;

public interface StudentMapper {
    //增加学生
    int addStudent(Student student);

    //根据id删除学生
    int deleteStudentById(int userID);

    //修改学生信息
    int updateStudent(Student student);

    //根据名称查询学生
    List<Student> queryStudentByName(String userName);

    //根据id查询学生
    Student queryStudentById(int userID);

    //查询全部学生
    List<Student> queryAllStudents();

    //分页查询
    List<Student> queryStudentByLimit(PagingVO pagingVO);
}
