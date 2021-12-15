package com.example.linksNotepad.repository;

import com.example.linksNotepad.model.Link;
import com.example.linksNotepad.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link,Long> {
    List<Link> findLinksByUserInfo_Username(String usernameId);
    List<Link> findLinksByUserInfo_UsernameAndLinkGroups_NameGroup(String username,String nameGroup);
}
