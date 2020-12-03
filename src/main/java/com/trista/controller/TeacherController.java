package com.trista.controller;

import com.trista.exception.CustomException;
import com.trista.pojo.*;
import com.trista.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private SelectedServiceImpl selectedService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private UserLoginServiceImpl userLoginService;

    //分页展示课程信息
    @RequestMapping("/showCourse")
    public String showCourse(Model model){
        //获取当前用户信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<Course> courseList = null;
        courseList = courseService.queryCourseByTeacherID(Integer.parseInt(username));
        model.addAttribute("courseList",courseList);
        return "teacher/showCourse";
    }

    //根据名字搜索课程
    @RequestMapping(value = "/selectCourse",method = {RequestMethod.POST})
    public String selectCourse(String findByName,Model model){
        List<Course> courseList = courseService.queryCourseByName(findByName);
        model.addAttribute("courseList",courseList);
        return "teacher/showCourse";
    }

    //跳转到成绩显示界面
    @RequestMapping("/gradeCourse")
    public String showGrade(Integer id,Integer page,Model model){
        PagingVO pagingVO = new PagingVO();
        List<SelectedCourse> selectedCourseList = selectedService.querySelectedCourseById(id);
        pagingVO.setTotalCount(selectedCourseList.size());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("courseID",id);
        map.put("pageSize",pagingVO.getPageSize());
        if (page==null || page == 0){
            pagingVO.setToPageNo(1);
            map.put("toPageNo",1);
            selectedCourseList = selectedService.querySelectedCourseByLimit(map);
            System.out.println(selectedCourseList);
        }else {
            pagingVO.setToPageNo(page);
            map.put("toPageNo",page);
            selectedCourseList = selectedService.querySelectedCourseByLimit(map);
        }
        for (SelectedCourse selectedCourse : selectedCourseList) {
            selectedCourse.setStudentName(studentService.queryStudentById(selectedCourse.getStudentID()).getUserName());
            if (selectedCourse.getMark() == null){
                selectedCourse.setOver(false);
            }else {
                selectedCourse.setOver(true);
            }
        }
        model.addAttribute("selectedCourseList",selectedCourseList);
        model.addAttribute("pagingVO",pagingVO);
        return "teacher/showGrade";
    }

    //跳转到打分界面
    @RequestMapping(value = "/mark",method = {RequestMethod.GET})
    public String markUI(Model model,int courseID, int studentID){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("courseID",courseID);
        map.put("studentID",studentID);
        SelectedCourse selectedCourse = selectedService.querySelectedCourse(map);
        selectedCourse.setStudentName(studentService.queryStudentById(selectedCourse.getStudentID()).getUserName());
        model.addAttribute("selectedCourse",selectedCourse);
        return "teacher/mark";
    }

    //打分操作
    @RequestMapping(value = "/mark",method = {RequestMethod.POST})
    public String mark(SelectedCourse selectedCourse){
        selectedService.updateSelectedCourse(selectedCourse);
        return "redirect:/teacher/gradeCourse?id="+selectedCourse.getCourseID();
    }

    //跳转到密码修改操作
    @RequestMapping(value = "/passwordReset",method = {RequestMethod.GET})
    public String passwordRestUI(){
        return "teacher/passwordReset";
    }
    //密码修改
    @RequestMapping(value = "/passwordReset",method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1) throws CustomException {
        UserLogin user = userLoginService.queryUserLoginByName("1001");
        System.out.println(user);
        if (!oldPassword.equals(user.getPassword())){
            throw new CustomException("旧密码不正确");
        }else {
            user.setPassword(password1);
            System.out.println(user.getPassword());
        }
        return "/WEB-INF/login.jsp";
    }
}
