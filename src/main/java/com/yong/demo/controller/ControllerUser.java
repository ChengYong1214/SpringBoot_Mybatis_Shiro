package com.yong.demo.controller;

import com.yong.demo.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ControllerUser {

    //返回登陆视图
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //登陆请求验证
    @RequestMapping("/loginAction")
    public String loginAction(User user,Model model){
        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 3.执行登录方法
        try{
            subject.login(token);
            return "redirect:/index";
        } catch (UnknownAccountException e){
            e.printStackTrace();
            model.addAttribute("msg","用户名不存在！");
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误！");
        }
        return "login";
    }

    //登陆成功返回首页
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //退出登陆
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "redirect:/login";
    }


    //无权限返回视图
    @RequestMapping("/unAuth")
    public String unAuth(){
        return "unAuth";
    }


    //个人信息
    @RequestMapping("/myself")
    public String myself(Model model){
        User user= (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "myself";
    }

    //拥有的权限访问页面，要认证才可访问
    @RequestMapping("/perm1")
    @RequiresPermissions("睡觉")
    public String perm1(){
        return "perm/perm1";
    }
    @RequestMapping("/perm2")
    @RequiresPermissions("打游戏")
    public String perm2(){
        return "perm/perm2";
    }
    @RequestMapping("/perm3")
    @RequiresPermissions("看电影")
    public String perm3(){
        return "perm/perm3";
    }
}
