package com.example.linksNotepad.model;

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
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "links_group_id")
    private LinkGroups linkGroups;
    @ManyToOne
    @PrimaryKeyJoinColumn
    private UserInfo userInfo;
}
