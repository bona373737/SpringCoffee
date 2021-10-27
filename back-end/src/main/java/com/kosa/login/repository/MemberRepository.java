package com.kosa.login.repository;

import com.kosa.login.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)//Role과 left outer join
    @Query("select m from sc_member m where m.fromSocial = :social and m.email=:email")
    Optional<Member> findByEmail(String email, boolean social);

}
