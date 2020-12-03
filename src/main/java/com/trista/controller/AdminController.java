package com.trista.controller;


import com.trista.exception.CustomException;
import com.trista.pojo.*;
import com.trista.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier("StudentServiceImpl")
    private StudentServiceImpl studentService;

    @Autowired
    @Qualifier("CollegeServiceImpl")
    private CollegeServiceImpl collegeService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private SelectedServiceImpl selectedService;

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private UserLoginServiceImpl userLoginService;

    //--------------------------------------学生操作------------------------------------------------//
    //分页显示所有学生
    @RequestMapping("/showStudent")
    public String showStudent(Model model, Integer page){

        List<Student> studentList = null;
        //页码对象
        PagingVO pagingVO = new PagingVO();
        //设置总页数
        pagingVO.setTotalCount(studentService.queryAllStudents().size());
        if (page == null || page == 0) {
            pagingVO.setToPageNo(1);
            studentList = studentService.queryStudentByLimit(pagingVO);
        } else {
            pagingVO.setToPageNo(page);
            studentList = studentService.queryStudentByLimit(pagingVO);
        }
        for (Student student : studentList) {
            College college = collegeService.queryCollegeById(student.getCollegeID());
            student.setCollegeName(college.getCollegeName());
        }
        model.addAttribute("studentList",studentList);
        model.addAttribute("pagingVO",pagingVO);
        return "admin/showStudent";
    }

    //根据名字搜索学生
    @RequestMapping(value = "/selectStudent",method = {RequestMethod.POST})
    public String selectStudent(String findByName, Model model){
        List<Student> studentList = null;
        studentList = studentService.queryStudentByName(findByName);
        for (Student student : studentList) {
            College college = collegeService.queryCollegeById(student.getCollegeID());
            student.setCollegeName(college.getCollegeName());
        }
        model.addAttribute("studentList",studentList);
        return "admin/showStudent";
    }

    //跳转到添加学生信息页面
    @RequestMapping(value = "/addStudent", method = {RequestMethod.GET})
    public String addStudentUI(Model model){
        List<College> collegeList = collegeService.queryAllColleges();
        model.addAttribute("collegeList", collegeList);
        return "admin/addStudent";
    }

    //添加学生信息操作
    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST})
    public String addStudent(Student student){
        studentService.addStudent(student);
        return "redirect:/admin/showStudent";
    }

    //跳转到修改学生信息页面
    @RequestMapping(value = "/editStudent",method = {RequestMethod.GET})
    public String updateStudentUI(Model model,Integer id){
        List<College> collegeList = collegeService.queryAllColleges();
        Student student = studentService.queryStudentById(id);
        model.addAttribute("collegeList", collegeList);
        model.addAttribute("student",student);
        return "admin/editStudent";
    }

    //修改学生信息
    @RequestMapping(value = "/editStudent",method = {RequestMethod.POST})
    public String updateStudent(Student student){
        studentService.updateStudent(student);
        return "redirect:/admin/showStudent";
    }

    //删除学生
    @RequestMapping(value = "/removeStudent",method = {RequestMethod.GET})
    public String deleteStudent(Integer id){
        studentService.deleteStudentById(id);
        return  "redirect:/admin/showStudent";
    }


    //--------------------------------------教师操作------------------------------------------------//
    //展示教师信息
    @RequestMapping("/showTeacher")
    public String showTeacher(Model model,Integer page){
        List<Teacher> teacherList = null;
        PagingVO pagingVO = new PagingVO();
        pagingVO.setTotalCount(teacherService.queryAllTeachers().size());
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            teacherList = teacherService.queryTeacherByLimit(pagingVO);
        }else {
            pagingVO.setToPageNo(page);
            teacherList = teacherService.queryTeacherByLimit(pagingVO);
        }
        for (Teacher teacher : teacherList) {
            teacher.setCollegeName(collegeService.queryCollegeById(teacher.getCollegeID()).getCollegeName());
        }
        model.addAttribute("teacherList",teacherList);
        model.addAttribute("pagingVO",pagingVO);
        return "admin/showTeacher";
    }

    //跳转到添加教师的界面
    @RequestMapping(value = "/addTeacher",method = {RequestMethod.GET})
    public String addTeacherUI(Model model){
        List<College> collegeList = collegeService.queryAllColleges();
        model.addAttribute("collegeList",collegeList);
        return "admin/addTeacher";
    }

    //添加教师
    @RequestMapping(value = "/addTeacher",method = {RequestMethod.POST})
    public String addTeacher(Teacher teacher){
        teacherService.addTeacher(teacher);
        return "redirect:/admin/showTeacher";
    }

    //根据名字搜索教师
    @RequestMapping(value = "/selectTeacher", method = {RequestMethod.POST})
    public String selectTeacher(Model model, String findByName){
        List<Teacher> teacherList = null;
        teacherList = teacherService.queryTeacherByName(findByName);
        for (Teacher teacher : teacherList) {
            teacher.setCollegeName(collegeService.queryCollegeById(teacher.getCollegeID()).getCollegeName());
        }
        model.addAttribute("teacherList",teacherList);
        return "admin/showTeacher";
    }

    //跳转到修改教师信息界面
    @RequestMapping(value = "/editTeacher",method = {RequestMethod.GET})
    public String editTeacherUI(Model model, Integer id){
        List<College> collegeList = collegeService.queryAllColleges();
        Teacher teacher = teacherService.queryTeacherById(id);
        model.addAttribute("collegeList",collegeList);
        model.addAttribute("teacher",teacher);
        return "admin/editTeacher";
    }

    //修改教师信息
    @RequestMapping(value = "/editTeacher",method = {RequestMethod.POST})
    public String editTeacher(Teacher teacher){
        teacherService.updateTeacher(teacher);
        return "redirect:/admin/showTeacher";
    }

    //删除教师
    @RequestMapping(value = "/removeTeacher")
    public String removeTeacher(Integer id){
        teacherService.deleteTeacherById(id);
        return "redirect:/admin/showTeacher";
    }

    //--------------------------------------课程操作------------------------------------------------//
    //分页展示课程信息
    @RequestMapping("/showCourse")
    public String showCourse(Model model,Integer page){
        List<Course> courseList = null;
        PagingVO pagingVO = new PagingVO();
        pagingVO.setTotalCount(courseService.queryAllCourses().size());
        if (page == null || page == 0){
            pagingVO.setToPageNo(1);
            courseList = courseService.queryCourseByLimit(pagingVO);
        }else {
            pagingVO.setToPageNo(page);
            courseList = courseService.queryCourseByLimit(pagingVO);
        }
        model.addAttribute("courseList",courseList);
        model.addAttribute("pagingVO",pagingVO);
        return "admin/showCourse";
    }

    //跳转到增加课程页面
    @RequestMapping(value = "/addCourse",method = {RequestMethod.GET})
    public String addCourseUI(Model model){
        List<Teacher> teacherList = teacherService.queryAllTeachers();
        List<College> collegeList = collegeService.queryAllColleges();
        model.addAttribute("teacherList",teacherList);
        model.addAttribute("collegeList",collegeList);
        return "admin/addCourse";
    }

    //添加课程
    @RequestMapping(value = "/addCourse",method = {RequestMethod.POST})
    public String addCourse(Course course){
        courseService.addCourse(course);
        return "redirect:/admin/showCourse";
    }

    //跳转到修改课程界面
    @RequestMapping(value = "/editCourse",method = {RequestMethod.GET})
    public String editCourseUI(Model model,Integer id){
        List<Teacher> teacherList = teacherService.queryAllTeachers();
        List<College> collegeList = collegeService.queryAllColleges();
        Course course = courseService.queryCourseById(id);
        model.addAttribute("teacherList",teacherList);
        model.addAttribute("collegeList",collegeList);
        model.addAttribute("course",course);
        return "admin/editCourse";
    }

    //修改课程
    @RequestMapping(value = "/editCourse",method = {RequestMethod.POST})
    public String editCourse(Course course){
        courseService.updateCourse(course);
        return "redirect:/admin/showCourse";
    }

    //根据名字搜索课程
    @RequestMapping(value = "/selectCourse",method = {RequestMethod.POST})
    public String selectCourse(Model model, String findByName){
        List<Course> courseList = null;
        courseList = courseService.queryCourseByName(findByName);
        for (Course course : courseList) {
            course.setCollegeName(collegeService.queryCollegeById(course.getCollegeID()).getCollegeName());
        }
        model.addAttribute("courseList",courseList);
        return "admin/showCourse";
    }

    //删除课程
    @RequestMapping("/removeCourse")
    public String removeCourse(Integer id){
        courseService.deleteCourseById(id);
        return "redirect:/admin/showCourse";
    }



    //--------------------------------------账号密码重置------------------------------------------------//
    //管理员跳转到密码重置页面
    @RequestMapping(value = "/passwordReset",method = {RequestMethod.GET})
    public String passwordResetUI(){
        return "admin/passwordReset";
    }

    //管理员修改密码
    @RequestMapping(value = "/passwordReset",method = {RequestMethod.POST })
    public String passwordReset(String oldPassword, String password1) throws CustomException {
        UserLogin user = userLoginService.queryUserLoginByName("admin");
        if (!oldPassword.equals(user.getPassword())){
            throw new CustomException("旧密码不正确");
        }else {
            user.setPassword(password1);
        }
        return "admin/passwordReset";
    }

    //非管理员跳转到修改密码页面
    @RequestMapping(value = "/userPasswordReset",method = {RequestMethod.GET})
    public String userPasswordResetUI(){
        return "admin/userPasswordReset";
    }

    //非管理员修改密码操作
    @RequestMapping(value = "/userPasswordReset",method = {RequestMethod.POST})
    public String userPasswordReset(UserLogin userLogin) throws CustomException {
        System.out.println(userLogin);
        UserLogin user = userLoginService.queryUserLoginByName(userLogin.getUserName());
        if (user == null){
            throw new CustomException("该用户不存在");
        }else if ("admin".equals(user.getUserName())){
            throw new CustomException("该用户为管理员，无法修改");
        }else if (userLogin.getPassword().equals(user.getPassword())){
            throw new CustomException("新密码与旧密码一致");
        }else {
            userLoginService.updateUserLogin(userLogin);
        }
        return "admin/userPasswordReset";
    }
}
