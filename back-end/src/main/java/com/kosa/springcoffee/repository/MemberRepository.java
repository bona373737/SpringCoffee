package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("select m from sc_member m where m.fromSocial = :social and m.email=:email")
    Optional<Member> getByEmail(String email, boolean social);

    @Query("select m from sc_member m where m.email=:email")
    Member getByEmail(String email);

    UserDetails findByEmail(String email);

    Boolean existsByEmail(String email);

    @Override
    List<Member> findAll();
}