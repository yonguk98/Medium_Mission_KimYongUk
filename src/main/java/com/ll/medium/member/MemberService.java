package com.ll.medium.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(MemberCreateForm memberCreateForm){
        Member member = Member.builder()
                .username(memberCreateForm.getUsername())
                .password(passwordEncoder.encode(memberCreateForm.getPassword()))
                .build();

        memberRepository.save(member);
    }

}
