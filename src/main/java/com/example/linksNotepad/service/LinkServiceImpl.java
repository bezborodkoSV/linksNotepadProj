package com.example.linksNotepad.service;

import com.example.linksNotepad.model.Link;
import com.example.linksNotepad.model.LinkGroups;
import com.example.linksNotepad.repository.LinkGroupsRepository;
import com.example.linksNotepad.repository.LinkRepository;
import com.example.linksNotepad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LinkServiceImpl {
    private final LinkGroupsRepository linkGroupsRepository;
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public LinkServiceImpl(LinkGroupsRepository linkGroupsRepository, LinkRepository linkRepository, UserRepository userRepository) {
        this.linkGroupsRepository = linkGroupsRepository;
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public boolean saveLink(Link link,String nameGroup,String username) {
        if(nameGroup==null){
            link.setLinkGroups(linkGroupsRepository.findLinkGroupsByUserInfo_UsernameAndNameGroup(username,"Default"));
        }else {
            link.setLinkGroups(linkGroupsRepository.findLinkGroupsByUserInfo_UsernameAndNameGroup(username, nameGroup));
        }
        link.setUserInfo(userRepository.findByUsername(username));
        linkRepository.saveAndFlush(link);
        return true;
    }

    public List<Link> linkByGroup(String username,String nameGroup){
        return linkRepository.findLinksByUserInfo_UsernameAndLinkGroups_NameGroup(username,nameGroup);
    }

    public List<Link> allLinks(String username){
        return linkRepository.findLinksByUserInfo_Username(username);
    }

    @Transactional
    public boolean updateLink(Link link){
        System.out.println(link.getId()+"   "+link.getName()+"   name group "+link.getLinkGroups().getNameGroup());
        linkRepository.save(link);
        return true;
    }

    public Link link(String nameLink,String username,String nameGroup){
        return linkRepository.findLinksByNameAndUserInfo_UsernameAndLinkGroups_NameGroup(nameLink,username,nameGroup);
    }
}
