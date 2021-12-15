package com.example.linksNotepad.controller;

import com.example.linksNotepad.model.Link;
import com.example.linksNotepad.model.LinkGroups;
import com.example.linksNotepad.service.LinkGroupsService;
import com.example.linksNotepad.service.LinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class LinkController {
    private final LinkGroupsService linkGroupsService;
    private final LinkServiceImpl linkService;

    public LinkController(LinkServiceImpl linkService, LinkGroupsService linkGroupsService) {
        this.linkService = linkService;
        this.linkGroupsService = linkGroupsService;
    }

//    @GetMapping("/cap")
//    public String link(Model model) {
////        model.addAttribute("allLinks", linkService.allLinks());
//        return "cap";
//    }

    @GetMapping(value = "/private/home")
    public String linkHome(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("allLinks", linkService.allLinks(principal.getName()));
            model.addAttribute("linkGroups",linkGroupsService.listGroup(principal.getName()));
        }
        model.addAttribute("saveLinkGroup", new LinkGroups());
        model.addAttribute("saveLink", new Link());
        if ((principal == null)) {
            System.out.println("principal null");
        } else {
            System.out.println(principal.getName().toString() + "          log user");
        }
        return "home";
    }

    @GetMapping(value = "/private/home/{nameGroup}")
    public String linkByGroup(@PathVariable String nameGroup, Model model,Principal principal){
        System.out.println(" name group  " + nameGroup);
        System.out.println(" size "+ linkService.linkByGroup(principal.getName(),nameGroup).size());
        model.addAttribute("allLinks", linkService.linkByGroup(principal.getName(),nameGroup));
        model.addAttribute("linkGroups",linkGroupsService.listGroup(principal.getName()));
        model.addAttribute("saveLinkGroup", new LinkGroups());
        model.addAttribute("saveLink", new Link());
        return "home";
    }

//    @PostMapping(value = "/private/home/{nameGroup}")
//    public String linkHomeByGroup(@RequestParam(name = "nameGroup") String nameGroup){
//        System.out.println(nameGroup);
//        return "home";
//    }
//    @GetMapping(value = "/addLink")
//    public String linkAdd(Model model) {
////        model.addAttribute("allLinks", linkService.allLinks());
//        model.addAttribute("saveLink",new Link());
//        return "addLink";
//    }

    @PostMapping("/add")
    public String saveLink(@ModelAttribute("saveLink") Link link, String linkGroup, BindingResult bindingResult, Principal principal) {
        linkService.saveLink(link, linkGroup, principal.getName());
        return "redirect:/private/home";
    }

    @PostMapping("/addLinkGroup")
    public String saveLinkGroups(@ModelAttribute("saveLinkGroups")LinkGroups linkGroups,Principal principal){
        linkGroupsService.saveLinkGroups(linkGroups,principal.getName());
        return "redirect:/private/home";
    }
}
