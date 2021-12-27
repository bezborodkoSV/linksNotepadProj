package com.example.linksNotepad.controller;

import com.example.linksNotepad.model.UserInfo;
import com.example.linksNotepad.service.UserDetailsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginAndRegistrationController {
    private final UserDetailsServiceImpl userDetailsService;

    public LoginAndRegistrationController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
//    @GetMapping(value = "/login")
//public String login(String error, String logout, Model model, Principal principal){
//if (error!=null){
//model.addAttribute("error","Your username or password is invalid.");
//}
//if (logout!=null){
//    model.addAttribute("message", "You have been logged out successfully.");
//}
//
//    return "redirect:/home";
//}

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserInfo());
        return "registration";
    }

    @PostMapping("/registration")
    public String save(@ModelAttribute("userForm") UserInfo userInfo, Model model) {
        if (!userInfo.getPassword().equals(userInfo.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userDetailsService.userSave(userInfo)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/welcome";
    }
}
