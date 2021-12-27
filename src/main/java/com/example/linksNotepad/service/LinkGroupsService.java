package com.example.linksNotepad.service;

import com.example.linksNotepad.model.LinkGroups;
import com.example.linksNotepad.repository.LinkGroupsRepository;
import com.example.linksNotepad.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LinkGroupsService {
    private final UserRepository userRepository;
    private final LinkGroupsRepository linkGroupsRepository;

    final static Logger logger = Logger.getLogger(LinkGroupsService.class);

    public LinkGroupsService(LinkGroupsRepository linkGroupsRepository, UserRepository userRepository) {
        this.linkGroupsRepository = linkGroupsRepository;
        this.userRepository = userRepository;
    }


    public boolean saveLinkGroups(LinkGroups linkGroups, String username) {
        LinkGroups linkGroupsDb = linkGroupsRepository.findLinkGroupsByUserInfo_UsernameAndNameGroup(username, linkGroups.getNameGroup());
        if (linkGroupsDb != null) {
            return false;
        }
        linkGroups.setUserInfo(userRepository.findByUsername(username));
        linkGroupsRepository.save(linkGroups);
        return true;
    }

    public LinkGroups findGroupByNameGroup(String nameGroup) {
        return linkGroupsRepository.findLinkGroupsByNameGroup(nameGroup);
    }


    public List<LinkGroups> listGroup(String username) {
        logger.info("Show oll groups by name user");
        return linkGroupsRepository.findLinkGroupsByUserInfo_Username(username);
    }

}
