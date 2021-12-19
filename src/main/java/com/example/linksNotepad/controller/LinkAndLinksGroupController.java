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
public class LinkAndLinksGroupController {
    private final LinkGroupsService linkGroupsService;
    private final LinkServiceImpl linkService;

    public LinkAndLinksGroupController(LinkServiceImpl linkService, LinkGroupsService linkGroupsService) {
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

    @PostMapping("/addLink")
    public String saveLink(@ModelAttribute("saveLink") Link link,@RequestParam("nameGroup")String nameGroup, Principal principal) {
        linkService.saveLink(link, nameGroup ,principal.getName());
        if (nameGroup==null){
            return "redirect:/private/home";
        }
        return "redirect:/private/home/"+nameGroup;
    }

    @PostMapping("/addLinkGroup")
    public String saveLinkGroups(@ModelAttribute("saveLinkGroups")LinkGroups linkGroups,Principal principal){
        linkGroupsService.saveLinkGroups(linkGroups,principal.getName());
        String redirectUrl = "redirect:/private/home/"+linkGroups.getNameGroup();
        return redirectUrl;
    }

    @PostMapping("/changeLinkParameter")
    public String changeLinkParameter(@ModelAttribute("linkByName") Link linkByName){
        System.out.println(linkByName.getLinkGroups().getNameGroup()+" group?????????????????????????????????????????????????????????????? id group"+ linkByName.getLinkGroups().getId());
        linkService.updateLink(linkByName);
        return "redirect:/private/home";
    }

    @GetMapping("/private/changeLinkParameter/{nameGroup}/{nameLink}")
    public String viewChangeLinkParameter(Model model,Principal principal,@PathVariable("nameGroup")String nameGroup,@PathVariable("nameLink")String nameLink){
        model.addAttribute("linkByName", linkService.link(nameLink, principal.getName(),nameGroup));
//        select
        model.addAttribute("linkGroupList",linkGroupsService.listGroup(principal.getName()));
        return "changeLinkParameter";
    }
}
