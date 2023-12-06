package com.ll.medium.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void create(MemberCreateForm memberCreateForm){
        Member member = Member.builder()
                .username(memberCreateForm.getUsername())
                .password(memberCreateForm.getPassword())
                .build();

        memberRepository.save(member);
    }

}
