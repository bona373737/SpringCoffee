package com.kosa.springcoffee.entity;


import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "sc_member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class Member extends BaseEntity{
    @Id
    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();


    //@OneToMany(mappedBy = "member")
    //private List<Order> orders = new ArrayList<>();


    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }

}
