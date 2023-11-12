package com.project.controller;

import com.project.model.User;
import com.project.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {


    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    @ResponseBody
    public String testAddUser(){
        User user = new User();
        user.setName("Soham");
        user.setEmail("soham@gmail.com");
        user.setPassword("Password123!!");
        user.setRole("Admin");
        userRepository.save(user);

        return "Test Add New User Successfull";
    }

    @GetMapping("/home")
    public String homePageController(Model model){
        model.addAttribute("title","Contact Management System");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPageController(Model model){
        model.addAttribute("title","About - Contact Management System");
        return "about";
    }

    @GetMapping("/signup")
    public String signupPageController(Model model){
        model.addAttribute("title","Register - Contact Management System");
        model.addAttribute("user",new User());
        return "register";
    }


    @PostMapping("/registerNewUser")
    public String addNewUser(@ModelAttribute("user") User user, @RequestParam(value = "agreement",defaultValue = "false") boolean agreement, Model model, HttpSession session){

        try{
            if (!agreement){
                System.out.println("You have not agreed the Terms and Conditions");
                throw new Exception("You have not agreed the Terms and Conditions");
            }
            user.setRole("ROLE_USER");
            user.setEnanbled(true);
            User result = userRepository.save(user);
            System.out.println("Saved to Database");
            System.out.println("User Data-> "+result);
            System.out.println("User Agreement Status-> "+agreement);
            model.addAttribute("user",new User());
            model.addAttribute("isSuccess", true);
            session.setAttribute("message","Successfully Registered");
            return "register";
        }
        catch (Exception e){
            e.printStackTrace();
            session.setAttribute("message","Something Went Wrong");
            model.addAttribute("isSuccess", false);
            return "register";
        }
        finally {
            session.removeAttribute("message"); // Always remove the message from the session after rendering
        }

    }


}
