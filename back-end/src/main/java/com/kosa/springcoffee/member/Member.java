package com.kosa.springcoffee.member;


import com.kosa.springcoffee.noticeboard.Board;
import com.kosa.springcoffee.qnaboard.QnaBoard;
import com.kosa.springcoffee.cart.Cart;
import com.kosa.springcoffee.base.entity.*;
import com.kosa.springcoffee.order.Order;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name = "sc_member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class Member extends BaseEntity implements UserDetails {
    @Id
    private String email;

    private String password;

    private String name;

    private String address;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "writer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "writer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<QnaBoard> qnaBoards = new ArrayList<>();

    public void addMemberRole(MemberRole memberRole){
        roles.add(memberRole);
    }

    public void removeMemberRole() {roles.clear();}

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeAddress(String address){
        this.address = address;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<String> roleList = new ArrayList<>();
        this.roles.forEach(role -> roleList.add(role.name()));

        return roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
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