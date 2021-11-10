package com.kosa.springcoffee.repository;

import com.kosa.springcoffee.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from sc_member m where m.email=:email")
    Optional<Member> findByEmailOptional(String email);

    Member findByEmail(String email);

}
