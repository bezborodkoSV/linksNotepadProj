package com.example.linksNotepad.service;

import com.example.linksNotepad.model.LinkGroups;
import com.example.linksNotepad.model.Role;
import com.example.linksNotepad.model.UserInfo;
import com.example.linksNotepad.repository.LinkGroupsRepository;
import com.example.linksNotepad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LinkGroupsRepository linkGroupsRepository;

    public UserDetailsServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, LinkGroupsRepository linkGroupsRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.linkGroupsRepository = linkGroupsRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userInfo;
    }

    @Transactional
    public boolean userSave(UserInfo userInfo) {
        UserInfo userInfoDb = userRepository.findByUsername(userInfo.getUsername());
        if (userInfoDb != null) {
            return false;
        }
        userInfo.setUsername(userInfo.getUsername().trim());
        userInfo.setPassword(userInfo.getPassword().trim());
        userInfo.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        LinkGroups linkGroups = new LinkGroups();
        linkGroups.setNameGroup("Default");
        linkGroups.setUserInfo(userRepository.findByUsername(userInfo.getUsername()));
        linkGroupsRepository.save(linkGroups);
        return true;
    }
}
