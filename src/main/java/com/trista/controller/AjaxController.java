package com.trista.controller;

import com.mysql.cj.util.StringUtils;
import com.trista.pojo.Student;
import com.trista.pojo.UserLogin;
import com.trista.service.StudentService;
import com.trista.service.StudentServiceImpl;
import com.trista.service.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Controller
public class AjaxController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private UserLoginServiceImpl userLoginService;

    @RequestMapping("/a")
    @ResponseBody
    public String a(String userID){
        String msg = "";
        if (userID.equals("")){
            msg = "wrong";
        }else {
            Student student = studentService.queryStudentById(Integer.parseInt(userID));
            if (null != student) {
                msg = "exist";
            } else {
                msg = "noexist";
            }
        }
        return msg;
    }

    @RequestMapping("/b")
    @ResponseBody
    public String b(String userName){
        String msg = "";
        if (userName.equals("")){
            msg = "wrong";
        }else {
            UserLogin user = userLoginService.queryUserLoginByName(userName);
            System.out.println(user);
            if (null != user) {
                msg = "exist";
            } else {
                msg = "noexist";
            }
        }
        return msg;
    }

}

