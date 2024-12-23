package com.korea.blog.domain.member.controller;

import com.korea.blog.domain.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/join")
  public String join() {
    return "/join";
  }

  @Getter
  @Setter
  public static class JoinForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;
  }

  @PostMapping("/join")
  public String join(@Valid JoinForm joinForm) {
    if (!joinForm.getPassword().equals(joinForm.getPasswordConfirm())) {
      throw new RuntimeException("비밀번호가 일치하지 않습니다.");
    }

    memberService.join(joinForm.getUsername(), joinForm.getPassword());
    return "redirect:/members/login";
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class DuplicationCheckDto {
    private boolean result;
  }

  @GetMapping("/dupChk")
  @ResponseBody
  public DuplicationCheckDto duplicationCheck(String username) {
    boolean result = memberService.isExist(username);
    return new DuplicationCheckDto(result);
  }
}
