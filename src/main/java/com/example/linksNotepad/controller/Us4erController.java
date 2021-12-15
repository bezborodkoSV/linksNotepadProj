package com.example.linksNotepad.controller;

import com.example.linksNotepad.model.Link;
import com.example.linksNotepad.repository.LinkRepository;
import com.example.linksNotepad.service.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller(value = "/private")
public class Us4erController {
    private final LinkServiceImpl linkService;

    public Us4erController(LinkServiceImpl linkService) {
        this.linkService = linkService;
    }

//    @GetMapping("/add")
//    public String add(Model model) {
//        model.addAttribute("saveLink", new Link());
//        return "add";
//    }

    //
//    @PostMapping("/add")
//    public String saveLink(@ModelAttribute("saveLink") Link link, String linkGroup, BindingResult bindingResult, Principal principal) {
//        linkService.saveLink(link, linkGroup, principal.getName());
//        return "redirect:/private/home";
//    }
//    @PostMapping("/add")
//    public String saveLink(@ModelAttribute("saveLink") Link link, String linkGroup, BindingResult bindingResult, Principal principal) {
//        linkService.saveLink(link, linkGroup, principal.getName());
//        return "redirect:/private/home";
//    }
}
