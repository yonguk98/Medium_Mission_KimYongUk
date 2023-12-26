package com.ll.medium.member.service;

import com.ll.medium.global.DataNotFoundException;
import com.ll.medium.global.JwtUtil;
import com.ll.medium.global.security.SecurityUser;
import com.ll.medium.member.entity.Member;
import com.ll.medium.member.dto.MemberCreateForm;
import com.ll.medium.member.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public SecurityUser getUserFromApiKey(String apiKey) {
        Claims claims = JwtUtil.decode(apiKey);

        Map<String, Object> data = (Map<String, Object>) claims.get("data");
        long id = Long.parseLong((String) data.get("id"));
        String username = (String) data.get("username");
        List<? extends GrantedAuthority> authorities = ((List<String>) data.get("authorities"))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new SecurityUser(
                id,
                username,
                "",
                authorities
        );
    }

}
