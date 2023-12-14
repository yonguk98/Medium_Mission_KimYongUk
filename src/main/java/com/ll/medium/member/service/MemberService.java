package com.ll.medium.member.service;

import com.ll.medium.DataNotFoundException;
import com.ll.medium.member.entity.Member;
import com.ll.medium.member.entity.MemberCreateForm;
import com.ll.medium.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Member getMemberByUsername(String username){
        Optional<Member> member = memberRepository.findByUsername(username);

        if(member.isPresent()){
            return member.get();
        }else{
            throw new DataNotFoundException("member not found");
        }
    }

}
