package com.pmember.repository;

import com.pmember.dto.MemberFormDto;
import com.pmember.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    Member findByShowId(String showId);
}
