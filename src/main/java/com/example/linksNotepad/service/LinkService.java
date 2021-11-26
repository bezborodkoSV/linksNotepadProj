package com.example.linksNotepad.service;

import com.example.linksNotepad.model.Link;
import com.example.linksNotepad.repository.LinkGroupsRepository;
import com.example.linksNotepad.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {
    private final LinkGroupsRepository linkGroupsRepository;
    private final LinkRepository linkRepository;



    public LinkService(LinkGroupsRepository linkGroupsRepository, LinkRepository linkRepository) {
        this.linkGroupsRepository = linkGroupsRepository;
        this.linkRepository = linkRepository;
    }

    public boolean saveLink(Link link) {
        linkRepository.save(link);
        return true;
    }
    public List<Link> allLinks(){
        return linkRepository.findAll();
    }
}
