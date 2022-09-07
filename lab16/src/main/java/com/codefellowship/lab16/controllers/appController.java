package com.codefellowship.lab16.controllers;

import com.codefellowship.lab16.models.appUser;
import com.codefellowship.lab16.repositories.AppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.security.Principal;

@Controller
public class appController  {
    @Autowired
    AppRepo appRepo;

    @Autowired
     private PasswordEncoder passwordEncoder;

    @Autowired
    HttpServlet request;


    @GetMapping("/")
    public String homePage(Principal p, Model m){
        if (p !=null){
            String username = p.getName();
            appUser yousir = appRepo.findByUsername(username);

            m.addAttribute("username",username);
            m.addAttribute("nickname", yousir.getNickname());
        }
        return "index.html";
    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    @GetMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String nickname, String password){
        String hasedPassword = passwordEncoder.encode(password);
        appUser newYousir = new appUser(username, hasedPassword, nickname);
        appRepo.save(newYousir);
        authWithHttpServletRequest(username, password);
        return new RedirectView("/login");
    }
    public void authWithHttpServletRequest(String username, String password){
       /* try{
            request.login(username, password);
        } catch (ServletException e){
            e.printStackTrace();
        }*/
    }
}
