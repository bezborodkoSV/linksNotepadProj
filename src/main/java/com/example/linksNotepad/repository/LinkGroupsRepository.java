package com.example.linksNotepad.repository;

import com.example.linksNotepad.model.LinkGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkGroupsRepository extends JpaRepository<LinkGroups,Long> {
    LinkGroups findLinkGroupsByNameGroup(String nameGroup);
    List<LinkGroups> findLinkGroupsByUserInfo_Username(String username);
    LinkGroups findLinkGroupsByUserInfo_UsernameAndNameGroup(String username,String nameGroup);
}
