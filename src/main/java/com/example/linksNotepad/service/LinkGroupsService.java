package com.example.linksNotepad.service;

import com.example.linksNotepad.model.LinkGroups;
import com.example.linksNotepad.repository.LinkGroupsRepository;
import com.example.linksNotepad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkGroupsService {
    private final UserRepository userRepository;
    private final LinkGroupsRepository linkGroupsRepository;

    public LinkGroupsService(LinkGroupsRepository linkGroupsRepository, UserRepository userRepository) {
        this.linkGroupsRepository = linkGroupsRepository;
        this.userRepository = userRepository;
    }


    public boolean saveLinkGroups(LinkGroups linkGroups,String username){
        LinkGroups linkGroupsDb = linkGroupsRepository.findLinkGroupsByUserInfo_UsernameAndNameGroup(username,linkGroups.getNameGroup());
        if (linkGroupsDb!=null){
            return false;
        }
        linkGroups.setUserInfo(userRepository.findByUsername(username));
        linkGroupsRepository.save(linkGroups);
        return true;
    }

    public LinkGroups findGroupByNameGroup(String nameGroup){return linkGroupsRepository.findLinkGroupsByNameGroup(nameGroup);}



    public List<LinkGroups> listGroup(String username){
        return linkGroupsRepository.findLinkGroupsByUserInfo_Username(username);
    }

}
