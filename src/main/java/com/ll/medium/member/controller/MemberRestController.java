package com.ll.medium.member.controller;

import com.ll.medium.member.dto.MemberCreateForm;
import com.ll.medium.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemberRestController {

    MemberService memberService;

    private final HttpServletResponse response;
    private final HttpServletRequest request;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody MemberCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        memberService.create(form);
        return new ResponseEntity(HttpStatus.OK);
    }
//
//    @PostMapping("/login")
//    public GlobalResponse<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
//        GlobalResponse<Member> checkedResp = memberRestService.checkUsernameAndPassword(dto.getUsername(), dto.getPassword());
//
//        Member member = checkedResp.getData();
//        String accessToken = JwtUtil.encode(
//                60 * 10, // 1 minute
//                Map.of(
//                        "id", member.getId().toString(),
//                        "username", member.getUsername(),
//                        "authorities", member.getAuthoritiesAsStrList()
//                )
//        );
//        String refreshToken = JwtUtil.encode(
//                60 * 60 * 24, //1 day
//                Map.of(
//                        "id", member.getId().toString(),
//                        "username", member.getUsername()
//                )
//        );
//        memberRestService.setRefreshToken(member, refreshToken);
//
//        addCrossDomainCookie(accessToken, refreshToken);
//
//        return GlobalResponse.of("200", "로그인 성공.", new LoginResponseDto(member, accessToken, refreshToken));
//    }
//
//    @PostMapping("/login/refresh")
//    public GlobalResponse refreshAccessToken() {
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) {
//            return GlobalResponse.of("401", "cookies not exist.");
//        }
//        Optional<Cookie> refreshTokenCookieOp = Arrays.stream(cookies)
//                .filter(cookie -> cookie.getName().equals("refreshToken"))
//                .findFirst();
//        if (refreshTokenCookieOp.isEmpty()) {
//            return GlobalResponse.of("401", "refreshToken not exist.");
//        }
//
//        String refreshToken = refreshTokenCookieOp.get().getValue();
//        Member member = memberRestService.findMemberByRefreshToken(refreshToken).get();
//        String accessToken = JwtUtil.encode(
//                60 * 10,
//                Map.of(
//                        "id", member.getId().toString(),
//                        "username", member.getUsername(),
//                        "authorities", member.getAuthoritiesAsStrList()
//                )
//        );
//        ResponseCookie accessCookie = ResponseCookie.from("accessToken", accessToken)
//                .path("/")
//                .maxAge(60 * 10)
//                .sameSite("None")
//                .secure(true)
//                .httpOnly(true)
//                .build();
//        response.addHeader("Set-Cookie", accessCookie.toString());
//        return GlobalResponse.of("200", "refresh accessToken complete");
//    }
//
//    @PostMapping("/logout")
//    public GlobalResponse logout() {
//
//        removeCrossDomainCookie();
//        return GlobalResponse.of("200", "로그아웃 성공");
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/test")
//    public GlobalResponse test(Principal principal) {
//        return GlobalResponse.of("200", "test", principal.getName());
//    }
//
//    private void removeCrossDomainCookie() {
//        ResponseCookie cookie1 = ResponseCookie.from("accessToken", null)
//                .path("/")
//                .maxAge(0)
//                .sameSite("None")
//                .secure(true)
//                .httpOnly(true)
//                .build();
//        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", null)
//                .path("/")
//                .maxAge(0)
//                .sameSite("None")
//                .secure(true)
//                .httpOnly(true)
//                .build();
//        response.addHeader("Set-Cookie", cookie1.toString());
//        response.addHeader("Set-Cookie", cookie2.toString());
//    }
//
//    private void addCrossDomainCookie(String accessToken, String refreshToken) {
//        ResponseCookie cookie1 = ResponseCookie.from("accessToken", accessToken)
//                .path("/")
//                .maxAge(60 * 10)
//                .sameSite("None")
//                .secure(true)
//                .httpOnly(true)
//                .build();
//        ResponseCookie cookie2 = ResponseCookie.from("refreshToken", refreshToken)
//                .path("/")
//                .maxAge(60 * 60 * 24)
//                .sameSite("None")
//                .secure(true)
//                .httpOnly(true)
//                .build();
//        response.addHeader("Set-Cookie", cookie1.toString());
//        response.addHeader("Set-Cookie", cookie2.toString());
//    }


}