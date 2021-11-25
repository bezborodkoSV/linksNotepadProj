package com.example.linksNotepad.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity

@Table(name = "link_groups")
public class LinkGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String nameGroup;
    @Column
    private String descriptionLinkGroups;
//    @OneToMany(mappedBy = "linkGroups")
//    private Set<Link> links;

}
