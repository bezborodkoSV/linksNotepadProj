package com.example.linksNotepad.controller;

import com.example.linksNotepad.model.Link;
import com.example.linksNotepad.model.LinkGroups;
import com.example.linksNotepad.service.LinkGroupsService;
import com.example.linksNotepad.service.LinkServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.constraints.NotEmpty;
import java.security.Principal;

@Controller
public class LinkAndLinksGroupController {
    private final LinkGroupsService linkGroupsService;
    private final LinkServiceImpl linkService;
    Logger logger = Logger.getLogger(LinkAndLinksGroupController.class);

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

    @GetMapping("/private/changeLinkParameter/{nameGroup}/{nameLink}")
    public String viewChangeLinkParameter(Model model,Principal principal,@PathVariable("nameGroup")String nameGroup,@PathVariable("nameLink")String nameLink){
        model.addAttribute("linkByName", linkService.link(nameLink, principal.getName(),nameGroup));
//        select
        model.addAttribute("linkGroupList",linkGroupsService.listGroup(principal.getName()));
        return "changeLinkParameter";
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
    @PreAuthorize("hasRole('USER') or #userInfo.id ==authentication.name")
    public String saveLink(@ModelAttribute("saveLink") Link link,@RequestParam("nameGroup")String nameGroup, Principal principal,RedirectAttributes redirectAttributes) {
        if (link.getName().length()==0){
            redirectAttributes.addFlashAttribute("errorNameLink","Link name cannot be empty");
            return "redirect:/private/home";
        }
        if (linkService.saveLink(link,nameGroup, principal.getName())!=true){
            redirectAttributes.addFlashAttribute("errorNameLink","A link with the same name already exists in such a group");
            return "redirect:/private/home";
        }
        linkService.saveLink(link, nameGroup ,principal.getName());
        if (nameGroup=="Default"){
            return "redirect:/private/home";
        }
        return "redirect:/private/home/"+nameGroup;
    }

    @PostMapping("/addLinkGroup")
    public String saveLinkGroups(@ModelAttribute("saveLinkGroups")LinkGroups linkGroups, Principal principal, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "home";
        }
        if (linkGroupsService.saveLinkGroups(linkGroups,principal.getName())!=true){
            redirectAttributes.addFlashAttribute("groupError","This group already exists");
            return "redirect:/private/home";
        }
        linkGroupsService.saveLinkGroups(linkGroups,principal.getName());
        String redirectUrl = "redirect:/private/home/"+linkGroups.getNameGroup();
        return redirectUrl;
    }

    @PostMapping("/changeLinkParameter")
    public String changeLinkParameter(@ModelAttribute("linkByName") Link linkByName,Model model){
        if (linkService.updateLink(linkByName)!=true){
            model.addAttribute("errorLinkNameInGroup","A link with the same name already exists in such a group");
            return "changeLinkParameter";
        }
        linkService.updateLink(linkByName);
        return "redirect:/private/home";
    }

    @PostMapping("/deleteLink")
    public String deleteLink(@RequestParam("nameLink") String nameLink,Principal principal){
        linkService.deleteLink(nameLink, principal.getName());
        return "redirect:private/home";
    }
}
