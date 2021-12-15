package com.example.linksNotepad.repository;

import com.example.linksNotepad.model.Role;
import com.example.linksNotepad.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {
    UserInfo findByUsername(String username);

}
