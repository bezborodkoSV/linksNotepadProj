package com.example.linksNotepad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "roles")
@Getter
@Setter
@Table(name = "user_inf")
public class UserInfo implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String password;

    transient private String passwordConfirm;
    @JsonIgnore
    @OneToMany(mappedBy = "userInfo")
    private Set<Link> linkSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userInfo")
    private Set<LinkGroups> linkGroupsSet;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
