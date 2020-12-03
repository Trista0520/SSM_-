package com.trista.controller;


import com.trista.exception.CustomException;
import com.trista.pojo.Course;
import com.trista.pojo.PagingVO;
import com.trista.pojo.SelectedCourse;
import com.trista.service.CourseServiceImpl;
import com.trista.service.SelectedServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private SelectedServiceImpl selectedService;


    //所有课程展示
    @RequestMapping("/showCourse")
    public String showCourse(Integer page, Model model){
        PagingVO pagingVO = new PagingVO();
        List<Course> courseList = null;
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
        return "student/showCourse";
    }

    //根据名称搜索课程
    @RequestMapping(value = "/selectCourse",method = {RequestMethod.POST})
    public String selectCourse(String findByName,Integer page,Model model){
        List<Course> courseList = courseService.queryCourseByName(findByName);
        model.addAttribute("courseList",courseList);
        return "student/showCourse";
    }

    //选课操作
    @RequestMapping("/stuSelectedCourse")
    public String stuSelectedCourse(Integer id,Model model) throws CustomException {

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        String  username = (String) subject.getPrincipal();
        SelectedCourse course = new SelectedCourse();
        Map<String, Object> map = new HashMap<String, Object>();
        course.setCourseID(id);
        course.setStudentID(Integer.parseInt(username));
        map.put("courseID",id);
        map.put("studentID",Integer.parseInt(username));
        if (selectedService.querySelectedCourse(map) == null){
            selectedService.addSelectedCourse(course);
        }else {
            throw new CustomException("该门课程你已经选了，不能再选");
        }
        return "redirect:/student/showCourse";
    }


    //展示已选课程
    @RequestMapping("/selectedCourse")
    public String selectedCourse(Model model){
        Subject subject = SecurityUtils.getSubject();
        String  username = (String) subject.getPrincipal();
        List<Course> courseList = new ArrayList<Course>();
        List<SelectedCourse> list = selectedService.querySelectedCourseByStuId(Integer.parseInt(username));
        System.out.println(list);
        for (SelectedCourse selectedCourse : list) {
            Course course = courseService.queryCourseById(selectedCourse.getCourseID());
            System.out.println(course);
            if (course != null){
                courseList.add(course);
            }
        }
        model.addAttribute("selectedCourseList",courseList);
        return "student/selectCourse";
    }

    //退课操作


}
