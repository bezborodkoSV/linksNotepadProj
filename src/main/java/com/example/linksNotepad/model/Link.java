package com.example.linksNotepad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity

@Table(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column
    private String url;
    @Column
    private String description;
    @Column
    private String yourNotesAboutLink;
    @JsonIgnore
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "link_groups_id")
    private LinkGroups linkGroups;
    @JsonIgnore
    @ManyToOne
    @PrimaryKeyJoinColumn
    private UserInfo userInfo;

    public Link() {
    }

    public Link(LinkGroups linkGroups) {
        this.linkGroups = linkGroups;
    }
}
