package com.codefellowship.lab16.controllers;

import com.codefellowship.lab16.models.appUser;
import com.codefellowship.lab16.repositories.AppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class appController  {
    @Autowired
    AppRepo appRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
   private HttpServlet request;


    @GetMapping("/")
    public String homePage(Principal p, Model m){
        if (p !=null){
            String username = p.getName();
            appUser youSir = appRepo.findByUsername(username);

            m.addAttribute("username",username);
            m.addAttribute("nickname", youSir.getNickname());
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


    @GetMapping("/bossSauce")
    public String getSecretSauce(){
        return "secretSauce";
    }

    @GetMapping("/test")
    public String getTestPage(Principal p, Model m){
        if (p != null){
            String username = p.getName();
            appUser appuser = appRepo.findByUsername(username);

            m.addAttribute("username", username);
            m.addAttribute("nickname", appuser.getNickname());
        }
        return "test";
    }

    @GetMapping("/users/{id}")
    public  String getUserInfo(Model m, Principal p, @PathVariable Long id){
        if (p != null){
            String username = p.getName();
            appUser appuser = appRepo.findByUsername(username);

            m.addAttribute("username", username);
            m.addAttribute("nickname", appuser.getNickname());
        }
        appUser dataBUser = appRepo.findById(id).orElseThrow();
        m.addAttribute("dataBUserUsername",dataBUser.getUsername());
        m.addAttribute("dataBUserNickName",dataBUser.getNickname());
        m.addAttribute("dataBUserId", dataBUser.getId());

        m.addAttribute("testDate", LocalDateTime.now());

        return "user-info";
    }

    @PostMapping("/signup")
    public RedirectView createUser(String username, String nickname, String password){
        String hashedPassword = passwordEncoder.encode(password);
        appUser newYousir = new appUser(username, hashedPassword, nickname);
        appRepo.save(newYousir);
        //authWithHttpServletRequest(username, password);
        return new RedirectView("/login");
    }

    @PostMapping("/test")
    public  RedirectView testAppUser(){
        String hashedPassword = passwordEncoder.encode("password");
        appUser newAppUser = new appUser("wade",hashedPassword, "Parcival");
        appRepo.save(newAppUser);
        return new RedirectView("/");
    }

    @PutMapping("/user/{id}")
    public RedirectView editUserInfo(Model m, Principal p, @PathVariable Long id, String username, String nickname, RedirectAttributes redirect){
       if(p != null && p.getName().equals(username)){
           appUser newAppUser = appRepo.findById(id).orElseThrow();
           newAppUser.setUsername(username);
           newAppUser.setNickname(nickname);
           appRepo.save(newAppUser);
       } else{
           redirect.addFlashAttribute("errorMessage","Not allowed to edit another user's info");
       } return new RedirectView("/users/" + id);
    }



   /* public void authWithHttpServletRequest(String username, String password){
        try{
            request.login(username, password);
        } catch (ServletException e){
            e.printStackTrace();
        }
    }*/
}
