package com.example.linksNotepad.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    private long id;
    @Column
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<UserInfo> userInfos;

    public Role() {

    }

    @Override
    public String getAuthority() {
        return getName();
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
