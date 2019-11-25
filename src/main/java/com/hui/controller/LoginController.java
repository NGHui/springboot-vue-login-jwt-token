package com.hui.controller;

import com.hui.bean.User;
import com.hui.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 辉
 * 座右铭:坚持总能遇见更好的自己!
 * @date 2019/11/20
 */
//@CrossOrigin("*")
@Controller
public class LoginController {

    @Autowired
    private LoginMapper loginMapper;

    @RequestMapping("/list")
    @ResponseBody
    public Map<Object,Object> list(HttpServletRequest request, HttpServletResponse response){
        List<User> list = loginMapper.list();
        request.getSession().setAttribute("list",list);
        //写自己的cookies
        String s = UUID.randomUUID().toString();
        //System.out.println(s);
        Cookie cookie = new Cookie("hui", s);
        response.addCookie(cookie);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("list",list);
        map.put("token",s);
        return map;
    }

    @GetMapping("/hui")
    @ResponseBody
    public String getToken(/*@RequestParam(value = "name") String name ,*/HttpServletRequest request, HttpServletResponse response){
        //System.out.println(name);
        String header = request.getHeader("token");
        System.out.println(header);
        return header;
    }
    /*@RequestMapping("/login")
    @ResponseBody
    public User login(User user){
        user.setUsername("admin");
        user.setPassword("admin");
        User login = loginMapper.login(user);
        //System.out.println(login.getUsername());
        return login;
    }*/
}
