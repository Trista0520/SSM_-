package com.trista.service;

import com.trista.mapper.StudentMapper;
import com.trista.pojo.Course;
import com.trista.pojo.PagingVO;
import com.trista.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class StudentServiceImpl implements StudentService {


    private StudentMapper studentMapper;

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }


    public int addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    public int deleteStudentById(int userID) {
        return studentMapper.deleteStudentById(userID);
    }

    public int updateStudent(Student student) {
        return studentMapper.updateStudent(student);
    }

    public  List<Student> queryStudentByName(String userName) {
        return studentMapper.queryStudentByName(userName);
    }

    public Student queryStudentById(int userID) {
        return studentMapper.queryStudentById(userID);
    }

    public List<Student> queryAllStudents() {
        return studentMapper.queryAllStudents();
    }

    public List<Student> queryStudentByLimit(PagingVO pagingVO) {
       return studentMapper.queryStudentByLimit(pagingVO);
    }

}
