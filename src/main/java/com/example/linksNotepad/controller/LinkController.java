package com.example.linksNotepad.controller;

import com.example.linksNotepad.service.LinkService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jws.WebParam;

@Controller
public class LinkController {
//    @Autowired
//    private LinkService linkService;

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/first")
    private String link(Model model) {
model.addAttribute("allLinks",linkService.allLinks());
//        return "first";
        return null;
    }


}
